package org.jiumao.mall.db;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Unit test for simple App.
 */
public class CopyOfAppTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CopyOfAppTest( String testName )
    {
        super( testName );
    }


    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(CopyOfAppTest.class);
    }


    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);

        String sql =
                " add_sql:insert into generalcrawler_brand_sbcx"
                + " (applicantAddr_en,brandName,goo    "
                + "                             ds_url,"
                + "applicantName_en,remark,registerApplyNum"
                + ",classNum,source,applicantAddr_zh,brand_t"
                + "erm,applicantDate,isSharedBrand,brand_num"
                + ",goods_status_all,brand_z             "
                + "                    h,brand_en,brand"
                + "_py,brand_head,brand_type,applicantNa"
                + "me_zh,goods_details_all,raw_url,raw_host)"
                + " values ('  ','雪鸽 ','http://80g.ht.cn/img.php?img_id=58b0e"
                + "                                 4b55c9144c02c2527b2','"
                + " ','商标已无效。 ','528813 ','32 ','商标查询网','江苏张家"
                + "港市杨舍镇  ','1990年09月20日至2000年09月19日 ','1989-09-"
                + "23 ','否  ',' ','                                "
                + " 商标已无效() ','雪鸽 ',' ','xuege ','XG ','普通商标 "
                + "','国营张家港市食品厂  ','[3202——汽水;]  ','http://cx"
                + ".ht.cn/searchdetial-id-140930.html','null')";
    }
}
