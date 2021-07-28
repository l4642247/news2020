package cn.nicecoder.newssys.service.base;

import cn.nicecoder.newssys.domain.request.base.SysUserDO;
import cn.nicecoder.newssys.domain.entity.base.SysUser;
import cn.nicecoder.newssys.domain.response.base.SysUserVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xxxxx
 * @since 2021-02-24
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 获取当前用户id
     * @author: xxxxx
     * @Param: []
     * @return: java.lang.Long
     * @date: 2021/4/28 下午3:16
     */
    public SysUserVO getCurrentUser();

    /**
     * 修改密码
     * @param oldPassword
     * @param password
     * @return
     */
    public SysUser passwordModify(String oldPassword, String password);

    /**
     * 获取用户信息视图
     * @param username
     * @return
     */
    public SysUserVO getOneByUsername(String username);

    /**
     * 分页查询
     * @param page
     * @param barberUserDO
     * @return
     */
    Page<SysUserVO> listPageBarberUser(Page<SysUser> page, SysUserDO barberUserDO);


    /**
     * 新增
     * @param barberUserSave
     * @return
     */
    @Transactional
    SysUser saveOne(SysUserDO barberUserSave,int opt);

}
