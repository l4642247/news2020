package cn.nicecoder.newssys.domain.request.base;

import cn.nicecoder.newssys.domain.entity.base.SysRole;
import lombok.Data;

/**
 * 角色数据传输对象
 * @author: xxxxx
 * @date: 2021/2/24 下午11:27
 */
@Data
public class SysRoleDO extends SysRole {
    private String selTree1_select_nodeId;
}
