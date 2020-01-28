package com.github.jvanheesch.spring.data.rest.jdk8module;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import com.fasterxml.jackson.databind.util.NameTransformer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

// quickly enable auto-configuration, component-scan etc
@SpringBootApplication
public class Application {
    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    public static class CustomSerializationOptionsModule extends SimpleModule {
        @Override
        public void setupModule(SetupContext context) {
            SimpleSerializers serializers = new SimpleSerializers();
//            SimpleDeserializers deserializers = new SimpleDeserializers();

            serializers.addSerializer(StringContainer.class, new StringContainerSerializer());
//            deserializers.addDeserializer(StringContainer.class, new StringContainerDeserializer());

            context.addSerializers(serializers);
//            context.addDeserializers(deserializers);
        }

        // TODO_JORIS: fix @JsonUnwrapped shit.
        public static class StringContainerSerializer extends JsonSerializer<StringContainer> {
            @Override
            public void serialize(StringContainer container, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                String value = container.getValue();
                if (value != null) {
                    gen.writeString(value);
                } else {
                    gen.writeNull();
                }
            }

            @Override
            public boolean isUnwrappingSerializer() {
                return super.isUnwrappingSerializer();
            }

            // we komen hier 2x in, lijkt NIET owv 2x @JsonUnwrapped ...
            @Override
            public JsonSerializer<StringContainer> unwrappingSerializer(NameTransformer unwrapper) {
                return super.unwrappingSerializer(unwrapper);
            }
        }

//        public static class StringContainerDeserializer extends StdDeserializer<StringContainer> {
//            public StringContainerDeserializer() {
//                super(StringContainer.class);
//            }
//
//            @Override
//            public StringContainer deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
//                JsonNode node = jp.getCodec().readTree(jp);
//                Long verdictId = node.longValue();
//
//                Verdict verdict = ResourceUtil.requirePresent(this.verdictRepository.findById(verdictId));
//                StringContainer verdictRecord = new StringContainer();
//                verdictRecord.setVerdict(verdict);
//                return verdictRecord;
//            }
//        }
    }

}
