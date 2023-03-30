package com.gin.config.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * 通用的string转换为enum，首先尝试从枚举名字转化，失败后尝试从int转化为enum
 * @author hfy
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class GenericStringToEnumConverterFactory implements ConverterFactory<String, Enum> {

    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {

        return (Converter<String, T>) new GenericStringToEnumConverter(targetType);
    }

    public static class GenericStringToEnumConverter implements Converter<String, Enum> {

        Class targetType;

        public GenericStringToEnumConverter(Class targetType) {
            this.targetType = targetType;
        }

        @Override
        public Enum convert(String source) {

            if (source.length() == 0) {
                return null;
            }

            try {
                //首先根据枚举名查找枚举
                return Enum.valueOf(targetType, source);
            } catch (IllegalArgumentException ex) {
                int intValue;
                try {
                    intValue = Integer.parseInt(source);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("无法解析枚举值：" + source + ",枚举类型为：" + targetType.toString());
                }

                //根据枚举数字查询
                return (Enum) (targetType.getEnumConstants()[intValue]);
            }

        }
    }
}