package cn.nicecoder.newssys.service;

import cn.nicecoder.newssys.entity.DO.NewsDO;
import cn.nicecoder.newssys.entity.News;
import cn.nicecoder.newssys.entity.VO.NewsVO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lon't
 * @since 2020-12-18
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public interface NewsService extends IService<News> {
    /**
     * 分页查询
     * @author: longt
     * @Param: [newsDO]
     * @return: java.util.List<cn.nicecoder.newssys.entity.VO.NewsVO>
     * @date: 2020/12/23 下午4:32
     */
    List<NewsVO> listPageNews(NewsDO newsDO);

    /**
     * 在看排行
     * @author: longt
     * @Param: []
     * @return: java.util.List<cn.nicecoder.newssys.entity.VO.NewsVO>
     * @date: 2020/12/23 下午4:32
     */
    List<NewsVO> listNewsWatchingTopList();

    /**
     * 点击排行
     * @author: longt
     * @Param: []
     * @return: java.util.List<cn.nicecoder.newssys.entity.VO.NewsVO>
     * @date: 2020/12/23 下午4:33
     */
    List<NewsVO> listNewsClickTopList();
}
