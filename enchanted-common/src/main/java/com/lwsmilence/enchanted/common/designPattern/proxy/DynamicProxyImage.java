package com.lwsmilence.enchanted.common.designPattern.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理。
 * 实现InvocationHandler接口，在代理对象的方法被调用时，会执行invoke方法（相当于回调）。在invoke方法里，可以对代理的对象的方法进行扩展
 * 动态代理的动态在于，代理的对象是个Object，可以用于代理不同的对象
 */
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
