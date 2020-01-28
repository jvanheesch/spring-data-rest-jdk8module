package com.github.jvanheesch.spring.data.rest.jdk8module;

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
import org.springframework.http.HttpOutputMessage;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SerializationTest {
    @Autowired
    private RepositoryRestMvcConfiguration repositoryRestMvcConfiguration;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void initializeObjectMapper() {
        this.objectMapper = repositoryRestMvcConfiguration.halObjectMapper();
    }

    @Test
    void givenAStringMyOptionalOwner_whenSerializing_thenEmptyOptionalLeadsToNullAndNullLeadsToAbsentProperty() throws Exception {
        StringMyOptionalOwner original = new StringMyOptionalOwner();

        original.setStringMyOptional1(MyOptional.of("abc"));
        original.setStringMyOptional2(MyOptional.empty());
        original.setStringContainer1(new StringContainer("abc"));
        original.setStringContainer2(new StringContainer("def"));
        original.setStringContainer3(null);
        original.setStringContainer4(null);
        // variables op type Optional<X> should never be null.
        // this code is only here to illustrate jackson's serialization behavior
        original.setStringMyOptional3(null);

        String json = serialize(objectMapper, original);

        System.out.println(json);
//        JSONAssert.assertEquals(
//                readJsonFromClassPath("StringMyOptionalOwner.json"),
//                json,
//                JSONCompareMode.LENIENT
//        );
    }

//    @Test
//    void givenAStringMyOptionalOwner_whenDeserializing_thenNullLeadsToEmptyOptionalAndAbsentPropertyLeadsToNull() throws Exception {
//        String json = readJsonFromClassPath("StringMyOptionalOwner.json");
//
//        StringMyOptionalOwner deserialized = objectMapper.readValue(json, StringMyOptionalOwner.class);
//
//        assertThat(deserialized.getStringMyOptional1().get())
//                .isEqualTo("abc");
//        assertThat(deserialized.getStringMyOptional2().isEmpty())
//                .isTrue();
//        assertThat(deserialized.getStringMyOptional3())
//                .isNull();
//    }
//
//    @Test
//    void givenAnEmbeddedStringMyOptionalOwner_whenSerializing_thenEmptyOptionalLeadsToNullAndNullLeadsToAbsentProperty() throws Exception {
//        EmbeddedStringMyOptionalOwner original = new EmbeddedStringMyOptionalOwner();
//
//        original.setStringMyOptional1(MyOptional.of("abc"));
//
//        // this line results in the following error:
//        // com.fasterxml.jackson.core.JsonGenerationException: Can not write a string, expecting field name (context: Object)
//        String json = serialize(objectMapper, original);
//
//        JSONAssert.assertEquals(
//                readJsonFromClassPath("EmbeddedStringMyOptionalOwner.json"),
//                json,
//                JSONCompareMode.LENIENT
//        );
//    }
//
//    @Test
//    void givenAnEmbeddedStringMyOptionalOwner_whenDeserializing_thenNullLeadsToEmptyOptionalAndAbsentPropertyLeadsToNull() throws Exception {
//        String json = readJsonFromClassPath("EmbeddedStringMyOptionalOwner.json");
//
//        EmbeddedStringMyOptionalOwner deserialized = objectMapper.readValue(json, EmbeddedStringMyOptionalOwner.class);
//
//        assertThat(deserialized.getStringMyOptional1().get())
//                .isEqualTo("abc");
//    }

    private String readJsonFromClassPath(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new ClassPathResource(path).getInputStream()))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }

    /**
     * Based on {@link org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter#writeInternal(Object, Type, HttpOutputMessage)}.
     */
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
