package cn.nicecoder.newssys.service.biz;

import cn.nicecoder.newssys.domain.entity.biz.NewsUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lon't
 * @since 2020-12-28
 */
public interface NewsUserService extends IService<NewsUser> {

    /**
     * @Description: 不存在则新增
     * @author: longt
     * @Param: [user]
     * @return: cn.nicecoder.newssys.entity.NewsUser
     * @date: 2020/12/31 下午3:42
     */
    NewsUser saveNotExist(NewsUser user);
}
