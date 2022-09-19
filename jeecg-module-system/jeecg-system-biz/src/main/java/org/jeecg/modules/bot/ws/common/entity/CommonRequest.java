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

    public static CommonRequest getInstance(String command,Object content){
        return new CommonRequest().setCommand(command).setContent(content).setSyncId(UUID.randomUUID().toString());
    }

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
