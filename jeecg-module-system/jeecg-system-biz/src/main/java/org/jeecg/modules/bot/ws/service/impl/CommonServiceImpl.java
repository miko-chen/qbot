package org.jeecg.modules.bot.ws.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.bot.ws.service.BotMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/9 9:48
 */
@Service("common")
@Slf4j
public class CommonServiceImpl implements BotMessageService {
    @Override
    public void onMessage(JSONObject data) {
        log.info("通用数据 : {}",data);
    }
}
