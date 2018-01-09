package org.jiumao.mall.appkey;

@FunctionalInterface
public interface IdGenerator<T> {
    T id();
}
