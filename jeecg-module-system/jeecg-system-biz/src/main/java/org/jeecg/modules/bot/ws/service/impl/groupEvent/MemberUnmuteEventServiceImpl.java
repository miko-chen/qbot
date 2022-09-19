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
 * 群成员被取消禁言事件（该成员不是Bot）
 */
@Service("MemberUnmuteEvent")
@Slf4j
public class MemberUnmuteEventServiceImpl implements BotMessageService {
    @Override
    public void onMessage(JSONObject data) {
        log.info("【事件】群成员被取消禁言事件 : {}",data);
    }
}
