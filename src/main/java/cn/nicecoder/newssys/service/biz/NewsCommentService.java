package cn.nicecoder.newssys.service.biz;

import cn.nicecoder.newssys.domain.request.biz.NewsCommentDO;
import cn.nicecoder.newssys.domain.entity.biz.NewsComment;
import cn.nicecoder.newssys.domain.response.biz.NewsCommentVO;
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
     * @return: java.util.List<cn.nicecoder.newssys.entity.response.biz.NewsCommentVO>
     * @date: 2020/12/28 上午10:36
     */
    public List<NewsCommentVO> listPageComment(NewsCommentDO newsCommentDO);
}
