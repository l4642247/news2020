package cn.nicecoder.newssys.service;

import cn.nicecoder.newssys.entity.DO.NewsCommentDO;
import cn.nicecoder.newssys.entity.NewsComment;
import cn.nicecoder.newssys.entity.VO.NewsCommentVO;
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
 * @since 2020-12-24
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public interface NewsCommentService extends IService<NewsComment> {
    /**
     * 新增评论
     * @author: longt
     * @Param: [newsCommentDO]
     * @return: java.lang.Integer
     * @date: 2020/12/24 上午10:42
     */
    public Integer saveOne(NewsCommentDO newsCommentDO);

    /**
     * 分页查询评论
     * @author: longt
     * @Param: [newsCommentDO]
     * @return: java.util.List<cn.nicecoder.newssys.entity.VO.NewsCommentVO>
     * @date: 2020/12/28 上午10:36
     */
    public List<NewsCommentVO> listPageComment(NewsCommentDO newsCommentDO);
}
