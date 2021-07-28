package cn.nicecoder.newssys.domain.response.base;

import cn.nicecoder.newssys.domain.entity.base.SysRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息视图
 * @author: xxxxx
 * @date: 2021/3/4 上午11:24
 */
@Data
public class SysUserVO implements Serializable {
    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "身份证")
    private String idCard;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "角色列表")
    private List<SysRole> roleList;

    @ApiModelProperty(value = "角色字符串")
    private String roleStr;
}
