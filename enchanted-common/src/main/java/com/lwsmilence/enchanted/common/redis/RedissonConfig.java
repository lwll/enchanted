package com.lwsmilence.enchanted.common.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.io.InputStream;

/**
 * 有两种方式来配置redisson： java bean；配置文件
 * 当使用java bean的方式来配置时，需要定义一个property文件，从配置文件（通用的application.yml）中读取
 * 配置项含义：
 * <a href="https://github.com/redisson/redisson/wiki/2.-%E9%85%8D%E7%BD%AE%E6%96%B9%E6%B3%95">...</a>
 */
@Configuration
public class RedissonConfig {

    RedisProperty redisProperty;

    RedissonConfig(RedisProperty redisProperty) {
        this.redisProperty = redisProperty;
    }

    //
    @Primary
    @Bean(name = "RedissonClientOne", destroyMethod = "shutdown")
    public RedissonClient redisson() throws IOException {
        InputStream configStream = this.getClass().getClassLoader().getResourceAsStream("redisson.yaml");
        Config config = Config.fromYAML(configStream);
        return Redisson.create(config);
    }

    @Bean(name = "RedissonClientTwo", destroyMethod = "shutdown")
    public RedissonClient redissonTwo() throws IOException {
        Config config = new Config();

        config.useSingleServer()
                .setAddress("redis://" + redisProperty.getHost() + ":" + redisProperty.getPort());
//                .setPassword(redisProperty.getPassword());

        return Redisson.create(config);
    }


}
