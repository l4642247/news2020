package cn.nicecoder.newssys.domain.request.base;

import cn.nicecoder.newssys.domain.entity.base.SysUser;
import lombok.Data;

/**
 * 用户数据传输对象
 * @author: xxxxx
 * @date: 2021/2/24 下午11:27
 */
@Data
public class SysUserDO extends SysUser {
    private String roleStr;
}
