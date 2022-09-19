package org.jeecg.modules.bot.common.entity;

import lombok.Data;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/9 11:38
 */
@Data
public class NudgeMessage {
    private long target;
    private long subject;
    private Kind kind;

    enum Kind{
        Friend,Group,Stranger
    }

}
