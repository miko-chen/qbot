package org.jeecg.modules.bot.ws.service.impl.groupEvent;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.bot.ws.service.BotMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/8 18:30
 *
 * Bot因群主解散群而退出群, 操作人一定是群主
 */
@Service("BotLeaveEventDisband")
@Slf4j
public class BotLeaveEventDisbandServiceImpl implements BotMessageService {
    @Override
    public void onMessage(JSONObject data) {
        log.info("【事件】Bot因群主解散群而退出群 : {}",data);
    }
}
