package com.ws.app.client.handler;

import com.alibaba.fastjson.JSONObject;
import com.ws.app.entity.ContractNO;
import com.ws.app.service.impl.APPServiceImpl;
import com.ws.app.utils.EWSpringContextUtil;
import com.ws.app.utils.Tools;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;


@Sharable
public class TestHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println(msg.toString(CharsetUtil.UTF_8));
        JSONObject param = JSONObject.parseObject(msg.toString(CharsetUtil.UTF_8));

        APPServiceImpl asi = (APPServiceImpl)EWSpringContextUtil.getBean("aPPServiceImpl");

        if (param.getString("servCode").equals(ContractNO.LOGIN_CODE)) {
            param.put("rtnData",asi.login(param.getString("userNo")));
        }

        ctx.writeAndFlush(Tools.rtnBytebuf(param.toJSONString()));
    }

}
