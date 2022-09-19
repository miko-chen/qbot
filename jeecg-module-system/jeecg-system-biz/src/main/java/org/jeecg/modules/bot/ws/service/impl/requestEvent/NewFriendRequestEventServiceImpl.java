package org.jeecg.modules.bot.ws.service.impl.requestEvent;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.bot.ws.service.BotMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/8 18:30
 *
 * 添加好友申请
 */
@Service("NewFriendRequestEvent")
@Slf4j
public class NewFriendRequestEventServiceImpl implements BotMessageService {
    @Override
    public void onMessage(JSONObject data) {
        log.info("【事件】添加好友申请 : {}",data);
    }
}
