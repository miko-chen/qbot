package org.jeecg.modules.bot.ws.common.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/9 21:40
 */
@Data
@Accessors(chain = true)
public class CommonRequest {
    private String syncId;
    private String command;
    private String subCommand;
    private Object content;
    public static CommonRequest getInstance(String command){
        return new CommonRequest().setCommand(command).setContent(null).setSyncId(UUID.randomUUID().toString());
    }
    public static CommonRequest getInstance(String command,Object content){
        return new CommonRequest().setCommand(command).setContent(content).setSyncId(UUID.randomUUID().toString());
    }
    public static CommonRequest getInstanceId(String command,String syncId){
        return new CommonRequest().setCommand(command).setContent(null).setSyncId(syncId);
    }
    public static CommonRequest getInstanceId(String command,Object content,String syncId){
        return new CommonRequest().setCommand(command).setContent(content).setSyncId(syncId);
    }

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
