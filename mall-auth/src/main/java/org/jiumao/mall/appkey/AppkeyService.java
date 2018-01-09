package org.jiumao.mall.appkey;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Singleton;

import org.bson.Document;
import org.jiumao.db.MongoUtil;
import org.jiumao.db.mongo.MongoCRUD;
import org.jiumao.db.mongo.MongoOperators;
import org.jiumao.mall.appkey.cache.AppkeyTable;
import org.jiumao.mall.appkey.cache.ArrayTableMap;
import org.jiumao.mall.appkey.cache.Tables;
import org.jiumao.mall.appkey.cache.TbPosition;

import com.mongodb.client.MongoCollection;

/**
 * 提供Appkey 缓存和持久化 CRUD操作
 * 
 * @author ppf@jiumao.org
 */
@Singleton
public class AppkeyService {

    private static final MongoCollection<Document> APPKEY_COL = MongoUtil.getCollection("appkey");
    private static final ArrayTableMap<AppkeyTable> TABLE_MAP = new ArrayTableMap<AppkeyTable>(AppkeyTable.class);

    private final Timer updateAppkeyClicksToMongo = new Timer("AppkeyService", true);

    public AppkeyService() {
        startUp();
    }

    private void startUp() {
        // 从mongo中恢复到内存
        Recovery rec = new Recovery();
        APPKEY_COL.find().forEach((Document action) -> {
            int appkey = (int) action.get("_id");
            String path = (String) action.get("path");
            boolean isUsage = (boolean) action.get("isUsage");
            rec.update(appkey, path, isUsage, 0);
        });
        // 开启定时同步到Mongodb服务
        updateAppkeyClicksToMongo.schedule(new SynToMongoTask(TABLE_MAP, APPKEY_COL), 24 * 60 * 60 * 1000, 2 * 1000);
    }


    /**
     * 判断提供的Appkey是否可以访问
     * 
     * @param appkey 客户授权的appkey
     * @param path 访问接口
     */
    public boolean access(int appkey, String path) {
        int tb = TABLE_MAP.contains(appkey);
        if (-1 == tb) {
            return false;
        }
        AppkeyTable t = TABLE_MAP.getTables().get(tb);
        boolean isUsage = t.getIsUsages()[appkey % TABLE_MAP.getTableSize()];
        boolean isAccess = isAccessPath(path, t.getPaths()[appkey % TABLE_MAP.getTableSize()]);
        if (isUsage && isAccess) {
            t.getClicks()[tb % TABLE_MAP.getTableSize()].incrementAndGet();
        }
        return false;
    }

    private boolean isAccessPath(String desPath, String userPath) {
        String[] paths = userPath.split(Tables.ACCESS_PATH_SPLIT);
        for (String p : paths) {
            if (p.contains("*")) {
                // /api/* ,/api/appkey
                if (desPath.startsWith(p.replace("*", ""))) return true;
            } else {
                // /api/appkey , /api/appkey
                if (p.equalsIgnoreCase(desPath)) return true;
            }

        }
        return false;
    }

    /**
     * 更新appkey表
     */
    public boolean update(AppkeyDetail appkeyDetail) {
        int appkey = appkeyDetail.get_id();
        int tbIdx = TABLE_MAP.contains(appkey);
        if (-1 != tbIdx) {
            AppkeyTable tb = TABLE_MAP.getTables().get(tbIdx);
            tb.put(appkey, appkeyDetail.getPath(), appkeyDetail.isUsage(), appkeyDetail.getCount());
            MongoCRUD.updateById(APPKEY_COL, appkeyDetail, Document.parse(appkeyDetail.toJson()), false);
            return true;
        }

        return false;
    }

    /**
     * 给予权限，生成Appkey
     */
    public int insert(AppkeyDetail appkeyDetail) {
        TbPosition<AppkeyTable> pos = TABLE_MAP.getPosition();
        int appkey = pos.getId();
        appkeyDetail.set_id(appkey);
        pos.getTable().put(appkey, appkeyDetail.getPath(), appkeyDetail.isUsage(), appkeyDetail.getCount());
        APPKEY_COL.insertOne(Document.parse(appkeyDetail.toJson()));
        return appkey;
    }

    /**
     * 被恢复的表数据一定是时间序，即按照生成时候的顺序，否则浪费空间
     * <p>
     * 最后一定要 {@link #reBuildDelTable()}
     * 
     * @author ppf@jiumao.org
     * @date 2017年11月23日
     */
    public static class Recovery {
        private int tbSize = TABLE_MAP.getTableSize();
        private AppkeyTable currentTable = new AppkeyTable(tbSize);


        public Recovery() {
            TABLE_MAP.clear();
            TABLE_MAP.addTable(currentTable);
        }


        public void update(int appkey, String path, boolean isUsage, int count) {
            if (currentTable.getRow(appkey % tbSize).id() > 0) {
                currentTable = new AppkeyTable(tbSize);
                TABLE_MAP.addTable(currentTable);
            }
            currentTable.put(appkey, path, isUsage, count);
        }


        public void reBuildDelTable() {
            TABLE_MAP.findEmptyRow();
        }

    }


    public static ArrayTableMap<AppkeyTable> getAppkeys() {
        return TABLE_MAP;
    }

}


class SynToMongoTask extends TimerTask {

    final ArrayTableMap<AppkeyTable> appkeys;
    MongoCollection<Document> coll;


    public SynToMongoTask(ArrayTableMap<AppkeyTable> tableMap, MongoCollection<Document> table) {
        this.appkeys = tableMap;
        this.coll = table;
    }


    @Override
    public void run() {
        List<AppkeyTable> tables = appkeys.getTables();
        for (AppkeyTable at : tables) {
            AtomicInteger[] clicks = at.getClicks();
            int[] ids = at.getAppkeys();
            for (int i = 0; i < at.getTableSize(); i++) {
                int clickNum = clicks[i].getAndSet(0);
                int id = ids[i];
                if (id < 1) continue;
                Document newDoc = new Document(MongoOperators.INC, new Document("count", -clickNum));
                MongoCRUD.updateById(coll, id, newDoc, false);
            }
        }

    }
}
