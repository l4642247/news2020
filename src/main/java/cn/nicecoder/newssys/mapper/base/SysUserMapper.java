package cn.nicecoder.newssys.mapper.base;

import cn.nicecoder.newssys.domain.request.base.SysUserDO;
import cn.nicecoder.newssys.domain.entity.base.SysUser;
import cn.nicecoder.newssys.domain.response.base.SysUserVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xxxxx
 * @since 2021-02-24
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据username查询单个系统用户
     * @author: xxxxx
     * @Param: [username]
     * @return: cn.nicecoder.barbersys.entity.VO.BarberserVO
     * @date: 2021/5/20 上午10:34
     */
    SysUserVO getOneByUsername(@Param("username") String username);

    /**
     * 分页查询系统用户信息
     * @author: xxxxx
     * @Param: [page, barberUserDO]
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<cn.nicecoder.barbersys.entity.VO.BarberUserVO>
     * @date: 2021/5/20 上午10:34
     */
    Page<SysUserVO> listPageBarberUser(Page page, @Param("barberUserDO") SysUserDO barberUserDO);
}
