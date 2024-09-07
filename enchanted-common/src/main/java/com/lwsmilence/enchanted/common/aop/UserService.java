package com.lwsmilence.enchanted.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    public void execute() {
        log.info("i am executing...");
    }

}
