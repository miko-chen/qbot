package org.jeecg.modules.bot.common.entity;

import lombok.Data;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/9 11:52
 */
@Data
public class RecallMessage {
    private int messageId;
    private long target;
}
