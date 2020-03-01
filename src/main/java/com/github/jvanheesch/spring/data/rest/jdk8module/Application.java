package com.github.jvanheesch.spring.data.rest.jdk8module;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {
    @Autowired
    private VerdictRepository verdictRepository;
    @Autowired
    private ConclusionRepository conclusionRepository;

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.build();
        objectMapper.addMixIn(Object.class, IgnoreHibernateGarbage.class);
        return objectMapper;
    }

    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private static class IgnoreHibernateGarbage {
    }

    @PostConstruct
    public void init() {
        Verdict compliant = new Verdict();
        compliant.setId(1L);
        compliant.setName("Compliant");
        Verdict nonCompliant = new Verdict();
        nonCompliant.setId(1L);
        nonCompliant.setName("Non-compliant");

        compliant = this.verdictRepository.save(compliant);
        nonCompliant = this.verdictRepository.save(nonCompliant);

        Conclusion conclusion = new Conclusion();
        OriginWoodEvaluation originWoodEvaluation = new OriginWoodEvaluation();
        VerdictRecord verdictRecord = new VerdictRecord();
        verdictRecord.setVerdict(compliant);
        originWoodEvaluation.setCitesLicense(verdictRecord);
        originWoodEvaluation.setFlegtLicense(new VerdictRecord());

        conclusion.setOriginWoodEvaluation(originWoodEvaluation);

        this.conclusionRepository.save(conclusion);
    }
}
