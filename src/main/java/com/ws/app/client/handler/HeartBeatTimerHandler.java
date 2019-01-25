package com.ws.app.client.handler;

import com.ws.app.entity.RequestPacket;
import com.ws.app.utils.PacketCodeC;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {

    private static final int HEARTBEAT_INTERVAL = 5;
    private static final String SERVCODE_HEARTBEAT = "001";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        scheduleSendHeartBeat(ctx);

        super.channelActive(ctx);
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        ctx.executor().schedule(() -> {

            RequestPacket rp = new RequestPacket();
            rp.setServCode(SERVCODE_HEARTBEAT);
            if (ctx.channel().isActive()) {
                ctx.writeAndFlush(PacketCodeC.encode(rp));
                scheduleSendHeartBeat(ctx);
            }

        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }
}
