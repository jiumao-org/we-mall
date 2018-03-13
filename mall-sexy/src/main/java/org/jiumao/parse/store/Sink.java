package org.jiumao.parse.store;

import com.alibaba.fastjson.JSONObject;

/**
 * 数据持久化
 * @author Bevis-Pei<ppf@jiumao.org> 
 * @date 2018/03/11
 */
public class Sink {
   
    private Pipeline pip;

    public Sink(Pipeline pip) {
        super();
        this.pip = pip;
    }

    public Pipeline getPip() {
        return pip;
    }

    public void setPip(Pipeline pip) {
        this.pip = pip;
    }
    
    public void save(JSONObject json) throws Exception {
        pip.accept(json);
        pip.write();
    }
    
    
}
