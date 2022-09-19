package org.jeecg.modules.bot.ws.service.impl.botEvent;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.bot.ws.service.BotMessageService;
import org.springframework.stereotype.Service;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/8 18:30
 *
 * Bot被服务器断开或因网络问题而掉线
 */
@Service("BotOfflineEventDropped")
@Slf4j
public class BotOfflineEventDroppedServiceImpl implements BotMessageService {
    @Override
    public void onMessage(JSONObject data) {
        log.info("【事件】Bot被服务器断开或因网络问题而掉线 : {}",data);
    }
}
