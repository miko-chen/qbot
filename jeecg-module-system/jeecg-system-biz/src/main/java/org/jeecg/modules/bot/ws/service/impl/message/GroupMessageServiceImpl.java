package org.jeecg.modules.bot.ws.service.impl.message;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.jeecg.modules.bot.common.entity.FriendAndGroupMeaasge;
import org.jeecg.modules.bot.common.entity.MessageChain;
import org.jeecg.modules.bot.common.utils.BotBeanUtil;
import org.jeecg.modules.bot.qqsys.entity.*;
import org.jeecg.modules.bot.qqsys.mapper.*;
import org.jeecg.modules.bot.ws.pojo.type.GroupType;
import org.jeecg.modules.bot.ws.pojo.type.MessageType;
import org.jeecg.modules.bot.ws.pojo.type.Sender;
import org.jeecg.modules.bot.ws.service.BotMessageService;
import org.jeecg.modules.bot.ws.service.CommandService;
import org.jeecg.modules.bot.ws.service.MessageSendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/8 18:30
 * <p>
 * 群消息
 */
@Service("GroupMessage")
@Slf4j
public class GroupMessageServiceImpl implements BotMessageService {

    @Resource
    private MessageSendService messageSendService;
    @Resource
    private QqCommandMapper qqCommandMapper;
    @Resource
    private QqCommandPacketMapper qqCommandPacketMapper;
    @Resource
    private QqUserMapper qqUserMapper;
    @Resource
    private QqGroupMapper qqGroupMapper;
    @Resource
    private QqUserGroupMapper qqUserGroupMapper;
    @Resource
    private BotBeanUtil<CommandService> botBeanUtil;

    @Override
    public void onMessage(JSONObject data) {
        log.info("群消息 : {}", data);
        MessageType messageType = data.toJavaObject(MessageType.class);
        saveSenderInfo(messageType.getSender());
        List<MessageChain> messageChainList = messageType.getMessageChain();
        if (messageChainList.size() > 1) {
            MessageChain msg = messageChainList.get(1);
            MessageChain.Type type = msg.getType();
            if (type == MessageChain.Type.Plain) {
                String text = msg.getText();
                if (StringUtils.isEmpty(text)) return;
                List<QqCommand> commandList = qqCommandMapper.selectList(new LambdaQueryWrapper<QqCommand>()
                        .eq(QqCommand::getIsEnable, 0)
                        .orderByDesc(QqCommand::getCommand)
                );
                commandList.forEach(qqCommand -> {
                    QqCommandPacket qqCommandPacket = qqCommandPacketMapper.selectById(qqCommand.getPacket());

                    if (qqCommandPacket.getIsEnable() == 0
                            && qqCommand.getIsEnable() == 0
                            && text.length() >= qqCommand.getCommand().length()
                            && text.startsWith(qqCommand.getCommand())) {
                        String res;
                        if ("function".equals(qqCommandPacket.getType())) {

                            CommandService commandService = botBeanUtil.getService(qqCommand.getUrl());
                            res = commandService.handle(messageType);
                            log.info("调用本地函数处理指令，指令：{}", qqCommand.getCommand());
                        } else {
                            res = Unirest.post(qqCommand.getUrl()).header("Content-Type", "application/json")
                                    .field("message", data).asString().getBody();
                            log.info("调用远程函数处理指令，指令：{}，发送的消息：{}", qqCommand.getCommand(), res);
                        }
                        messageSendService.sendGroupMessage(res);
                    }


                });


            }
        }
    }

    public void saveSenderInfo(Sender sender) {
        if (!qqUserMapper.exists(new LambdaQueryWrapper<QqUser>().eq(QqUser::getQq, sender.getId()))) {
            QqUser qqUser = new QqUser();
            qqUser.setQq(sender.getId());
            qqUser.setIsAdmin(1);
            qqUser.setIsEnable(0);
            qqUser.setNickName(sender.getNickname());
            qqUserMapper.insert(qqUser);
        }
        GroupType groupType = sender.getGroup();
        if (!qqGroupMapper.exists(new LambdaQueryWrapper<QqGroup>().eq(QqGroup::getGroupId, groupType.getId()))) {
            QqGroup qqGroup = new QqGroup();

            qqGroup.setGroupId(groupType.getId());
            qqGroup.setName(groupType.getName());
            qqGroupMapper.insert(qqGroup);
        }
        if (!qqUserGroupMapper.exists(new LambdaQueryWrapper<QqUserGroup>().eq(QqUserGroup::getGroupId, groupType.getId()).eq(QqUserGroup::getQq, sender.getId()))) {
            QqUserGroup qqUserGroup = new QqUserGroup()
                    .setGroupId(groupType.getId())
                    .setQq(sender.getId())
                    .setJoinTimestamp(sender.getJoinTimestamp())
                    .setLastSpeakTimestamp(sender.getLastSpeakTimestamp())
                    .setMutetimeremaining(sender.getMuteTimeRemaining())
                    .setIntegral(0)
                    .setIsEnable(0)
                    .setSpecialTitle(sender.getSpecialTitle())
                    .setPermission(sender.getPermission());
            qqUserGroupMapper.insert(qqUserGroup);
        }
    }
}
