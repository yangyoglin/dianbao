package com.dianbao.util;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.ToString;

/**
 * 响应消息
 * @author pasken
 * @date on 2019/3/7.
 */
@ToString
public class CommonUtils implements Serializable {

    public static Map<String,String> result(String description){
        Map<String,String> map = new HashMap<>(1);
        map.put("result", description);
        return map;
    }

    public static Map<String,Object> result(int code,String message){
        Map<String,Object> map = new HashMap<>();
        map.put("code", code);
        map.put("message", message);
        return map;
    }


}