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
 * 群临时消息
 */
@Service("TempMessage")
@Slf4j
public class TempMessageServiceImpl implements BotMessageService {
    @Override
    public void onMessage(JSONObject data) {
        log.info("群临时消息 : {}",data);
    }
}
