package com.ws.app.client.handler;

import com.alibaba.fastjson.JSONObject;
import com.ws.app.entity.ContractNO;
import com.ws.app.init.AppCache;
import com.ws.app.service.impl.APPServiceImpl;
import com.ws.app.utils.EWSpringContextUtil;
import com.ws.app.utils.Tools;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Sharable
public class TestHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        AppCache.times = System.currentTimeMillis();

        System.out.println(msg.toString(CharsetUtil.UTF_8));
        JSONObject param = JSONObject.parseObject(msg.toString(CharsetUtil.UTF_8));
        APPServiceImpl asi = (APPServiceImpl) EWSpringContextUtil.getBean("aPPServiceImpl");

        switch (param.getString("servCode")) {
            case ContractNO.HEARTBEAT_CODE:
                ctx.writeAndFlush(Tools.rtnBytebuf(param.toJSONString()));
                break;
            case ContractNO.LOGIN_CODE:
                if (asi.login(param.getString("userNo")) == null) {
                    param.put("rtnCode", "1002");
                    param.put("rtnData", "");
                } else {
                    param.put("rtnCode", "9999");
                    param.put("rtnData", asi.login(param.getString("userNo")));
                }
                break;
            case ContractNO.QUERY_CODE:
                List<Map<String, Object>> result = asi.query(param.getString("consNo"));
                if (result == null) {
                    param.put("rtnCode", "1002");
                    param.put("rtnData", "");
                } else {
                    param.put("rtnCode", "9999");
                    param.put("rtnData", result);
                }
                break;
            case ContractNO.DOWNLOAD_CODE:
                List<Map<String, Object>> download_result = asi.downLoad(param.getString("userNo"));
                if (download_result == null) {
                    param.put("rtnCode", "1002");
                    param.put("rtnData", "");
                } else {
                    param.put("rtnCode", "9999");
                    param.put("rtnData", download_result);
                }
                break;
            case ContractNO.UPLOAD_CODE:
                Map<String, Object> param_map = new HashMap<>();
                param_map.put("userNo", param.getString("userNo"));
                param_map.put("consNo", param.getString("consNo"));
                param_map.put("endCode", param.getString("endCode"));
                System.out.println("------------上传指数参数-----------");
                System.out.println(param_map.toString());
                JSONObject upload_result = asi.upLoad(param_map);
                if (upload_result == null) {
                    param.put("rtnCode", "1002");
                    param.put("rtnData", "");
                } else {
                    param.put("rtnCode", "9999");
                    param.put("rtnData", upload_result);
                }
                break;

            default:
                ctx.writeAndFlush(Tools.rtnBytebuf(param.toJSONString()));
        }
        ctx.writeAndFlush(Tools.rtnBytebuf(param.toJSONString()));
    }
}
