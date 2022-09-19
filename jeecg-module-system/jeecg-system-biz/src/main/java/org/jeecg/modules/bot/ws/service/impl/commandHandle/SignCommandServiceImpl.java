package org.jeecg.modules.bot.ws.service.impl.commandHandle;

import cn.hutool.core.date.CalendarUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.jeecg.modules.bot.common.entity.FriendAndGroupMeaasge;
import org.jeecg.modules.bot.common.entity.MessageChain;
import org.jeecg.modules.bot.qqsys.entity.QqUserGroup;
import org.jeecg.modules.bot.qqsys.mapper.QqUserGroupMapper;
import org.jeecg.modules.bot.ws.pojo.type.MessageType;
import org.jeecg.modules.bot.ws.service.CommandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/18 0:04
 */
@Service("signService")
public class SignCommandServiceImpl implements CommandService {
    @Resource
    private QqUserGroupMapper qqUserGroupMapper;

    @Override
    public String handle(MessageType data) {
        FriendAndGroupMeaasge friendAndGroupMeaasge = new FriendAndGroupMeaasge();
        friendAndGroupMeaasge.setTarget(data.getSender().getGroup().getId());
        List<MessageChain> list = new ArrayList<>();


        QqUserGroup qqUserGroup = qqUserGroupMapper.selectOne(new LambdaQueryWrapper<QqUserGroup>()
                .eq(QqUserGroup::getGroupId, data.getSender().getGroup().getId())
                .eq(QqUserGroup::getQq, data.getSender().getId())
        );
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(qqUserGroup.getLastSignTime() == null ? 0L : qqUserGroup.getLastSignTime());

        list.add(new MessageChain().at(data.getSender().getId()));
        Calendar now = Calendar.getInstance();
        if (CalendarUtil.isSameDay(calendar, now)) {
            list.add(new MessageChain().plain("您已经签到过了"));
        } else {
            int integral = (int) (Math.random() * 10) + 1;
            qqUserGroup.setIntegral(qqUserGroup.getIntegral() + integral);
            list.add(new MessageChain().plain("签到成功，签到获得积分:" + integral));
            qqUserGroup.setLastSignTime(now.getTimeInMillis());
            qqUserGroupMapper.updateById(qqUserGroup);
        }


        friendAndGroupMeaasge.setMessageChain(list);
        return friendAndGroupMeaasge.toString();
    }
}
