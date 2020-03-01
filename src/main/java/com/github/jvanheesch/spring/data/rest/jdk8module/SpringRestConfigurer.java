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
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class SpringRestConfigurer implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.disableDefaultExposure();
        config.withEntityLookup().forValueRepository(VerdictRepository.class, Verdict::getId, VerdictRepository::findById);
    }

    /**
     * https://github.com/spring-projects/spring-data-rest/blob/master/src/main/asciidoc/custom-jackson-deserialization.adoc
     */
//    @Bean
//    public SimpleModule customSerializationOptionsModule(VerdictRepository verdictRepository) {
//        return new CustomSerializationOptionsModule(verdictRepository);
//    }

    public static class CustomSerializationOptionsModule extends SimpleModule {
        private final VerdictRepository verdictRepository;

        private CustomSerializationOptionsModule(VerdictRepository verdictRepository) {
            this.verdictRepository = verdictRepository;
        }

        @Override
        public void setupModule(SetupContext context) {
            SimpleSerializers serializers = new SimpleSerializers();
            SimpleDeserializers deserializers = new SimpleDeserializers();

            serializers.addSerializer(OriginWoodEvaluation.class, new OriginWoodEvaluationSerializer());
            deserializers.addDeserializer(OriginWoodEvaluation.class, new OriginWoodEvaluationDeserializer(verdictRepository));

            context.addSerializers(serializers);
            context.addDeserializers(deserializers);
        }

        public static class OriginWoodEvaluationSerializer extends JsonSerializer<OriginWoodEvaluation> {
            @Override
            public void serialize(OriginWoodEvaluation originWoodEvaluation, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeStartObject();
                if (originWoodEvaluation.getSupplierLocatedInEurope() != null) {
                    gen.writeFieldName("supplierLocatedInEurope");
                    if (originWoodEvaluation.getSupplierLocatedInEurope().getVerdict() == null) {
                        gen.writeNull();
                    } else {
                        gen.writeNumber(originWoodEvaluation.getSupplierLocatedInEurope().getVerdict().getId());
                    }
                }
                if (originWoodEvaluation.getFlegtLicense() != null) {
                    gen.writeFieldName("flegtLicense");
                    if (originWoodEvaluation.getFlegtLicense().getVerdict() == null) {
                        gen.writeNull();
                    } else {
                        gen.writeNumber(originWoodEvaluation.getFlegtLicense().getVerdict().getId());
                    }
                }
                if (originWoodEvaluation.getCitesLicense() != null) {
                    gen.writeFieldName("citesLicense");
                    if (originWoodEvaluation.getCitesLicense().getVerdict() == null) {
                        gen.writeNull();
                    } else {
                        gen.writeNumber(originWoodEvaluation.getCitesLicense().getVerdict().getId());
                    }
                }
                if (originWoodEvaluation.getFromIndonesia() != null) {
                    gen.writeFieldName("fromIndonesia");
                    if (originWoodEvaluation.getFromIndonesia().getVerdict() == null) {
                        gen.writeNull();
                    } else {
                        gen.writeNumber(originWoodEvaluation.getFromIndonesia().getVerdict().getId());
                    }
                }
                gen.writeEndObject();
            }
        }

        public static class OriginWoodEvaluationDeserializer extends StdDeserializer<OriginWoodEvaluation> {
            private final VerdictRepository verdictRepository;

            public OriginWoodEvaluationDeserializer(VerdictRepository verdictRepository) {
                super(Verdict.class);

                this.verdictRepository = verdictRepository;
            }

            @Override
            public OriginWoodEvaluation deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
                JsonNode jsonNode = jp.getCodec().readTree(jp);

                Set<String> propertiesPresentInJson = StreamSupport.stream(((Iterable<String>) jsonNode::fieldNames).spliterator(), false)
                        .collect(Collectors.toSet());

                OriginWoodEvaluation originWoodEvaluation = new OriginWoodEvaluation();

                if (propertiesPresentInJson.contains("supplierLocatedInEurope")) {
                    JsonNode supplierLocatedInEurope = jsonNode.get("supplierLocatedInEurope");
                    originWoodEvaluation.setSupplierLocatedInEurope(
                            new VerdictRecord(supplierLocatedInEurope.isNull()
                                    ? null :
                                    this.verdictRepository.findById(supplierLocatedInEurope.longValue()).get()
                            )
                    );
                }
                if (propertiesPresentInJson.contains("flegtLicense")) {
                    JsonNode flegtLicense = jsonNode.get("flegtLicense");
                    originWoodEvaluation.setFlegtLicense(
                            new VerdictRecord(flegtLicense.isNull()
                                    ? null :
                                    this.verdictRepository.findById(flegtLicense.longValue()).get()
                            )
                    );
                }
                if (propertiesPresentInJson.contains("citesLicense")) {
                    JsonNode citesLicense = jsonNode.get("citesLicense");
                    originWoodEvaluation.setCitesLicense(
                            new VerdictRecord(citesLicense.isNull()
                                    ? null :
                                    this.verdictRepository.findById(citesLicense.longValue()).get()
                            )
                    );
                }
                if (propertiesPresentInJson.contains("fromIndonesia")) {
                    JsonNode fromIndonesia = jsonNode.get("fromIndonesia");
                    originWoodEvaluation.setFromIndonesia(
                            new VerdictRecord(fromIndonesia.isNull()
                                    ? null :
                                    this.verdictRepository.findById(fromIndonesia.longValue()).get()
                            )
                    );
                }
                return originWoodEvaluation;
            }
        }
    }
}
