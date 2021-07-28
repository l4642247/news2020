package cn.nicecoder.newssys.controller.base;


import cn.nicecoder.newssys.common.enums.CommonEnum;
import cn.nicecoder.newssys.domain.request.base.SysRoleDO;
import cn.nicecoder.newssys.domain.entity.base.SysRole;
import cn.nicecoder.newssys.domain.response.base.SelectVO;
import cn.nicecoder.newssys.domain.comm.Resp;
import cn.nicecoder.newssys.service.base.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  角色前端控制器
 * </p>
 *
 * @author xxxxx
 * @since 2021-02-24
 */
@RestController
@RequestMapping("/role")
@Api(tags="角色相关接口")
public class SysRoleController {

    @Autowired
    SysRoleService sysRoleService;

    @GetMapping("/select")
    @ApiOperation(value="下拉框数据返回",notes="")
    public Resp select(){
        List<SysRole> sysRoleList = sysRoleService.list(new LambdaQueryWrapper<SysRole>()
                .eq(SysRole::getStatus, CommonEnum.NORMAL.getCode()));
        List<SelectVO> selectVOList = new ArrayList<>();
        sysRoleList.stream().forEach(item ->{
            SelectVO selectVO = new SelectVO();
            selectVO.setName(item.getName());
            selectVO.setValue(item.getId());
            selectVOList.add(selectVO);
        });
        return Resp.success(selectVOList);
    }

    @GetMapping("/page")
    @ApiOperation(value="查询所有角色",notes="")
    public Resp page(@RequestParam("page")Long current, @RequestParam("limit")Long size){
        Page<SysRole> page = new Page<>(current, size);
        Page<SysRole> result = sysRoleService.page(page, new LambdaQueryWrapper<SysRole>().eq(
                SysRole::getStatus, CommonEnum.NORMAL.getCode()));
        return Resp.success(result.getRecords(), result.getTotal());
    }

    @PostMapping("/save")
    @ApiOperation(value="保存/更新角色",notes="")
    public Resp save(@RequestBody SysRoleDO barberRoleDO){
        sysRoleService.saveOne(barberRoleDO);
        return Resp.success(barberRoleDO);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value="删除角色",notes="")
    public Resp delete(@PathVariable("id") Long id){
        SysRole sysRoleDelete = new SysRole();
        sysRoleDelete.setId(id);
        sysRoleDelete.setStatus(CommonEnum.DELETED.getCode());
        sysRoleService.updateById(sysRoleDelete);
        return Resp.success(sysRoleDelete);
    }
}

