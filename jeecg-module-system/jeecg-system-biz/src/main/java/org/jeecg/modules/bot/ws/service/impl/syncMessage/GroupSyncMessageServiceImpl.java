package org.jeecg.modules.bot.ws.service.impl.syncMessage;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.bot.ws.service.BotMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/8 18:30
 *
 * 同步群消息
 */
@Service("GroupSyncMessage")
@Slf4j
public class GroupSyncMessageServiceImpl implements BotMessageService {
    @Override
    public void onMessage(JSONObject data) {
        log.info("同步群消息 : {}",data);
    }
}
