package cn.nicecoder.newssys.service.impl;

import cn.nicecoder.newssys.entity.NewsUser;
import cn.nicecoder.newssys.mapper.NewsUserMapper;
import cn.nicecoder.newssys.service.NewsUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lon't
 * @since 2020-12-28
 */
@Service
public class NewsUserServiceImpl extends ServiceImpl<NewsUserMapper, NewsUser> implements NewsUserService {

    @Override
    public NewsUser saveNotExist(NewsUser user) {
        user = this.getOne(new LambdaQueryWrapper<NewsUser>().eq(NewsUser::getIp, user.getIp()));
        if(user.getId() == null){
            this.save(user);
        }
        return user;
    }
}
