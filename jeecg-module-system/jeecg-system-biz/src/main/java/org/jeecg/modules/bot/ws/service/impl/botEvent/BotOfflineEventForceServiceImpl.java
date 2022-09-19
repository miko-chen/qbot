package org.jeecg.modules.bot.ws.service.impl.botEvent;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.bot.ws.service.BotMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/8 18:30
 *
 * Bot被挤下线
 */
@Service("BotOfflineEventForce")
@Slf4j
public class BotOfflineEventForceServiceImpl implements BotMessageService {
    @Override
    public void onMessage(JSONObject data) {
        log.info("【事件】Bot被挤下线 : {}",data);
    }
}
