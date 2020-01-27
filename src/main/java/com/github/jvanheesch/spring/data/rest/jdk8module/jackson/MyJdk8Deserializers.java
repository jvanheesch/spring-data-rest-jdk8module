package com.github.jvanheesch.spring.data.rest.jdk8module.jackson;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ReferenceType;

import java.util.Optional;

public class MyJdk8Deserializers
    extends Deserializers.Base
    implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

    @Override // since 2.7
    public JsonDeserializer<?> findReferenceDeserializer(ReferenceType refType,
            DeserializationConfig config, BeanDescription beanDesc,
            TypeDeserializer contentTypeDeserializer, JsonDeserializer<?> contentDeserializer)
    {
        if (refType.hasRawClass(Optional.class)) {
            return new MyOptionalDeserializer(refType, null, contentTypeDeserializer,contentDeserializer);
        }
        // 21-Oct-2015, tatu: Should probably consider possibility of custom deserializer being
        //    added to property; if so, `contentDeserializer` would not be null.
        //    Room for future improvement
        
//        if (refType.hasRawClass(OptionalInt.class)) {
//            return OptionalIntDeserializer.INSTANCE;
//        }
//        if (refType.hasRawClass(OptionalLong.class)) {
//            return OptionalLongDeserializer.INSTANCE;
//        }
//        if (refType.hasRawClass(OptionalDouble.class)) {
//            return OptionalDoubleDeserializer.INSTANCE;
//        }
        return null;
    }
}
