package com.gin.utils.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class Result<T> {

    @ApiModelProperty
    private int code;

    @ApiModelProperty
    private String message;

    @ApiModelProperty
    private T data;
}