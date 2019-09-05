package com.ws.app.utils;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.security.MessageDigest;
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

    public String encoder(String coder) {
        StringBuilder output = new StringBuilder(1 << 5);
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(coder.getBytes("utf-8"));
            byte[] b = md.digest();
            for (int i = 0; i < b.length; i++) {
                String temp = Integer.toHexString(b[i] & 0xff);
                if (temp.length() < 2) {
                    output.append("0");
                }
                output.append(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {


        String coder = "{\"customer\":\"CESHI\",\"systemCode\":\"cx.100.001\",\"queryDate1\":\"2018-08-01 00:00:00\",\"queryDate2\":\"2018-08-30 23:59:59\",\"queryType\":\"0\",\"meterType\":\"wlwsb\",\"list\":[]}";

        StringBuilder output = new StringBuilder(1 << 5);
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(coder.getBytes("utf-8"));
            byte[] b = md.digest();
            for (int i = 0; i < b.length; i++) {
                String temp = Integer.toHexString(b[i] & 0xff);
                if (temp.length() < 2) {
                    output.append("0");
                }
                output.append(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(output.toString());
    }
}
