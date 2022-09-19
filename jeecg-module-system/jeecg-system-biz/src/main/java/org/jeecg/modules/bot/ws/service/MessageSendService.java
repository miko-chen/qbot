package org.jeecg.modules.bot.ws.service;

import org.jeecg.modules.bot.common.entity.*;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/12 21:29
 */
public interface MessageSendService {
    /**
     * 根据消息id获取消息
     * */
    void messageFromId(int id,Long target);
    /**
     * 发送好友消息
     * */
    void sendFriendMessage(FriendAndGroupMeaasge friendMeaasge);
    /**
     * 发送群消息
     * */
    void sendGroupMessage(FriendAndGroupMeaasge groupMessage);
    void sendGroupMessage(String groupMessage);
    /**
     * 发送群临时消息
     * */
    void sendTempMessage(TempMeaasge groupMessage);
    /**
     * 发送戳一戳消息
     * */
    void sendNudge(NudgeMessage nudgeMessage);
    /**
     * 撤回消息
     * */
    void recall(RecallMessage recallMessage);
    /**
     * 发送戳一戳消息
     * */
    void roamingMessages(RoamingMessages roamingMessages);
}
