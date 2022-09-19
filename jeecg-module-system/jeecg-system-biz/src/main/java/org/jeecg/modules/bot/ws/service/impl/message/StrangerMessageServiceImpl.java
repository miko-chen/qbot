package org.jeecg.modules.bot.ws.service.impl.message;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.bot.ws.service.BotMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/8 18:30
 *
 * 陌生人消息
 */
@Service("StrangerMessage")
@Slf4j
public class StrangerMessageServiceImpl implements BotMessageService {
    @Override
    public void onMessage(JSONObject data) {
        log.info("陌生人消息 : {}",data);
    }
}
