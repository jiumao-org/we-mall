package org.jiumao.mall.appkey.cache;

import org.jiumao.mall.appkey.AppkeyUtil;
import org.jiumao.mall.appkey.IdGenerator;

public abstract class AbstractTable implements Table {

    protected IdGenerator<Integer> generator = AppkeyUtil.DefaultGenerator;

    public IdGenerator<Integer> getGenerator() {
        return generator;
    }

    public void setGenerator(IdGenerator<Integer> generator) {
        this.generator = generator;
    }

}
