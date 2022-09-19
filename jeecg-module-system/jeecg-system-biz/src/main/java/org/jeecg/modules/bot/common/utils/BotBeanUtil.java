package org.jeecg.modules.bot.common.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/12 21:17
 */
@Component
public class BotBeanUtil<T> {
    @Resource
    private ApplicationContext applicationContext;

    public T getService(String name){
        return (T)applicationContext.getBean(name);
    }
}
