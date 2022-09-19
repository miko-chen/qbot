package org.jeecg.modules.bot.ws.handle;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.bot.common.utils.BotBeanUtil;
import org.jeecg.modules.bot.ws.service.BotMessageService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/12 21:15
 */
@Component
@Slf4j
public class BotMessageHandle {

    @Resource
    BotBeanUtil<BotMessageService> botBeanUtil;

    /**
     * 分发收到的数据
     * */
    public void distribute(JSONObject jsonObject){
        String type = jsonObject.getString("type");
        if(StringUtils.isEmpty(type))type="common";
        BotMessageService botMessageService = botBeanUtil.getService(type);
        botMessageService.onMessage(jsonObject);
    }
}
