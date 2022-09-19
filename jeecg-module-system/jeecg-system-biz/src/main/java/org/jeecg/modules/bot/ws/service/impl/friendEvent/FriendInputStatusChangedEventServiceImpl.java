package org.jeecg.modules.bot.ws.service.impl.friendEvent;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.bot.ws.service.BotMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/8 18:30
 *
 * 好友输入状态改变
 */
@Service("FriendInputStatusChangedEvent")
@Slf4j
public class FriendInputStatusChangedEventServiceImpl implements BotMessageService {
    @Override
    public void onMessage(JSONObject data) {
        log.info("【事件】好友输入状态改变 : {}",data);
    }
}
