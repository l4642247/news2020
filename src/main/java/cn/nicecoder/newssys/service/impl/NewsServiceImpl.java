package cn.nicecoder.newssys.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import cn.nicecoder.newssys.entity.DO.NewsDO;
import cn.nicecoder.newssys.entity.News;
import cn.nicecoder.newssys.entity.VO.NewsVO;
import cn.nicecoder.newssys.enums.StatusEnum;
import cn.nicecoder.newssys.mapper.NewsMapper;
import cn.nicecoder.newssys.service.NewsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lon't
 * @since 2020-12-18
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    // 默认分页参数
    private static final long PAGE_CURRENT_DEFAULT = 1;
    private static final long PAGE_SIZE_DEFAULT = 10;

    // 概要截取长度
    private static final int NEWS_SUMMARY_LENGTH = 100;

    // 在看和点击排行
    private static final int NEWS_WATCHING_TOP_SIZE = 5;
    private static final int NEWS_CLICK_TOP_SIZE = 5;

    @Override
    public List<NewsVO> listPageNews(NewsDO newsDO) {
        if(newsDO.getCurrent() == 0 || newsDO.getSize() == 0){
            newsDO.setCurrent(PAGE_CURRENT_DEFAULT);
            newsDO.setSize(PAGE_SIZE_DEFAULT);
        }
        Page<News> page = new Page<>(newsDO.getCurrent(), newsDO.getSize());
        IPage<News> iPage = this.page(page, new LambdaQueryWrapper<News>()
                .eq(false, News::getCatalog, newsDO.getCatalogId())
                .orderByDesc(News::getCreateTime));
        newsDO.setPages(iPage.getPages());
        List<NewsVO> newList = iPage.getRecords().stream().map(e->{
            NewsVO newsVO = new NewsVO();
            newsVO.setId(e.getId());
            newsVO.setTitle(e.getTitle());
            if(StrUtil.isNotEmpty(e.getContent())) {
                String contentNoTag = StrUtil.cleanBlank(StrUtil.removeAllLineBreaks(
                        HtmlUtil.cleanHtmlTag(e.getContent())));
                newsVO.setSummary(contentNoTag.length() < NEWS_SUMMARY_LENGTH ? contentNoTag : contentNoTag.substring(0,NEWS_SUMMARY_LENGTH)+"...");
            }
            newsVO.setCover(e.getCover());
            newsVO.setClick(e.getClick());
            newsVO.setPublisher(e.getAuthor());
            newsVO.setPublishTime(DateUtil.format(e.getCreateTime(), DatePattern.NORM_DATETIME_PATTERN));
            newsVO.setWatching(0);
            return newsVO;
        }).collect(Collectors.toList());
        return newList;
    }

    @Override
    public List<NewsVO> listNewsWatchingTopList() {
        return null;
    }

    @Override
    public List<NewsVO> listNewsClickTopList() {
        List<News> newsList = this.list(new LambdaQueryWrapper<News>()
                .eq(News::getStatus, StatusEnum.NORMAL.getCode())
                .orderByDesc(News::getClick).last("limit " + NEWS_CLICK_TOP_SIZE));
        List<NewsVO> newsVOList = newsList.stream().map(e->{
            NewsVO newsVO = new NewsVO();
            newsVO.setId(e.getId());
            newsVO.setTitle(e.getTitle());
            return newsVO;
        }).collect(Collectors.toList());
        return newsVOList;
    }
}
