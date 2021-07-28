package cn.nicecoder.newssys.mapper.biz;

import cn.nicecoder.newssys.domain.request.biz.NewsCommentDO;
import cn.nicecoder.newssys.domain.entity.biz.NewsComment;
import cn.nicecoder.newssys.domain.response.biz.NewsCommentVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lon't
 * @since 2020-12-24
 */
public interface NewsCommentMapper extends BaseMapper<NewsComment> {
    IPage<NewsCommentVO> listPageComment(Page page, @Param("ncd") NewsCommentDO newsCommentDO);
}
