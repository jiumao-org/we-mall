package org.jiumao.service;

import java.util.List;


public abstract class AbstractService<T> implements RPCService<T> {
    protected List<String> serverAddrs;


    public List<String> getServerList() {
        return serverAddrs;
    }
    
    public void setServerList(List<String> serverAddrs) {
        this.serverAddrs = serverAddrs;
    }
    
   public abstract T getInstance();

}
