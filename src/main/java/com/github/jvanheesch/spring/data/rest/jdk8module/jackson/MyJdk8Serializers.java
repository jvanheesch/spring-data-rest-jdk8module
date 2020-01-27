package com.github.jvanheesch.spring.data.rest.jdk8module.jackson;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.type.ReferenceType;

import com.github.jvanheesch.spring.data.rest.jdk8module.MyOptional;

public class MyJdk8Serializers extends Serializers.Base
    implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

    @Override
    public JsonSerializer<?> findReferenceSerializer(SerializationConfig config,
            ReferenceType refType, BeanDescription beanDesc,
            TypeSerializer contentTypeSerializer, JsonSerializer<Object> contentValueSerializer)
    {
        final Class<?> raw = refType.getRawClass();
        if (MyOptional.class.isAssignableFrom(raw)) {
            boolean staticTyping = (contentTypeSerializer == null)
                    && config.isEnabled(MapperFeature.USE_STATIC_TYPING);
            return new MyOptionalSerializer(refType, staticTyping,
                    contentTypeSerializer, contentValueSerializer);
        }
//        if (OptionalInt.class.isAssignableFrom(raw)) {
//            return OptionalIntSerializer.INSTANCE;
//        }
//        if (OptionalLong.class.isAssignableFrom(raw)) {
//            return OptionalLongSerializer.INSTANCE;
//        }
//        if (OptionalDouble.class.isAssignableFrom(raw)) {
//            return OptionalDoubleSerializer.INSTANCE;
//        }
        return null;
    }

//    @Override
//    public JsonSerializer<?> findSerializer(SerializationConfig config, JavaType type,
//            BeanDescription beanDesc)
//    {
//        Class<?> raw = type.getRawClass();
//        if (LongStream.class.isAssignableFrom(raw)) {
//            return LongStreamSerializer.INSTANCE;
//        }
//        if (IntStream.class.isAssignableFrom(raw)) {
//            return IntStreamSerializer.INSTANCE;
//        }
//        if (DoubleStream.class.isAssignableFrom(raw)) {
//            return DoubleStreamSerializer.INSTANCE;
//        }
//        if (Stream.class.isAssignableFrom(raw)) {
//            JavaType[] params = config.getTypeFactory().findTypeParameters(type, Stream.class);
//            JavaType vt = (params == null || params.length != 1) ? TypeFactory.unknownType() : params[0];
//            return new StreamSerializer(config.getTypeFactory().constructParametricType(Stream.class, vt), vt);
//        }
//        return null;
//    }
}
