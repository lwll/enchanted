package com.lwsmilence.enchanted.common.redis;

import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

@Getter
@Slf4j
@Component
public class RedissonUtil {
    RedissonClient redisson;

    public RedissonUtil(RedissonClient client) {
        this.redisson = client;
    }

    // 添加更多工具类
    // 可参考代码：https://gitee.com/crazymaker/crazy-springcloud/blob/master/crazymaker-base/redission-starter/src/main/java/com/crazymaker/redission/demo/RedissonManager.java


}
