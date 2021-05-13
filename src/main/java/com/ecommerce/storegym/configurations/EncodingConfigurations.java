package com.ecommerce.storegym.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import java.nio.charset.StandardCharsets;

/*
Indicano come deve agire Spring in determinati contesti, un esempio potrebbe essere: quando andiamo ad usare un server, se volessimo usare un Utf-8
dobbiamo fare una configurations per configurarle in utf-8.

 */


@Configuration
public class EncodingConfigurations {


    /*
    il metodo Bean definisce un comportamento da adottare quando è in esecuzione un server
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setDefaultCharset(StandardCharsets.UTF_8);
        return jsonConverter;
    }


}