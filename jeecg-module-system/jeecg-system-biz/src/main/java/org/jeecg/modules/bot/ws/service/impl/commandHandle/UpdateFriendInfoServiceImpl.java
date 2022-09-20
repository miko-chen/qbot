package org.jeecg.modules.bot.ws.service.impl.commandHandle;

import org.hibernate.validator.constraints.Range;
import org.jeecg.modules.bot.common.entity.FriendAndGroupMeaasge;
import org.jeecg.modules.bot.common.entity.MessageChain;
import org.jeecg.modules.bot.ws.pojo.type.MessageType;
import org.jeecg.modules.bot.ws.service.CommandService;
import org.jeecg.modules.bot.ws.service.MessageSendApi;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/20 15:01
 */
@Service("updateFriendInfoService")
public class UpdateFriendInfoServiceImpl implements CommandService {
    @Resource
    private MessageSendApi messageSendApi;
    @Override
    public String handle(MessageType data) {
        FriendAndGroupMeaasge friendAndGroupMeaasge = new FriendAndGroupMeaasge();
        friendAndGroupMeaasge.setTarget(data.getSender().getGroup().getId());
        List<MessageChain> list = new ArrayList<>();
        messageSendApi.friendList();
        list.add(new MessageChain().plain("正在更新"));
        friendAndGroupMeaasge.setMessageChain(list);
        return friendAndGroupMeaasge.toString();
    }
}
