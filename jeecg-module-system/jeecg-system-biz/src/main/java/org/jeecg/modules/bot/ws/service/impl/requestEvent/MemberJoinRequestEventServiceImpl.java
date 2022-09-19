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
 * 用户入群申请（Bot需要有管理员权限）
 */
@Service("MemberJoinRequestEvent")
@Slf4j
public class MemberJoinRequestEventServiceImpl implements BotMessageService {
    @Override
    public void onMessage(JSONObject data) {
        log.info("【事件】用户入群申请 : {}",data);
    }
}
