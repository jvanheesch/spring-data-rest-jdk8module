package com.github.jvanheesch.spring.data.rest.jdk8module;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanSerializer;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.fasterxml.jackson.databind.util.NameTransformer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SpringRestConfigurer implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.disableDefaultExposure();
        config.withEntityLookup().forValueRepository(VerdictRepository.class, Verdict::getId, VerdictRepository::findById);
    }

    /**
     * https://github.com/spring-projects/spring-data-rest/blob/master/src/main/asciidoc/custom-jackson-deserialization.adoc
     * https://stackoverflow.com/a/29047973/1939921
     */
    @Bean
    public SimpleModule customSerializationOptionsModule(VerdictRepository verdictRepository) {
        return new SimpleModule() {
            @Override
            public void setupModule(SetupContext context) {

                context.addBeanSerializerModifier(new BeanSerializerModifier() {
                    @Override
                    public JsonSerializer<?> modifySerializer(SerializationConfig config, BeanDescription beanDesc, JsonSerializer<?> serializer) {
                        System.out.println(beanDesc.getBeanClass());

                        if (VerdictRecord.class.isAssignableFrom(beanDesc.getBeanClass())) {
                            return new UnwrappingOrderSerializer((BeanSerializerBase) serializer, NameTransformer.NOP);
                        }
                        return serializer;
                    }
                });
            }
        };
    }

    public static class UnwrappingOrderSerializer extends UnwrappingBeanSerializer {
        public UnwrappingOrderSerializer(BeanSerializerBase src, NameTransformer transformer) {
            super(src, transformer);
        }

        @Override
        public JsonSerializer<Object> unwrappingSerializer(NameTransformer transformer) {
            return new UnwrappingOrderSerializer(this, transformer);
        }

        @Override
        protected void serializeFields(Object bean, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
            VerdictRecord order = (VerdictRecord) bean;
            jgen.writeStringField("paid", "test");
        }

        @Override
        public boolean isUnwrappingSerializer() {
            return true;
        }
    }
}
