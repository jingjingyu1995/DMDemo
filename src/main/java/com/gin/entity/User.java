package com.gin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gin.enums.SexEnum;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author yjj
 * @since 2023-03-30
 */
@Getter
@Setter
@ApiModel(value = "User对象", description = "")
@TableName(value = "\"USER\"")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;


    @NotBlank(message = "账号信息不可为空")
    private String account;

    private Date firstLogin;
    private String phone;

    private Integer deleted;

    private SexEnum sex;
}
