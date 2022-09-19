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
 * 通过QQ消息执行框架命令触发的事件
 */
@Service("CommandExecutedEvent")
@Slf4j
public class CommandExecutedEventServiceImpl implements BotMessageService {
    @Override
    public void onMessage(JSONObject data) {
        log.info("【事件】框架命令被执行 : {}",data);
    }
}
