package com.gin.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gin.exceptions.ServiceException;
import com.gin.jackson.BaseEnumConverter;

@JsonDeserialize(converter = BaseEnumConverter.class)
public  interface BaseEnum<T, E extends Enum<E>> {
    @JsonValue
    T getValue();

    String getContent();

    /**
     * 根据value获得BaseEnum枚举
     * @param enumClass enumClass
     * @param value value
     */
    static <T extends BaseEnum<?, ?>> T getEnumOfValue (Class<T> enumClass, String value){
        if (value == null || value.length() == 0) {
            return null;
        }

        Integer integer;
        try {
            integer = Integer.valueOf(value);
        } catch (Exception ex) {
            throw new ServiceException("无法解析，value:" + value + "，enumClass:" + enumClass.getSimpleName(), ex);
        }

        return getEnumOfValue(enumClass, integer);

    }

    /**
     * 根据value获得BaseEnum枚举
     * @param enumClass enumClass
     * @param value value
     */
    static <T extends BaseEnum<?, ?>> T getEnumOfValue (Class<T> enumClass, Integer value){
        if (value == null) {
            return null;
        }
        if (BaseEnum.class.isAssignableFrom(enumClass)) {
            for (T constant : enumClass.getEnumConstants()) {
                if (constant.getValue().equals(value)) {
                    return constant;
                }
            }
        }
        throw new ServiceException("无法解析，value:" + value + "，enumClass:" + enumClass.getSimpleName());

    }

}