package org.jeecg.modules.bot.ws.pojo.type;

import lombok.Data;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/9 22:28
 */
@Data
public class Sender {
    private Long id;
    private String nickname;
    private String remark;
    private String memberName;
    private String specialTitle;
    private String permission;
    private Long joinTimestamp;
    private Long lastSpeakTimestamp;
    private Long muteTimeRemaining;
    private String platform;
    private GroupType group;
}
