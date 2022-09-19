package org.jeecg.modules.bot.ws.conf;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.jeecg.modules.bot.ws.handle.BotMessageHandle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;



import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/12 21:13
 */
@Slf4j
@Configuration
@Component
public class WebSocketConnect {
    @Value("${qqbot.account}")
    private String account;
    @Value("${qqbot.verify_key}")
    private String verifyKey;

    @Resource
    private BotMessageHandle botMessageHandle;

    /**
     * 获取一个已连接的WebSocketClient对象
     */
    @Bean("botWebSocket")
    public WebSocketClient webSocket() {
        log.info("机器人账号: {} , 连接密钥 : {}",account,verifyKey);
        String url = "ws://localhost:8101/all?verifyKey="+verifyKey+"&qq="+account;
        try {
            URI uri = new URI(url);
            WebSocketClient webSocket = new WebSocketClient(uri) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {

                }

                @Override
                public void onMessage(String s) {
                    JSONObject jsonObject = JSONObject.parseObject(s);
                    JSONObject data = jsonObject.getJSONObject("data");
                    data.put("syncId",jsonObject.getString("syncId"));
                    botMessageHandle.distribute(data);
                }

                @Override
                public void onClose(int i, String s, boolean b) {

                }

                @Override
                public void onError(Exception e) {

                }
            };
            boolean isSuccess = webSocket.connectBlocking(2000, TimeUnit.MILLISECONDS);
            if (isSuccess) {
                log.info("连接成功");
            } else {
                log.error("websocket连接mirai失败");
            }
            return webSocket;
        } catch (URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
