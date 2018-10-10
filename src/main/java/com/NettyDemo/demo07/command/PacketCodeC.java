package com.NettyDemo.demo07.command;

import com.NettyDemo.demo07.serialize.Serializer;
import com.NettyDemo.demo07.serialize.SerializerAlgorithm;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZiJie.Yip
 * @Description: 编码
 * @date: 2018/10/9 17:02
 */
public class PacketCodeC {
    /**
     * 通信协议
     *  魔数   版本号  序列化算法  指令  数据长度   数据
     * 4字节   1字节     1字节    1字节   4字节     N字节
     */


    /**
     * 魔数
     */
    private static final int MAGIC_NUMBER = 0x12345678;
    private static final Map<Byte,Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    public static final PacketCodeC INSTANCE = new PacketCodeC();

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);

        serializerMap = new HashMap<>();
        serializerMap.put(SerializerAlgorithm.JSON, Serializer.DEFAULT);
    }

    /**
     * 封装成二进制数据
     * @param packet
     * @return
     */
    public ByteBuf encode(ByteBuf byteBuf, Packet packet){
        //2. 序列化 Java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        //3. 实际编码过程
        //3-1. 魔数 4字节
        byteBuf.writeInt(MAGIC_NUMBER);
        //3-2. 版本号 1字节
        byteBuf.writeByte(packet.getVersion());
        //3-3. 序列化算法
        byteBuf.writeByte(SerializerAlgorithm.JSON);
        //3-4. 指令
        byteBuf.writeByte(packet.getCommand());
        //3-4. 数据长度
        byteBuf.writeInt(bytes.length);
        //3-5. 数据
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    /**
     * 解析Java对象
     * @param byteBuf
     * @return
     */
    public Packet decode(ByteBuf byteBuf){
        //跳过 magic number 后续处理
        byteBuf.skipBytes(4);

        //跳过版本号
        byteBuf.skipBytes(1);

        //序列化算法标识
        byte serializerAlgorithm = byteBuf.readByte();

        //指令
        byte command = byteBuf.readByte();

        //数据包长度
        int length = byteBuf.readInt();

        //数据
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = packetTypeMap.get(command);
        Serializer serializer = serializerMap.get(serializerAlgorithm);

        if(requestType != null && serializer != null){
            Packet packet = serializer.deserialize(requestType, bytes);
            return packet;
        }
        return null;
    }




}
