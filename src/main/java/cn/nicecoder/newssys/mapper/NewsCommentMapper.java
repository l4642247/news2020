package cn.nicecoder.newssys.mapper;

import cn.nicecoder.newssys.entity.DO.NewsCommentDO;
import cn.nicecoder.newssys.entity.NewsComment;
import cn.nicecoder.newssys.entity.VO.NewsCommentVO;
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
