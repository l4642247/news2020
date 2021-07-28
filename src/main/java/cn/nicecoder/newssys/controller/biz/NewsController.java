package cn.nicecoder.newssys.controller.biz;


import cn.nicecoder.newssys.domain.request.biz.NewsDO;
import cn.nicecoder.newssys.domain.response.biz.NewsVO;
import cn.nicecoder.newssys.domain.comm.Resp;
import cn.nicecoder.newssys.service.biz.NewsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lon't
 * @since 2020-12-18
 */
@RestController
@RequestMapping("/news")
@Api(tags="新闻相关接口Controller")
public class NewsController {
    @Autowired
    NewsService newsService;

    /**
     * 分页查询
     * @author: longt
     * @Param: [newsDO]
     * @return: cn.nicecoder.newssys.entity.comm.Resp
     * @date: 2020/12/29 下午3:32
     */
    @GetMapping("listPage")
    public Resp listPage(Model model, NewsDO newsDO){
        List<NewsVO> newsVOList = newsService.listPageNews(newsDO);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("list", newsVOList);
        resultMap.put("current", newsDO.getCurrent());
        resultMap.put("pages", newsDO.getPages());
        resultMap.put("size", newsDO.getSize());
        return Resp.success(resultMap);
    }
}

