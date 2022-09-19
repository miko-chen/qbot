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
 * 某群入群公告改变
 */
@Service("GroupEntranceAnnouncementChangeEvent")
@Slf4j
public class GroupEntranceAnnouncementChangeEventServiceImpl implements BotMessageService {
    @Override
    public void onMessage(JSONObject data) {
        log.info("【事件】某群入群公告改变 : {}",data);
    }
}
