package com.lwsmilence.enchanted.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * <a href="https://pdai.tech/md/spring/spring-x-framework-aop.html">...</a>
 */
@Component
@Aspect
@Slf4j
public class LoggingAspect {

    /**
     * 切入点表达式的完成写法：
     * 标准的表达式写法：访问修饰符 返回值 包名.包名.包名...类名.方法名(参数列表)
     *
     * public void com.lwsmilence.enchanted.common.aop。UserService.saveAccount()
     * 访问修饰符可以省略
     *
     *  void com.lwsmilence.enchanted.common.aop.UserService.saveAccount()
     * 可以使用通配符 *，表示任意或所有*。
     *
     * 对于参数列表 可以直接写数据类型：
     *
     * 基本类型直接写名称，比如 int
     * 引用类型写 包名.类名 的方式 java.lang.String
     * 可以使用通配符表示任意参数类型，但是该方法必须有参数
     * 可以使用 .. 表示有无参数均可，有参数可以是任意类型
     * 实际开发中切入点表达式的通常写法：切到业务层实现类下的所有方法：* com.smallbeef.service.impl.*.*(..)
     */
    @Pointcut("execution(* com.lwsmilence.enchanted.common.aop.*.*(..))")
    private void pt1() {}

    /**
     * 环绕通知
     * @param pjp
     * @return
     */
    @Around("pt1()")
    public Object logAround(ProceedingJoinPoint pjp) {
        log.info("开始执行方法:{}", pjp.getSignature().getName());

        Object result;

        try {
            result = pjp.proceed(pjp.getArgs());

            log.info("方法:{}执行完成, 结果为:{}", pjp.getSignature().getName(), result);

            return result;
        } catch (Throwable e) {
            log.info("方法：{}执行过程中出现异常:{}", pjp.getSignature().getName(), e.getStackTrace());
            throw new RuntimeException(e);
        }
    }

    /**
     * 前置通知
     */
    @Before("pt1()")
    public void beforeLog() {
        log.info("方法执行前，开始记录日志");
    }

    /**
     * 返回通知。在函数正常return之后执行
     */
    @AfterReturning("pt1()")
    public void afterReturn() {
        log.info("方法执行完成");
    }

    /**
     * 后置通知。方法执行完成后执行，无论是否发生异常。
     */
    @After("pt1()")
    public void after() {
        log.info("方法最终执行完成");
    }

    /**
     * 异常通知。方法抛出异常后执行
     */
    @AfterThrowing("pt1()")
    public void afterThrowing() {
        log.info("方法抛出异常执行");
    }

//    @AfterThrowing("")






}
