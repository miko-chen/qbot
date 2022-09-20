package org.jeecg.modules.bot.ws.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.jeecg.modules.bot.qqsys.entity.QqGroup;
import org.jeecg.modules.bot.qqsys.entity.QqUser;
import org.jeecg.modules.bot.qqsys.entity.QqUserGroup;
import org.jeecg.modules.bot.qqsys.mapper.QqGroupMapper;
import org.jeecg.modules.bot.qqsys.mapper.QqUserGroupMapper;
import org.jeecg.modules.bot.qqsys.mapper.QqUserMapper;
import org.jeecg.modules.bot.qqsys.service.IQqGroupService;
import org.jeecg.modules.bot.qqsys.service.IQqUserGroupService;
import org.jeecg.modules.bot.qqsys.service.IQqUserService;
import org.jeecg.modules.bot.ws.service.BotMessageService;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.bot.ws.service.MessageSendApi;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/9 9:48
 */
@Service("common")
@Slf4j
public class CommonServiceImpl implements BotMessageService {
    @Resource
    private IQqUserGroupService qqUserGroupService;
    @Resource
    private IQqGroupService qqGroupService;
    @Resource
    private MessageSendApi messageSendApi;
    @Resource
    private IQqUserService qqUserService;


    @Override
    public void onMessage(JSONObject data) {
        log.info("通用数据 : {}", data);
        String syncId = data.getString("syncId");
        String[] syncParams = syncId.split("\\|");
        if (syncParams.length == 1) {
            log.info("异步无参数据");
            if ("groupList".equals(syncParams[0])) {
                updateQqGroupInfo(data.getJSONArray("data"));
            }else if("friendList".equals(syncParams[0])){
                updateQqFriendList(data.getJSONArray("data"));
            }
        } else if (syncParams.length == 2) {
            log.info("异步单参数据");
            if ("userProfile".equals(syncParams[0]) || "friendProfile".equals(syncParams[0])) {
                updateQqUserInfo(Long.parseLong(syncParams[1]), data);
            } else if ("memberList".equals(syncParams[0])) {
                updateQqMemberList(data.getJSONArray("data"));
                //messageSendApi.userProfile(Long.parseLong(syncParams[1]));
            }
        } else if (syncParams.length == 3) {
            log.info("异步双参数据 , 参数1 : {} , 参数2 : {}", syncParams[1], syncParams[2]);
            if ("memberProfile".equals(syncParams[0])) {
                updateQqUserInfo(Long.parseLong(syncParams[2]), data);
            }


        }
    }

    /**
     * 更新QQ用户信息
     */
    public void updateQqUserInfo(long target, JSONObject data) {
        QqUser qqUser = qqUserService.getOne(new LambdaQueryWrapper<QqUser>().eq(QqUser::getQq, target));
        if(qqUser==null){
            qqUser = new QqUser();
        }
        qqUser.setQq(target);
        qqUser.setNickName(data.getString("nickname"));
        qqUser.setEmail(data.getString("email"));
        qqUser.setAge(data.getInteger("age"));
        qqUser.setLevel(data.getInteger("level"));
        qqUser.setSign(data.getString("sign"));
        qqUser.setSex("UNKNOWN".equals(data.getString("sex")) ? 0 : "MALE".equals(data.getString("sex")) ? 1 : 2);
        qqUserService.saveOrUpdate(qqUser);
    }

    /**
     * 更新群基本信息
     */
    public void updateQqGroupInfo(JSONArray data) {
        for (Object i : data) {
            JSONObject json = JSONObject.parseObject(JSONObject.toJSONString(i));
            long groupId = json.getLong("id");
            String name = json.getString("name");
            String permission = json.getString("permission");
            QqGroup qqGroup = qqGroupService.getOne(new LambdaQueryWrapper<QqGroup>().eq(QqGroup::getGroupId, groupId));
            if(qqGroup==null){
                qqGroup = new QqGroup();
                qqGroup.setGroupId(groupId);
            }
            qqGroup.setName(name);
            qqGroup.setPermission(permission);
            qqGroupService.saveOrUpdate(qqGroup);
            messageSendApi.memberList(groupId);
        }
    }

    /**
     * 更新群成员列表信息
     */
    public void updateQqMemberList(JSONArray data) {
        for (Object i : data) {
            JSONObject json = JSONObject.parseObject(JSONObject.toJSONString(i));
            JSONObject groupJson = json.getJSONObject("group");
            QqUserGroup qqUserGroup = qqUserGroupService.getOne(new LambdaQueryWrapper<QqUserGroup>()
                    .eq(QqUserGroup::getGroupId, groupJson.getLong("id"))
                    .eq(QqUserGroup::getQq, json.getLong("id")));
            if(qqUserGroup==null){
                qqUserGroup=new QqUserGroup();
                qqUserGroup.setQq(json.getLong("id"));
                qqUserGroup.setGroupId(groupJson.getLong("id"));
            }
            qqUserGroup.setMemberName(json.getString("memberName"));
            qqUserGroup.setIntegral(0);
            qqUserGroup.setPermission(json.getString("permission"));
            qqUserGroup.setSpecialTitle(json.getString("specialTitle"));
            qqUserGroup.setJoinTimestamp(json.getLong("joinTimestamp"));
            qqUserGroup.setLastSpeakTimestamp(json.getLong("lastSpeakTimestamp"));
            qqUserGroup.setMutetimeremaining(json.getLong("muteTimeRemaining"));
            qqUserGroupService.saveOrUpdate(qqUserGroup);
            messageSendApi.userProfile(qqUserGroup.getQq());
        }

    }

    /**
     * 更新好友列表信息
     * */
    public void updateQqFriendList(JSONArray data){
        for (Object i : data){
            JSONObject json = JSONObject.parseObject(JSONObject.toJSONString(i));
            messageSendApi.friendProfile(json.getLong("id"));
        }
    }
}
