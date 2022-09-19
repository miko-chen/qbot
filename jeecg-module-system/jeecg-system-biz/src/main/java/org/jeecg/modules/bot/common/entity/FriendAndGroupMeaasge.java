package org.jeecg.modules.bot.common.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/9 10:52
 */
@Data
public class FriendAndGroupMeaasge {
    private Long target;
    private List<MessageChain> messageChain;

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
