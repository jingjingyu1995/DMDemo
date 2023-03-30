package com.gin.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * @Description
 * @Date 2023/3/30 16:34
 * @Created by jingjing.yu
 */
public enum SexEnum implements BaseEnum<Integer,SexEnum> {

    MAN(1, "男"),
    WOMEN(2, "女");

    private Integer value;

    private String content;

    SexEnum(Integer value, String content) {
        this.value = value;
        this.content = content;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String getContent() {
        return this.content;
    }
}
