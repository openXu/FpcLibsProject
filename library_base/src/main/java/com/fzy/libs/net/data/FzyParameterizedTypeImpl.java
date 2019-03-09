package com.fzy.libs.net.data;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Author: openXu
 * Time: 2019/3/5 16:26
 * class: FzyParameterizedTypeImpl
 * Description: 解决gson解析FzyResponse<T>泛型类型的为题
 */
public class FzyParameterizedTypeImpl implements ParameterizedType {
    private final Class raw;
    private final Type[] args;

    public FzyParameterizedTypeImpl(Class raw, Type[] args) {
        this.raw = raw;
        this.args = args != null ? args : new Type[0];
    }

    @Override
    public Type[] getActualTypeArguments() {
        return args;
    }

    @Override
    public Type getRawType() {
        return raw;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}