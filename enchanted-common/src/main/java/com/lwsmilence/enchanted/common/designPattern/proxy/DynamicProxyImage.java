package com.lwsmilence.enchanted.common.designPattern.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class DynamicProxyImage implements InvocationHandler {

    private Object realObject;

    public DynamicProxyImage(Object o) {
        this.realObject = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("display")) {
            log.info("Before displaying image...");
            Object result = method.invoke(realObject, args);
            log.info("After displaying image...");
            return result;
        } else {
            return method.invoke(realObject, args);
        }
    }
}
