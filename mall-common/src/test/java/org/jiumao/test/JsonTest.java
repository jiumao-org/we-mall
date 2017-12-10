package org.jiumao.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.jiumao.common.utils.JsonUtil;
import org.junit.Test;

public class JsonTest {
    
    @Test
    public void jsonTest() throws Exception {
        Map<String, String> m = new HashMap<String, String>();
        m.put("action", "auth");
        m.put("token", "auth");
        
        String json  =JsonUtil.toJson(m, false);
        System.out.println(json);
    }

}
