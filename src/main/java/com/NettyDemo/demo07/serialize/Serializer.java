package com.NettyDemo.demo07.serialize;

/**
 * @Author: ZiJie.Yip
 * @Description: 通信序列化接口
 * @date: 2018/10/9 16:53
 */
public interface Serializer {
    /**
     * 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * java对象转二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转java对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
    /**
     * 默认使用Json解析
     */
    Serializer DEFAULT = new JSONSerializer();
}
