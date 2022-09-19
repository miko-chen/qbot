package org.jeecg.modules.bot.common.response;

import lombok.Data;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/13 11:28
 */
@Data
public class Response<T> {
    private int code;
    private String msg;
    private boolean success;
    private T data;

    public static Response success(){
        Response response = new Response();
        response.setCode(200);
        response.setSuccess(true);
        response.setMsg("请求成功");
        return response;
    }
}
