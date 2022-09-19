package org.jeecg.modules.bot.ws.service;

import org.jeecg.modules.bot.ws.pojo.type.MessageType;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/18 0:03
 */
public interface CommandService {
    String handle(MessageType data);
}
