package com.gin.config.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.gin.config.converter.*;
import com.gin.jackson.MyHandler;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @author hefeiyu 2020-12-10 14:17
 */
public class BaseWebMvcConfiguration extends WebMvcConfigurationSupport {


    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new StringToBaseEnumConverterFactory());
        registry.addConverter(new StringToDateConverter());
        registry.addConverterFactory(new GenericStringToEnumConverterFactory());
    }

    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.extendMessageConverters(converters);

        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter jacksonConverter = (MappingJackson2HttpMessageConverter) converter;
                ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
                objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX, true);
                objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
                objectMapper.setHandlerInstantiator(new MyHandler());
                System.out.println("objectMapper:" + objectMapper);
            }
        }
    }

}
