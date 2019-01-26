package com.ws.app.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.util.UUID;

/**
 * 工具类
 *
 * @author wang_yw
 */
public class Tools {

    /**
     * 获取随机数
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 加工返回ByteBuf
     *
     * @param param
     * @return
     */
    public static ByteBuf rtnBytebuf(String param) {
        return Unpooled.copiedBuffer(param + System.getProperty("line.separator"), CharsetUtil.UTF_8);
    }
}
