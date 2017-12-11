package org.jiumao.mall.appkey.cache;

import java.util.concurrent.atomic.AtomicInteger;



/**
 * 缓存appkey关键数据，定期同步到数据库。
 * <ul>
 * <li>{@link #appkeys} appkey
 * <li>{@link #paths} :可访问的接口路径用多个用,分开。支持通配符*
 * <li>{@link #isUsage} :是否可用
 * <li>{@link #clicks} :访问次数，定期更新
 * 
 * @author ppf@jiumao.org
 * @date 2017年11月22日
 */
public class AppkeyTable extends AbstractTable {
    


    public AppkeyTable() {
    }
    

    public AppkeyTable(int size) {
        init(size);
    }

    private int[] appkeys ;
    private String[] paths ;
    private boolean[] isUsages ;
    private AtomicInteger[] clicks;// 访问次数


    @Override
    public AppkeyRow getRow(int index) {
        return new AppkeyRow(appkeys[index], paths[index], isUsages[index]);
    }

    public class AppkeyRow implements Row {
        int appkey;
        String path;
        boolean isUsage;


        public AppkeyRow(int appkey, String path, boolean isUsage) {
            super();
            this.appkey = appkey;
            this.path = path;
            this.isUsage = isUsage;
        }


        @Override
        public int id() {
            return appkey;
        }


        public int getAppkey() {
            return appkey;
        }


        public void setAppkey(int appkey) {
            this.appkey = appkey;
        }


        public String getPath() {
            return path;
        }


        public void setPath(String path) {
            this.path = path;
        }


        public boolean isUsage() {
            return isUsage;
        }


        public void setUsage(boolean isUsage) {
            this.isUsage = isUsage;
        }

    }


    public int[] getAppkeys() {
        return appkeys;
    }


    public void setAppkeys(int[] appkeys) {
        this.appkeys = appkeys;
    }


    public String[] getPaths() {
        return paths;
    }


    public void setPaths(String[] paths) {
        this.paths = paths;
    }


    public boolean[] getIsUsages() {
        return isUsages;
    }


    public void setIsUsages(boolean[] isUsages) {
        this.isUsages = isUsages;
    }


    @Override
    public void init(int size) {
        this.tableSize = size;
        appkeys = new int[tableSize];
        paths = new String[tableSize];
        isUsages = new boolean[tableSize];
        clicks = new AtomicInteger[tableSize];
    }


    @Override
    public Table copyEmptyTable() {
        return new AppkeyTable(tableSize);
    }


    @Override
    public void delete(int id) {
        isUsages[id % tableSize] = false;
    }


    /**
     * 添加一行数据，数据都不能为空
     * @param appkey 根据规则生成，不能随意写
     * @param path  接口权限，多个用 , 分隔
     * @param isUsage 是否启用
     * @param count 每访问一次+1，如果预先有值，可以设置为负值。
     * @return
     */
    public boolean put(int appkey, String path, boolean isUsage,int count) {
        int idx = appkey % tableSize;
        appkeys[idx] = appkey;
        paths[idx] = path;
        isUsages[idx] = isUsage;
        clicks[idx] = new AtomicInteger(count);
        return true;
    }


    @Override
    public boolean exist(int appkey) {
        return appkey == appkeys[appkey % tableSize];
    }


    public AtomicInteger[] getClicks() {
        return clicks;
    }


    public void setClicks(AtomicInteger[] clicks) {
        this.clicks = clicks;
    }

}
