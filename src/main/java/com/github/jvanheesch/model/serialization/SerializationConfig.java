//package com.github.jvanheesch.model.serialization;
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.JsonSerializer;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
//import com.fasterxml.jackson.databind.module.SimpleDeserializers;
//import com.fasterxml.jackson.databind.module.SimpleModule;
//import com.fasterxml.jackson.databind.module.SimpleSerializers;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.IOException;
//
//@Configuration
//public class SerializationConfig {
//    /**
//     * https://github.com/spring-projects/spring-data-rest/blob/master/src/main/asciidoc/custom-jackson-deserialization.adoc
//     */
//    @Bean
//    public SimpleModule customSerializationOptionsModule(VerdictRepository verdictRepository) {
//        return new CustomSerializationOptionsModule(verdictRepository);
//    }
//
//    public static class CustomSerializationOptionsModule extends SimpleModule {
//        private final VerdictRepository verdictRepository;
//
//        private CustomSerializationOptionsModule(VerdictRepository verdictRepository) {
//            this.verdictRepository = verdictRepository;
//        }
//
//        @Override
//        public void setupModule(SetupContext context) {
//            SimpleSerializers serializers = new SimpleSerializers();
//            SimpleDeserializers deserializers = new SimpleDeserializers();
//
//            serializers.addSerializer(VerdictRecord.class, new VerdictRecordSerializer());
//            deserializers.addDeserializer(VerdictRecord.class, new VerdictRecordDeserializer(verdictRepository));
//            serializers.addSerializer(Person.class, new PersonSerializer());
//
//            context.addSerializers(serializers);
//            context.addDeserializers(deserializers);
//        }
//
//        public static class VerdictRecordSerializer extends JsonSerializer<VerdictRecord> {
//            @Override
//            public void serialize(VerdictRecord verdictRecord, JsonGenerator gen, SerializerProvider serializers) throws IOException {
//                gen.writeFieldName("lola");
//                if (verdictRecord.getVerdict() != null) {
//                    gen.writeNumber(verdictRecord.getVerdict().getId());
//                } else {
//                    gen.writeNull();
//                }
//            }
//        }
//
//        public static class PersonSerializer extends JsonSerializer<Person> {
//            @Override
//            public void serialize(Person person, JsonGenerator gen, SerializerProvider serializers) throws IOException {
//                gen.writeFieldName("namexx");
//                if (person.getName() != null) {
//                    gen.writeString(person.getName());
//                } else {
//                    gen.writeNull();
//                }
//            }
//        }
//
//        public static class VerdictRecordDeserializer extends StdDeserializer<VerdictRecord> {
//            private final VerdictRepository verdictRepository;
//
//            public VerdictRecordDeserializer(VerdictRepository verdictRepository) {
//                super(Verdict.class);
//
//                this.verdictRepository = verdictRepository;
//            }
//
//            @Override
//            public VerdictRecord deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
//                JsonNode jsonNode = jp.getCodec().readTree(jp);
//
//                return new VerdictRecord(this.verdictRepository.findById(jsonNode.longValue()).get());
//            }
//
//            @Override
//            public VerdictRecord getNullValue(DeserializationContext ctxt) {
//                return new VerdictRecord();
//            }
//        }
//    }
//}
