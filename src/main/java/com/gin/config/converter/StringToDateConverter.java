package com.gin.config.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
     * 毫秒 时间戳转Date 转换器
     * @author hefeiyu
     */
public class StringToDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        if(StringUtils.isEmpty(source)){
            return null;
        }
        long longTime;
        try {
            longTime = Long.parseLong(source);
        } catch (Exception ex) {

            throw new IllegalArgumentException("Date转化失败，请传入毫秒时间戳，source=" + source);
        }

        return new Date(longTime);
    }
}