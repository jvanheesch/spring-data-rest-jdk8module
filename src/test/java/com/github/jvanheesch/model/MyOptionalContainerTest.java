package com.github.jvanheesch.model;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.jvanheesch.model.serialization.OriginWoodEvaluation;
import com.github.jvanheesch.model.serialization.OriginWoodLicenseValidityVerdict;
import com.github.jvanheesch.model.serialization.Verdict;
import com.github.jvanheesch.model.serialization.VerdictRecord;
import com.github.jvanheesch.spring.data.rest.jdk8module.MyOptional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MyOptionalContainerTest {
    @Autowired
    private RepositoryRestMvcConfiguration repositoryRestMvcConfiguration;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void initializeObjectMapper() {
        this.objectMapper = repositoryRestMvcConfiguration.halObjectMapper();
    }

    @Test
    void leuter() throws Exception {
        OriginWoodEvaluation originWoodEvaluation = new OriginWoodEvaluation();

        Verdict verdict = new OriginWoodLicenseValidityVerdict();
        verdict.setName("leuterverdict");
        originWoodEvaluation.setFromIndonesia(new VerdictRecord(verdict));

        String json = serialize(objectMapper, originWoodEvaluation);

        JSONAssert.assertEquals(
                readJsonFromClassPath("MyOptionalContainer.json"),
                json,
                JSONCompareMode.NON_EXTENSIBLE
        );
    }

    @Test
    void givenAnMyOptionalContainer_whenSerializing_thenEmptyMyOptionalLeadsToNullAndNullLeadsToAbsentProperty() throws Exception {
        MyOptionalContainer original = new MyOptionalContainer();

        original.setMyOptional1(MyOptional.of("abc"));
        original.setMyOptional2(MyOptional.empty());
        // variables op type MyOptional<X> should never be null.
        // this code is only here to illustrate jackson's serialization behavior
        original.setMyOptional3(null);

        String json = serialize(objectMapper, original);

        JSONAssert.assertEquals(
                readJsonFromClassPath("MyOptionalContainer.json"),
                json,
                JSONCompareMode.NON_EXTENSIBLE
        );
    }

    @Test
    void givenAnMyOptionalContainer_whenDeserializing_thenNullLeadsToEmptyMyOptionalAndAbsentPropertyLeadsToNull() throws Exception {
        String json = readJsonFromClassPath("MyOptionalContainer.json");

        MyOptionalContainer deserialized = objectMapper.readValue(json, MyOptionalContainer.class);

        assertThat(deserialized.getMyOptional1().get())
                .isEqualTo("abc");
        assertThat(deserialized.getMyOptional2().isEmpty())
                .isTrue();
        assertThat(deserialized.getMyOptional3())
                .isNull();
    }

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
