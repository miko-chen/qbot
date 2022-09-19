package org.jeecg.modules.bot.ws.service.impl.message;

import com.alibaba.fastjson.JSONObject;
import org.jeecg.modules.bot.ws.service.BotMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/8 18:30
 *
 * 其他客户端消息
 */
@Service("OtherClientMessage")
@Slf4j
public class OtherClientMessageServiceImpl implements BotMessageService {
    @Override
    public void onMessage(JSONObject data) {
        log.info("其他客户端消息 : {}",data);
    }
}
