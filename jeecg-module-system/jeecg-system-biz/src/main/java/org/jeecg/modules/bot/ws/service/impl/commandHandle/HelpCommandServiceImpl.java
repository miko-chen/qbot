package org.jeecg.modules.bot.ws.service.impl.commandHandle;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.bot.common.entity.FriendAndGroupMeaasge;
import org.jeecg.modules.bot.common.entity.MessageChain;
import org.jeecg.modules.bot.qqsys.entity.QqCommand;
import org.jeecg.modules.bot.qqsys.entity.QqCommandPacket;
import org.jeecg.modules.bot.qqsys.mapper.QqCommandMapper;
import org.jeecg.modules.bot.qqsys.mapper.QqCommandPacketMapper;
import org.jeecg.modules.bot.ws.pojo.type.MessageType;
import org.jeecg.modules.bot.ws.service.CommandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/19 13:48
 */
@Service("helpService")
public class HelpCommandServiceImpl implements CommandService {
    @Resource
    private QqCommandPacketMapper qqCommandPacketMapper;
    @Resource
    private QqCommandMapper qqCommandMapper;

    @Override
    public String handle(MessageType data) {
        FriendAndGroupMeaasge friendAndGroupMeaasge = new FriendAndGroupMeaasge();
        friendAndGroupMeaasge.setTarget(data.getSender().getGroup().getId());
        List<MessageChain> list = new ArrayList<>();

        List<MessageChain> messageChainList = data.getMessageChain();
        list.add(new MessageChain().plain("----帮助(/help)"));
        if (messageChainList.size() > 1) {
            String text = messageChainList.get(1).getText();
            String[] strArray = text.split(" ");
            if (strArray.length > 1) {
                list.add(new MessageChain().plain(" [" + strArray[1] + "]----\n"));
                QqCommandPacket qqCommandPacket = qqCommandPacketMapper.selectOne(new LambdaQueryWrapper<QqCommandPacket>()
                        .eq(QqCommandPacket::getName, strArray[1]));
                if (null != qqCommandPacket) {
                    List<QqCommand> qqCommands = qqCommandMapper.selectList(new LambdaQueryWrapper<QqCommand>()
                            .eq(QqCommand::getPacket, qqCommandPacket.getId()));
                    for (QqCommand qqCommand : qqCommands) {
                        list.add(new MessageChain().plain(qqCommand.getCommand()+"\t"+(qqCommand.getIsEnable()==0?"启用":"禁用")));
                    }
                }
            } else {
                list.add(new MessageChain().plain("----\n"));
                long total = 0L;
                Page<QqCommandPacket> qqCommandPacketPage = qqCommandPacketMapper.selectPage(Page.of(1, 10, total), null);
                List<QqCommandPacket> qqCommandPackets = qqCommandPacketPage.getRecords();
                if (qqCommandPackets.size() == 0) {
                    list.add(new MessageChain().plain("未找到指令\n"));
                } else {
                    for (QqCommandPacket qcp : qqCommandPacketPage.getRecords()) {
                        list.add(new MessageChain().plain(qcp.getName() + "\n"));
                    }
                }
                list.add(new MessageChain().plain("请输入/帮助 [模块名]查看详细信息"));
            }
        }

        friendAndGroupMeaasge.setMessageChain(list);

        return friendAndGroupMeaasge.toString();
    }
}
