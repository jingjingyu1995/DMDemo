package com.gin.jackson;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.gin.enums.BaseEnum;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BaseEnumConverter extends StdConverter<Integer, Object> {

    private Class<?> enumType;
    //构造器，传入枚举的类型
    public BaseEnumConverter(Class<?> enumType) {
        this.enumType = enumType;
    }

    @Override
    public Object convert(Integer value) {
        if (value == null) {
            return null;
        }
        if (enumType == null) {
            throw new RuntimeException("enumType为空，请配置objectMapper.setHandlerInstantiator(new MyHandler());");
        }

        return getObject(enumType, value);
    }

    @SuppressWarnings("unchecked")
    private static Object getObject(Class<?> enumType, Integer i) {
        for (Object constant : enumType.getEnumConstants()) {
            if (((BaseEnum<Integer, ?>) constant).getValue().equals(i)) {
                return constant;
            }
        }
        throw new RuntimeException("枚举值错误：" + i);
    }


}