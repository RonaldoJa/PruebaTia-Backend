package com.PruebaTia.PruebaTia.Config;

import com.PruebaTia.PruebaTia.Entity.Authentication;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.core.GrantedAuthority;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();

        SimpleModule module = new SimpleModule();
        SimpleAbstractTypeResolver resolver = new SimpleAbstractTypeResolver();
        resolver.addMapping(GrantedAuthority.class, Authentication.class);
        module.setAbstractTypes(resolver);
        objectMapper.registerModule(module);

        return objectMapper;
    }
}
