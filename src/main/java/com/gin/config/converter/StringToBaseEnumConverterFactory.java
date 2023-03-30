package com.gin.config.converter;


import com.gin.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * BaseEnum转化器工厂类 String-->BaseEnum
 *
 * @author hefeiyu
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class StringToBaseEnumConverterFactory implements ConverterFactory<String, BaseEnum> {

    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return (Converter<String, T>) new StringToEnumConverter(targetType);
    }

    /**
     * String-->BaseEnum 转换器，用于controller方法参数的BaseEnum枚举转换，将前台穿过来的int类型数据转换为BaseEnum
     *
     * @author hefeiyu
     */
    public static class StringToEnumConverter implements Converter<String, BaseEnum> {

        private Class<? extends BaseEnum> targetType;

        StringToEnumConverter(Class<? extends BaseEnum> targetType) {
            this.targetType = targetType;
        }

        @Override
        public BaseEnum convert(String source) {
            return BaseEnum.getEnumOfValue(targetType, source);
        }
    }
}