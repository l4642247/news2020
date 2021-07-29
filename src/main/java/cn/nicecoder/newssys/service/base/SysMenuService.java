package cn.nicecoder.newssys.service.base;

import cn.nicecoder.newssys.domain.entity.base.SysMenu;
import cn.nicecoder.newssys.domain.response.base.MenuNodeVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xxxxx
 * @since 2021-03-05
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 查询根菜单
     * @author: xxxxx
     * @Param: []
     * @return: java.util.List<cn.nicecoder.barbersys.entity.VO.MenuNodeVO>
     * @date: 2021/3/11 上午10:23
     */
    List<MenuNodeVO> createMenuTreeRoot(boolean onlyParent, boolean onlyChecked);

    /**
     * 获取首链接
     * @author: xxxxx
     * @Param: [nodeVO]
     * @return: java.lang.String
     * @date: 2021/4/22 下午7:01
     */
    public String getFirstHref(MenuNodeVO nodeVO);

    /**
     * @Description: 删除缓存
     * @author: xxxxx
     * @Param: []
     * @return:
     * @date: 2021/7/29 下午4:57
     */
    void removeChache();

}
