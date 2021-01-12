package cn.nicecoder.newssys.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.nicecoder.newssys.entity.DO.NewsCommentDO;
import cn.nicecoder.newssys.entity.DO.NewsDO;
import cn.nicecoder.newssys.entity.News;
import cn.nicecoder.newssys.entity.VO.NewsCatalogVO;
import cn.nicecoder.newssys.entity.VO.NewsCommentVO;
import cn.nicecoder.newssys.entity.VO.NewsPageVO;
import cn.nicecoder.newssys.entity.VO.NewsVO;
import cn.nicecoder.newssys.enums.TypeEnum;
import cn.nicecoder.newssys.service.NewsCatalogService;
import cn.nicecoder.newssys.service.NewsCommentService;
import cn.nicecoder.newssys.service.NewsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

/**
 * 页面跳转
 * @author: longt
 * @date: 2020/12/22 下午2:24
 */
@Controller
public class PageController {

    @Autowired
    NewsService newsService;

    @Autowired
    NewsCatalogService newsCatalogService;

    @Autowired
    NewsCommentService newsCommentService;

    /**
     * 去到首页
     * @author: longt
     * @Param: [model, newsDO]
     * @return: java.lang.String
     * @date: 2020/12/24 上午9:13
     */
    @GetMapping("/")
    public String index(Model model, NewsDO newsDO){
        // 查询数据
        List<NewsVO> newsList =  newsService.listPageNews(newsDO);
        List<NewsVO> newsWatchingTopList =  newsService.listNewsWatchingTopList();
        List<NewsVO> newsClickTopList =  newsService.listNewsClickTopList();
        List<NewsCatalogVO> catalogVOList = newsCatalogService.listAllCatalog();
        // 封装数据
        NewsPageVO newsPageVO = new NewsPageVO(newsList, newsDO.getCurrent(), newsDO.getSize(), newsDO.getPages());
        model.addAttribute("current", newsDO.getCurrent());
        model.addAttribute("size", newsDO.getSize());
        model.addAttribute("pages", newsDO.getPages());
        model.addAttribute("pageResult", newsPageVO);
        model.addAttribute("newsWatchingTop",newsWatchingTopList);
        model.addAttribute("newsClickTop",newsClickTopList);
        model.addAttribute("catalogResult", catalogVOList);
        return "index";
    }

    /**
     * @Description: 列表详情页
     * @author: longt
     * @Param: [model, id]
     * @return: java.lang.String
     * @date: 2020/12/24 上午9:36
     */
    @GetMapping("/news/{id}")
    public String detailPage(Model model, @PathVariable("id") Integer id){
        News current = newsService.getOne(new LambdaQueryWrapper<News>().eq(News::getId, id));
        News newsPre = newsService.getOne(new LambdaQueryWrapper<News>().lt(News::getId, id).last("limit 1"));
        News newsNext = newsService.getOne(new LambdaQueryWrapper<News>().gt(News::getId, id).last("limit 1"));
        NewsVO newsVO = new NewsVO();
        newsVO.setId(current.getId());
        newsVO.setTitle(current.getTitle());
        newsVO.setPublisher(current.getAuthor());
        newsVO.setWatching(0);
        newsVO.setContent(current.getContent());
        newsVO.setPublishTime(DateUtil.format(current.getCreateTime(), DatePattern.NORM_DATETIME_PATTERN));
        newsVO.setClick(0);

        // 评论列表
        NewsCommentDO queryParam = new NewsCommentDO();
        queryParam.setType(TypeEnum.COMMENT_TO_NEWS.getCode());
        queryParam.setCommTo(current.getId());
        List<NewsCommentVO> newsCommentVOList = newsCommentService.listPageComment(queryParam);

        // 返回数据
        model.addAttribute("newsCur", newsVO);
        model.addAttribute("newsPre", newsPre);
        model.addAttribute("newsNext", newsNext);
        model.addAttribute("commentList", newsCommentVOList);
        return "newsPage";
    }



}
