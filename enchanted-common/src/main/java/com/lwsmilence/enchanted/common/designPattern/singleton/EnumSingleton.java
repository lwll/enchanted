package com.lwsmilence.enchanted.common.designPattern.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * 使用枚举来实现单例。
 * 枚举在jvm中是唯一的。
 */
@Slf4j
public enum EnumSingleton {

    INSTANCE;

    public void doSomething() {
        log.info("enum singleton do something");
    }
}
