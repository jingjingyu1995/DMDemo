package com.gin.jackson;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.util.Converter;

public class MyHandler extends HandlerInstantiator {


    @Override
    public JsonDeserializer<?> deserializerInstance(DeserializationConfig config, Annotated annotated, Class<?> deserClass) {
        return null;
    }

    @Override
    public KeyDeserializer keyDeserializerInstance(DeserializationConfig config, Annotated annotated, Class<?> keyDeserClass) {
        return null;
    }

    @Override
    public JsonSerializer<?> serializerInstance(SerializationConfig config, Annotated annotated, Class<?> serClass) {
        return null;
    }

    @Override
    public TypeResolverBuilder<?> typeResolverBuilderInstance(MapperConfig<?> config, Annotated annotated, Class<?> builderClass) {
        return null;
    }

    @Override
    public TypeIdResolver typeIdResolverInstance(MapperConfig<?> config, Annotated annotated, Class<?> resolverClass) {
        return null;
    }
    //实例化时需要传入字段的类型
    @Override
    public Converter<?, ?> converterInstance(MapperConfig<?> config, Annotated annotated, Class<?> implClass) {
        if (implClass == BaseEnumConverter.class) {
            return new BaseEnumConverter(annotated.getRawType());
        }
        return super.converterInstance(config, annotated, implClass);
    }
}