package com.zhijia.smartschool.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhijia
 * @since 2022-09-13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="Clazz对象")
@TableName("tb_clazz")
public class Clazz implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer number;

    private String introducation;

    private String headmaster;

    private String email;

    private String telephone;

    private String gradeName;


}
