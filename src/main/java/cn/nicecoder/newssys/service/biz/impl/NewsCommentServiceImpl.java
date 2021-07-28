package cn.nicecoder.newssys.service.biz.impl;

import cn.nicecoder.newssys.domain.request.biz.NewsCommentDO;
import cn.nicecoder.newssys.domain.entity.biz.NewsComment;
import cn.nicecoder.newssys.domain.response.biz.NewsCommentVO;
import cn.nicecoder.newssys.mapper.biz.NewsCommentMapper;
import cn.nicecoder.newssys.service.biz.NewsCommentService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lon't
 * @since 2020-12-24
 */
@Service
public class NewsCommentServiceImpl extends ServiceImpl<NewsCommentMapper, NewsComment> implements NewsCommentService {
    // 默认分页参数
    private static final long PAGE_CURRENT_DEFAULT = 1;
    private static final long PAGE_SIZE_DEFAULT = 10;

    @Override
    public Integer saveOne(NewsCommentDO newsCommentDO) {
        NewsComment newsComment = new NewsComment();
        newsComment.setType(newsCommentDO.getType());
        newsComment.setCommFrom(newsCommentDO.getCommFrom());
        newsComment.setCommTo(newsCommentDO.getCommTo());
        newsComment.setContent(newsCommentDO.getContent());
        // 保存数据
        this.save(newsComment);
        return newsComment.getId();
    }

    @Override
    public List<NewsCommentVO> listPageComment(NewsCommentDO newsCommentDO) {
        if(newsCommentDO.getCurrent() == 0 && newsCommentDO.getSize() == 0){
            newsCommentDO.setCurrent(PAGE_CURRENT_DEFAULT);
            newsCommentDO.setSize(PAGE_SIZE_DEFAULT);
        }
        Page<NewsComment> page = new Page<>(newsCommentDO.getCurrent(), newsCommentDO.getSize());
        IPage<NewsCommentVO> list= this.baseMapper.listPageComment(page, newsCommentDO);
        // 返回分页信息
        newsCommentDO.setCurrent(page.getCurrent());
        newsCommentDO.setSize(page.getSize());
        newsCommentDO.setPages(page.getPages());
        return list.getRecords();
    }
}
