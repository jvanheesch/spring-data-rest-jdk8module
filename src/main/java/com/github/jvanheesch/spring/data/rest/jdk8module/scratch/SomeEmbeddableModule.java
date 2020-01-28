package com.github.jvanheesch.spring.data.rest.jdk8module.scratch;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.module.SimpleSerializers;

import java.io.IOException;

public class SomeEmbeddableModule extends SimpleModule {
    public SomeEmbeddableModule() {
    }

    @Override
    public void setupModule(SetupContext context) {
        SimpleSerializers serializers = new SimpleSerializers();
        serializers.addSerializer(SomeEmbeddable.class, new SomeEmbeddableSerializer());
        context.addSerializers(serializers);

//        SimpleDeserializers deserializers = new SimpleDeserializers();
//        deserializers.addDeserializer(SomeEmbeddable.class, new SomeEmbeddableDeserializer());
//        context.addDeserializers(deserializers);
    }

    public static class SomeEmbeddableSerializer extends JsonSerializer<SomeEmbeddable> {
        @Override
        public void serialize(SomeEmbeddable value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeStartObject();
            gen.writeStringField("string", value.getString());
            gen.writeEndObject();
        }
    }

    public static class SomeEmbeddableDeserializer extends StdDeserializer<SomeEmbeddable> {
        public SomeEmbeddableDeserializer() {
            super(SomeEmbeddable.class);
        }

        @Override
        public SomeEmbeddable deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode node = jp.getCodec().readTree(jp);
            Long verdictId = node.longValue();

            return null;
//            return verdictRecord;
        }
    }
}
