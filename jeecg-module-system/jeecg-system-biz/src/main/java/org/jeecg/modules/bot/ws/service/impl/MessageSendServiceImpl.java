package org.jeecg.modules.bot.ws.service.impl;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.jeecg.modules.bot.common.entity.*;
import org.jeecg.modules.bot.ws.common.entity.CommonRequest;
import org.jeecg.modules.bot.ws.service.MessageSendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/12 21:29
 */
@Service
@Slf4j
public class MessageSendServiceImpl implements MessageSendService {
    @Resource
    private WebSocketClient botWebSocket;

    @Override
    public void messageFromId(int id, Long target) {

    }

    @Override
    public void sendFriendMessage(FriendAndGroupMeaasge friendMeaasge) {
        log.info("发送的消息： {}", JSONObject.toJSONString(friendMeaasge));
        botWebSocket.send(CommonRequest.getInstance("sendFriendMessage",friendMeaasge).toString());
    }

    @Override
    public void sendGroupMessage(FriendAndGroupMeaasge groupMessage) {
        log.info("发送的消息： {}",JSONObject.toJSONString(groupMessage));
        botWebSocket.send(CommonRequest.getInstance("sendGroupMessage",groupMessage).toString());
    }

    @Override
    public void sendGroupMessage(String groupMessage) {
        String result = CommonRequest.getInstance("sendGroupMessage",JSONObject.parseObject(groupMessage)).toString();
        log.info("包装后发送的消息： {}",result);
        botWebSocket.send(result);
    }

    @Override
    public void sendTempMessage(TempMeaasge tempMeaasge) {
        log.info("发送的消息： {}",JSONObject.toJSONString(tempMeaasge));
        botWebSocket.send(CommonRequest.getInstance("sendTempMessage",tempMeaasge).toString());
    }

    @Override
    public void sendNudge(NudgeMessage nudgeMessage) {

    }

    @Override
    public void recall(RecallMessage recallMessage) {

    }

    @Override
    public void roamingMessages(RoamingMessages roamingMessages) {

    }
}
