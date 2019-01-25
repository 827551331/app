package com.ws.app.utils;

import com.ws.app.entity.RequestPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * 报文编解码工具
 *
 * @author wang_yw
 */
public class PacketCodeC {

    /**
     * 编码
     *
     * @param packet
     * @return
     */
    public static ByteBuf encode(RequestPacket packet) {

        // 1. 创建 ByteBuf 对象
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        // 2. 序列化 Java 对象
        byte[] bytes = SerializeUtil.serialize(packet);

        // 3. 实际编码过程
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    /**
     * 解码
     *
     * @param byteBuf
     * @return
     */
    public RequestPacket decode(ByteBuf byteBuf) {

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        return (RequestPacket)SerializeUtil.unserialize(bytes);
    }

}
