package cn.nicecoder.newssys.controller.base;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.nicecoder.newssys.common.enums.CommonEnum;
import cn.nicecoder.newssys.domain.entity.base.SysMenu;
import cn.nicecoder.newssys.domain.comm.MenuTreeResp;
import cn.nicecoder.newssys.domain.comm.Resp;
import cn.nicecoder.newssys.service.base.SysMenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  菜单前端控制器
 * </p>
 *
 * @author xxxxx
 * @since 2021-03-05
 */
@RestController
@RequestMapping("/menu")
@Api(tags="菜单相关接口")
public class SysMenuController {

    @Autowired
    SysMenuService sysMenuService;

    @GetMapping("/page")
    @ApiOperation(value="查询所有菜单",notes="")
    public Resp page(@RequestParam("page")Long current, @RequestParam("limit")Long size,
                     @RequestParam(value = "name", required = false)String name, @RequestParam(value = "type", required = false)Long type){
        Page<SysMenu> page = new Page<>(current, size);
        Page<SysMenu> result = sysMenuService.page(page, new LambdaQueryWrapper<SysMenu>()
                .like(StrUtil.isNotEmpty(name), SysMenu::getName, name)
                .eq(ObjectUtil.isNotEmpty(type), SysMenu::getType, type)
                .orderByAsc(SysMenu::getParentId, SysMenu::getSort));
        return Resp.success(result.getRecords(), result.getTotal());
    }

    @PostMapping("/save")
    @ApiOperation(value="保存/更新菜单",notes="")
    public Resp save(@RequestBody Map menuMap){
        SysMenu sysMenu = new SysMenu();
        BeanUtil.fillBeanWithMap(menuMap, sysMenu, true);
        if(ObjectUtil.isNotEmpty(menuMap.get("selTree1_select_nodeId"))){
            sysMenu.setParentId(Long.parseLong(menuMap.get("selTree1_select_nodeId").toString()));
        }else{
            sysMenu.setParentId(0L);
        }
        if(ObjectUtil.isNotEmpty(menuMap.get("status")) && "on".equals(menuMap.get("status"))){
            sysMenu.setStatus(1);
        }else{
            sysMenu.setStatus(0);
        }
        sysMenuService.saveOrUpdate(sysMenu);
        return Resp.success(sysMenu);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value="删除菜单",notes="")
    public Resp delete(@PathVariable("id") Long id){
        SysMenu sysMenuDelete = new SysMenu();
        sysMenuService.removeById(id);
        return Resp.success(sysMenuDelete);
    }

    @PostMapping("/parentTree")
    @ApiOperation(value="查询所有菜单树",notes="")
    public MenuTreeResp parentTree(){
        return new MenuTreeResp(sysMenuService.createMenuTreeRoot(true, false));
    }

    @PostMapping("/checkTree")
    @ApiOperation(value="查询下拉多选菜单树",notes="")
    public MenuTreeResp checkTree(){
        return new MenuTreeResp(sysMenuService.createMenuTreeRoot(false, false));
    }

}

