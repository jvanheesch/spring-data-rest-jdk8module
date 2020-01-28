package com.github.jvanheesch.spring.data.rest.jdk8module.scratch;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SomeEntitySerializationTest {
    @Autowired
    private RepositoryRestMvcConfiguration repositoryRestMvcConfiguration;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void initializeObjectMapper() {
        this.objectMapper = repositoryRestMvcConfiguration.halObjectMapper();
    }

    @Test
    void givenAStringMyOptionalOwner_whenSerializing_thenEmptyOptionalLeadsToNullAndNullLeadsToAbsentProperty() throws Exception {
        SomeEntity original = new SomeEntity();

        original.setSomeEmbeddable1(new SomeEmbeddable("abc"));
        original.setSomeEmbeddable2(new SomeEmbeddable());
        original.setSomeEmbeddable2(null);

        String json = serialize(objectMapper, original);

        System.out.println(json);

        SomeEntity deser = objectMapper.readValue(json, SomeEntity.class);

        System.out.println("test");
    }

    private String readJsonFromClassPath(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new ClassPathResource(path).getInputStream()))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }

//    /**
//     * Based on {@link org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter#writeInternal(Object, Type, HttpOutputMessage)}.
//     */
    private String serialize(ObjectMapper objectMapper, Object object) throws IOException {
        JsonEncoding encoding = JsonEncoding.UTF8;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JsonGenerator generator = objectMapper.getFactory().createGenerator(baos, encoding);

        ObjectWriter objectWriter = objectMapper.writer();
        objectWriter.writeValue(generator, object);

        byte[] bytes = baos.toByteArray();
        return new String(bytes);
    }
}
