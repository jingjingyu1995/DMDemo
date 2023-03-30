package com.gin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

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
  @TableName("USER_INFO")
@ApiModel(value = "UserInfo对象", description = "")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "ID", type = IdType.AUTO)
      private Long id;

    private String account;

    private LocalDateTime firstLogin;
}
