package cn.nicecoder.newssys.service.base.impl;


import cn.nicecoder.newssys.common.enums.CommonEnum;
import cn.nicecoder.newssys.common.util.RedisClient;
import cn.nicecoder.newssys.domain.request.base.SysRoleDO;
import cn.nicecoder.newssys.domain.cond.biz.PermissionPO;
import cn.nicecoder.newssys.domain.entity.base.SysRole;
import cn.nicecoder.newssys.domain.entity.base.SysRoleMenu;
import cn.nicecoder.newssys.mapper.base.SysRoleMapper;
import cn.nicecoder.newssys.service.base.SysRoleMenuService;
import cn.nicecoder.newssys.service.base.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xxxxx
 * @since 2021-02-24
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Autowired
    private RedisClient redisClient;

    @Override
    public List<SysRole> getRoleByUsername(String username) {
        return this.baseMapper.getRoleByUsername(username);
    }

    @Override
    public SysRole saveOne(SysRoleDO barberRoleDO) {
        String[] menuStr = barberRoleDO.getSelTree1_select_nodeId().split(",");
        this.saveOrUpdate(barberRoleDO);
        // 更新关联菜单
        if(barberRoleDO.getId() != null) {
            sysRoleMenuService.remove(new LambdaQueryWrapper<SysRoleMenu>()
                    .eq(SysRoleMenu::getRoleId, barberRoleDO.getId()));
        }
        for (String menuId : menuStr ){
            saveRoleMenu(Long.parseLong(menuId), barberRoleDO.getId());
        }
        this.saveOrUpdate(barberRoleDO);

        // 清除缓存的菜单信息
        redisClient.delete(CommonEnum.REDIS_KEY_MENU_PERMISSION.getDesc());
        return barberRoleDO;
    }

    @Override
    public List<PermissionPO> getResourcePermission() {
        return this.baseMapper.getResourcePermission();
    }


    public void saveRoleMenu(Long menuId, Long roleId){
        SysRoleMenu barberRoleMenu = new SysRoleMenu();
        barberRoleMenu.setMenuId(menuId);
        barberRoleMenu.setRoleId(roleId);
        sysRoleMenuService.save(barberRoleMenu);
    }
}
