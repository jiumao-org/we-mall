package org.jiumao.mall.appkey;

import org.apache.commons.lang3.RandomUtils;
import org.jiumao.mall.appkey.cache.AppkeyTable;
import org.jiumao.mall.appkey.cache.ArrayTableMap;
import org.jiumao.mall.appkey.cache.TbPosition;


public final class AppkeyUtil {

    static int startInclusive = 100 * 1000;
    static int endExclusive = Integer.MAX_VALUE >> 11;

    public static final IdGenerator<Integer> DefaultGenerator = () -> {
        return RandomUtils.nextInt(startInclusive, endExclusive);
    };

    private static final ArrayTableMap<AppkeyTable> Appkeys = new ArrayTableMap<AppkeyTable>(
        AppkeyTable.class);


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

}
