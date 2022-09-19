package org.jeecg.modules.bot.ws.pojo.type;

import lombok.Data;
import org.jeecg.modules.bot.common.entity.MessageChain;

import java.util.List;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/9 22:25
 */
@Data
public class MessageType {
    private String type;
    private Sender sender;
    private List<MessageChain> messageChain;
}
