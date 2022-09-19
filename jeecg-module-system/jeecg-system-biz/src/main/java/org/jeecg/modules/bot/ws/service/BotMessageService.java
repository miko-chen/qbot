package org.jeecg.modules.bot.ws.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/12 21:18
 */
public interface BotMessageService {
    void onMessage(JSONObject data);
}
