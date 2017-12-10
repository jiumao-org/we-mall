package org.jiumao.service;

import java.util.List;

import org.jiumao.common.constants.LoggerName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class AbstractService<T> implements RPCService<T> {
    protected static final Logger log = LoggerFactory
            .getLogger(LoggerName.Server);
    protected List<String> serverAddrs;
    protected T Singleton;

    public List<String> getServerList() {
        return serverAddrs;
    }

    public void setServerList(List<String> serverAddrs) {
        this.serverAddrs = serverAddrs;
    }

    public T getInstance() {
        return Singleton;
    }

}
