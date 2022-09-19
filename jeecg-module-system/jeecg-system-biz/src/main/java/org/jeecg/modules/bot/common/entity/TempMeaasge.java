package org.jeecg.modules.bot.common.entity;

import lombok.Data;

import java.util.List;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/9 10:52
 */
@Data
public class TempMeaasge {
    private Long qq;
    private Long group;
    private List<MessageChain> messageChain;
}
