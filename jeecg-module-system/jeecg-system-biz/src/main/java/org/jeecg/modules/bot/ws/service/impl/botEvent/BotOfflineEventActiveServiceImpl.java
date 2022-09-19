package org.jeecg.modules.bot.ws.service.impl.botEvent;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.bot.ws.service.BotMessageService;
import org.jeecg.modules.bot.ws.service.BotMessageService;
import org.springframework.stereotype.Service;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/8 18:30
 *
 * Bot主动离线
 */
@Service("BotOfflineEventActive")
@Slf4j
public class BotOfflineEventActiveServiceImpl implements BotMessageService {
    @Override
    public void onMessage(JSONObject data) {
        log.info("【事件】Bot主动离线 : {}",data);
    }
}
