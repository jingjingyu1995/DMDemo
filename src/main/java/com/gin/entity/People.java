package com.gin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * <p>
 * 人员信息
 * </p>
 *
 * @author yjj
 * @since 2023-03-29
 */


@Getter
@Setter
@ApiModel(value = "People对象", description = "人员信息")
@TableName(value = "\"people\"")
public class People implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "\"id\"", type = IdType.AUTO)
    private Long id;

    @TableField(value = "\"name\"")
    private String name;
    @TableField(value = "\"age\"")
    private Integer age;
}
