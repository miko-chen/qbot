package org.jeecg.modules.bot.ws.service.impl.commandHandle;

import cn.hutool.core.math.MathUtil;
import com.aliyuncs.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.bot.common.entity.FriendAndGroupMeaasge;
import org.jeecg.modules.bot.common.entity.MessageChain;
import org.jeecg.modules.bot.qqsys.entity.QqUser;
import org.jeecg.modules.bot.qqsys.entity.QqUserGroup;
import org.jeecg.modules.bot.qqsys.mapper.QqUserGroupMapper;
import org.jeecg.modules.bot.qqsys.mapper.QqUserMapper;
import org.jeecg.modules.bot.ws.pojo.type.MessageType;
import org.jeecg.modules.bot.ws.service.CommandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/19 14:44
 */
@Service("integralService")
public class IntegralCommandServiceImpl implements CommandService {
    @Resource
    private QqUserGroupMapper qqUserGroupMapper;
    @Resource
    private QqUserMapper qqUserMapper;


    @Override
    public String handle(MessageType data) {
        FriendAndGroupMeaasge friendAndGroupMeaasge = new FriendAndGroupMeaasge();
        friendAndGroupMeaasge.setTarget(data.getSender().getGroup().getId());
        List<MessageChain> list = new ArrayList<>();

        List<MessageChain> messageChainList = data.getMessageChain();
        if (messageChainList.size() > 1) {
            String text = messageChainList.get(1).getText();
            String[] strArray = text.split(" ");
            if (strArray.length == 1) {
                QqUserGroup qqUserGroup = qqUserGroupMapper.selectOne(new LambdaQueryWrapper<QqUserGroup>()
                        .eq(QqUserGroup::getGroupId, data.getSender().getGroup().getId())
                        .eq(QqUserGroup::getQq, data.getSender().getId()));
                list.add(new MessageChain().at(data.getSender().getId()));
                list.add(new MessageChain().plain("您当前的积分为" + qqUserGroup.getIntegral()));
            } else if (strArray.length == 2) {
                QqUserGroup qqUserGroup = qqUserGroupMapper.selectOne(new LambdaQueryWrapper<QqUserGroup>()
                        .eq(QqUserGroup::getGroupId, data.getSender().getGroup().getId())
                        .eq(QqUserGroup::getQq, Long.parseLong(strArray[1])));
                if(null==qqUserGroup){list.add(new MessageChain().plain("请输入正确的QQ"));}
                else{
                    list.add(new MessageChain().at(data.getSender().getId()));
                    list.add(new MessageChain().plain("["+strArray[1]+"]当前的积分为" + qqUserGroup.getIntegral()));
                }

            } else {
                QqUser qqUser = qqUserMapper.selectOne(new LambdaQueryWrapper<QqUser>().eq(QqUser::getQq,data.getSender().getId()));
                //需要设置为管理员才可以
                if (qqUser.getIsAdmin() == 0
//                        || "ADMINISTRATOR".equals(data.getSender().getPermission())
                        || "OWNER".equals(data.getSender().getPermission())) {
                    LambdaQueryWrapper<QqUserGroup> queryWrapper = new LambdaQueryWrapper<QqUserGroup>()
                            .eq(QqUserGroup::getGroupId, data.getSender().getGroup().getId());
                    Long qq =  strArray.length==3?qqUser.getQq():Long.parseLong(strArray[3]);
                    queryWrapper.eq(QqUserGroup::getQq, qq);
                    list.add(new MessageChain().plain("["+qq+"]"));
                    QqUserGroup qqUserGroup = qqUserGroupMapper.selectOne(queryWrapper);

                    if ("add".equals(strArray[1])) {
                        qqUserGroup.setIntegral(qqUserGroup.getIntegral() + Integer.parseInt(strArray[2]));
                        list.add(new MessageChain().plain("新增积分"));
                    } else if ("sub".equals(strArray[1])) {
                        qqUserGroup.setIntegral(qqUserGroup.getIntegral() - Integer.parseInt(strArray[2]));
                        list.add(new MessageChain().plain("扣除积分"));
                    }

                    int updateCount = qqUserGroupMapper.updateById(qqUserGroup);
                    list.add(new MessageChain().plain(updateCount == 0 ? "失败" : "成功"));

                }
            }
        }
        friendAndGroupMeaasge.setMessageChain(list);
        return friendAndGroupMeaasge.toString();
    }
}

