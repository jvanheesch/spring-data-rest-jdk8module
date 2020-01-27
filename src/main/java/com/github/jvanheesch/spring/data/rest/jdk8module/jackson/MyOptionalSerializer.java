package com.github.jvanheesch.spring.data.rest.jdk8module.jackson;

import java.util.Optional;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.ReferenceTypeSerializer;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.util.NameTransformer;

public class MyOptionalSerializer
    extends ReferenceTypeSerializer<Optional<?>> // since 2.9
{
    private static final long serialVersionUID = 1L;

    /*
    /**********************************************************
    /* Constructors, factory methods
    /**********************************************************
     */

    protected MyOptionalSerializer(ReferenceType fullType, boolean staticTyping,
                                   TypeSerializer vts, JsonSerializer<Object> ser)
    {
        super(fullType, staticTyping, vts, ser);
    }

    protected MyOptionalSerializer(MyOptionalSerializer base, BeanProperty property,
                                   TypeSerializer vts, JsonSerializer<?> valueSer, NameTransformer unwrapper,
                                   Object suppressableValue, boolean suppressNulls)
    {
        super(base, property, vts, valueSer, unwrapper,
                suppressableValue, suppressNulls);
    }

    @Override
    protected ReferenceTypeSerializer<Optional<?>> withResolved(BeanProperty prop,
            TypeSerializer vts, JsonSerializer<?> valueSer,
            NameTransformer unwrapper)
    {
        return new MyOptionalSerializer(this, prop, vts, valueSer, unwrapper,
                _suppressableValue, _suppressNulls);
    }

    @Override
    public ReferenceTypeSerializer<Optional<?>> withContentInclusion(Object suppressableValue,
            boolean suppressNulls)
    {
        return new MyOptionalSerializer(this, _property, _valueTypeSerializer,
                _valueSerializer, _unwrapper,
                suppressableValue, suppressNulls);
    }

    /*
    /**********************************************************
    /* Abstract method impls
    /**********************************************************
     */

    @Override
    protected boolean _isValuePresent(Optional<?> value) {
        return value.isPresent();
    }

    @Override
    protected Object _getReferenced(Optional<?> value) {
        return value.get();
    }

    @Override
    protected Object _getReferencedIfPresent(Optional<?> value) {
        return value.isPresent() ? value.get() : null;
    }
}
