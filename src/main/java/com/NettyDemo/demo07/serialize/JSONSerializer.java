package com.NettyDemo.demo07.serialize;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: ZiJie.Yip
 * @Description: JSON序列化工具类
 * @date: 2018/10/9 16:58
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSONObject.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSONObject.parseObject(bytes,clazz);
    }
}
