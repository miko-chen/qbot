package org.jeecg.modules.bot.common.entity;

import lombok.Data;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/9 11:53
 */
@Data
public class RoamingMessages {
    private long timeStart;
    private long timeEnd;
    private long target;
}
