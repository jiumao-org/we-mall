package org.jiumao.mall.appkey;

import java.util.Timer;
import java.util.TimerTask;

import org.bson.Document;
import org.jiumao.mall.MongoUtil;
import org.jiumao.mall.appkey.cache.AppkeyTable;
import org.jiumao.mall.appkey.cache.ArrayTableMap;

import com.mongodb.client.MongoCollection;


public class AppkeyService {

    private static final MongoCollection<Document> table = MongoUtil.getCollection("appkey");

    private static final ArrayTableMap<AppkeyTable> Appkeys = new ArrayTableMap<AppkeyTable>(
        AppkeyTable.class);
    private static final Timer UpdateAppkeyClicksToMongo = new Timer();


    public static void startUpdateAppkeys() {
        UpdateAppkeyClicksToMongo.schedule(new SynToMongoTask(Appkeys, table), 24 * 60 * 60 * 1000, 2 * 1000);
    }


    public static boolean access(int appkey) {
        int tb = Appkeys.contains(appkey);
        if (-1 == tb) {
            return false;
        }
        AppkeyTable t = Appkeys.getTables().get(tb);
        t.getClicks()[tb % Appkeys.getTableSize()].incrementAndGet();
        return true;
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
        private int tbSize = Appkeys.getTableSize();
        private AppkeyTable currentTable = new AppkeyTable(tbSize);


        public Recovery() {
            Appkeys.clear();
            Appkeys.addTable(currentTable);
        }


        public void update(int appkey, String path, boolean isUsage, int count) {
            if (currentTable.getRow(appkey % tbSize).id() > 0) {
                currentTable = new AppkeyTable(tbSize);
                Appkeys.addTable(currentTable);
            }
            currentTable.put(appkey, path, isUsage, count);
        }


        public void reBuildDelTable() {
            Appkeys.findEmptyRow();
        }

    }


    public static ArrayTableMap<AppkeyTable> getAppkeys() {
        return Appkeys;
    }

}


class SynToMongoTask extends TimerTask {

    final ArrayTableMap<AppkeyTable> Appkeys;
    MongoCollection<Document> table;


    public SynToMongoTask(ArrayTableMap<AppkeyTable> Appkeys, MongoCollection<Document> table) {
        this.Appkeys = Appkeys;
        this.table = table;
    }


    @Override
    public void run() {

    }
}
