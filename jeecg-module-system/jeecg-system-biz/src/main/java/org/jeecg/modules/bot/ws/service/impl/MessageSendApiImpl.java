package org.jeecg.modules.bot.ws.service.impl;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.jeecg.modules.bot.common.entity.*;
import org.jeecg.modules.bot.ws.common.entity.CommonRequest;
import org.jeecg.modules.bot.ws.service.MessageSendApi;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/12 21:29
 */
@Service
@Slf4j
public class MessageSendApiImpl implements MessageSendApi {
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
        log.info("发送的消息： {}",JSONObject.toJSONString(nudgeMessage));
        botWebSocket.send(CommonRequest.getInstance("sendNudge",nudgeMessage).toString());
    }

    @Override
    public void recall(RecallMessage recallMessage) {
        log.info("发送的消息： {}",JSONObject.toJSONString(recallMessage));
        botWebSocket.send(CommonRequest.getInstance("recall",recallMessage).toString());
    }

    @Override
    public void roamingMessages(RoamingMessages roamingMessages) {
        log.info("发送的消息： {}",JSONObject.toJSONString(roamingMessages));
        botWebSocket.send(CommonRequest.getInstance("roamingMessages",roamingMessages).toString());
    }

    @Override
    public void friendList() {
        log.info("获取好友列表");
        botWebSocket.send(CommonRequest.getInstanceId("friendList","friendList").toString());
    }

    @Override
    public void groupList() {
        log.info("获取群列表");
        botWebSocket.send(CommonRequest.getInstanceId("groupList","groupList").toString());
    }
    @Override
    public void botProfile() {
        log.info("获取bot资料");
        botWebSocket.send(CommonRequest.getInstanceId("botProfile","botProfile").toString());
    }
    @Override
    public void memberList(Long target) {
        log.info("获取群成员列表");
        JSONObject json = new JSONObject();
        json.put("target",target);
        botWebSocket.send(CommonRequest.getInstanceId("memberList",json,"memberList|"+target).toString());
    }

    @Override
    public void friendProfile(Long target) {
        log.info("获取好友资料");
        JSONObject json = new JSONObject();
        json.put("target",target);
        botWebSocket.send(CommonRequest.getInstanceId("friendProfile",json,"friendProfile|"+target).toString());
    }

    @Override
    public void userProfile(Long target) {
        log.info("获取QQ用户资料");
        JSONObject json = new JSONObject();
        json.put("target",target);
        botWebSocket.send(CommonRequest.getInstanceId("userProfile",json,"userProfile|"+target).toString());
    }

    @Override
    public void memberProfile(Long target, Long memberId) {
        log.info("获取群成员资料");
        JSONObject json = new JSONObject();
        json.put("target",target);
        json.put("memberId",memberId);
        botWebSocket.send(CommonRequest.getInstanceId("memberProfile",json,"memberProfile|"+target+"|"+memberId).toString());
    }


}
