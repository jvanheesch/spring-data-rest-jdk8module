package com.github.jvanheesch.spring.data.rest.jdk8module.jackson;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.std.ReferenceTypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;

import java.util.Optional;

final class MyOptionalDeserializer
    extends ReferenceTypeDeserializer<Optional<?>>
{
    private static final long serialVersionUID = 1L;

    /*
    /**********************************************************
    /* Life-cycle
    /**********************************************************
     */

    /**
     * @since 2.9
     */
    public MyOptionalDeserializer(JavaType fullType, ValueInstantiator inst,
                                  TypeDeserializer typeDeser, JsonDeserializer<?> deser)
    {
        super(fullType, inst, typeDeser, deser);
    }

    /*
    /**********************************************************
    /* Abstract method implementations
    /**********************************************************
     */

    @Override
    public MyOptionalDeserializer withResolved(TypeDeserializer typeDeser, JsonDeserializer<?> valueDeser) {
        return new MyOptionalDeserializer(_fullType, _valueInstantiator,
                typeDeser, valueDeser);
    }

    @Override
    public Optional<?> getNullValue(DeserializationContext ctxt) throws JsonMappingException {
        // 07-May-2019, tatu: [databind#2303], needed for nested ReferenceTypes
        return Optional.ofNullable(_valueDeserializer.getNullValue(ctxt));
    }

    @Override
    public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
        // 07-May-2019, tatu: I _think_ this needs to align with "null value" and
        //    not necessarily with empty value of contents? (used to just do "absent"
        //    so either way this seems to me like an improvement)
        return getNullValue(ctxt);
    }

    @Override
    public Optional<?> referenceValue(Object contents) {
        return Optional.ofNullable(contents);
    }

    @Override
    public Object getReferenced(Optional<?> reference) {
        return reference.get();
    }

    @Override // since 2.9
    public Optional<?> updateReference(Optional<?> reference, Object contents) {
        return Optional.ofNullable(contents);
    }

    // Default ought to be fine:
//    public Boolean supportsUpdate(DeserializationConfig config) { }

}
