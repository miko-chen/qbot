package org.jeecg.modules.bot.ws.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.jeecg.modules.bot.ws.common.entity.CommonRequest;
import org.jeecg.modules.bot.ws.service.MessageSendService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/12 22:23
 */
@RestController
@RequestMapping("/ws")
@Slf4j
@Api(tags="机器人websocket操作")
public class WsController {
    @Resource(name="botWebSocket")
    private WebSocketClient botWebSocket;
    @Resource
    private MessageSendService messageSendService;

    @ApiOperation("重连ws")
    @RequestMapping("/reconnect")
    public String reconnect(){
        try {
            if(botWebSocket.reconnectBlocking())return "重连成功";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "重连失败";
    }

    @ApiOperation("关于机器人")
    @RequestMapping("/about")
    public void about(){
        botWebSocket.send(CommonRequest.getInstance("about",null).toString());
    }
}
