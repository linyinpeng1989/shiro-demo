package com.github.linyp.shiro.chapter10;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Yinpeng Lin
 * @date 2017/11/24
 * @desc 序列化工具类(使用ProtoStuff实现)
 */
public class SerializableUtils {
    /**
     * schema缓存
     */
    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<>();

    private static Objenesis objenesis = new ObjenesisStd(true);

    /**
     * 获取Schema对象
     * @param cls
     * @param <T>
     * @return
     */
    private static <T> Schema<T> getSchema(Class<T> cls) {
        Schema<T> schema = (Schema<T>) cachedSchema.get(cls);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(cls);
            if (schema != null) {
                cachedSchema.put(cls, schema);
            }
        }
        return schema;
    }

    /**
     * 序列化
     * @param obj   序列化目标对象
     * @param <T>
     * @return
     */
    public static <T> byte[] serialize(T obj) {
        Class<T> cls = (Class<T>) obj.getClass();
        Schema<T> schema = getSchema(cls);
        return ProtostuffIOUtil.toByteArray(obj, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
    }

    /**
     * 序列化
     * @param obj   序列化目标对象
     * @return
     */
    public static <T> String serializeToString(T obj) {
        try {
            Class<T> cls = (Class<T>) obj.getClass();
            Schema<T> schema = getSchema(cls);
            byte[] bytes = ProtostuffIOUtil.toByteArray(obj, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反序列化
     * @param data  字节数组
     * @param cls   对象类型
     * @return
     */
    public static <T> T deserialize(byte[] data, Class<T> cls) {
        T obj = objenesis.newInstance(cls);
        Schema<T> schema = getSchema(cls);
        ProtostuffIOUtil.mergeFrom(data, obj, schema);
        return obj;
    }

    /**
     * 反序列化
     * @param data  字符串
     * @param cls   对象类型
     * @return
     */
    public static <T> T deserialize(String data, Class<T> cls) {
        try {
            byte[] bytes = data.getBytes("UTF-8");
            return deserialize(bytes, cls);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
