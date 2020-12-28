package cn.nicecoder.newssys.controller;

import cn.nicecoder.newssys.entity.DO.NewsCommentDO;
import cn.nicecoder.newssys.entity.VO.NewsCommentPageVO;
import cn.nicecoder.newssys.entity.VO.NewsCommentVO;
import cn.nicecoder.newssys.entity.comm.Resp;
import cn.nicecoder.newssys.service.NewsCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lon't
 * @since 2020-12-24
 */
@RestController
@RequestMapping("/comment")
@Api(tags="评论相关接口Controller")
public class NewsCommentController {

    @Autowired
    NewsCommentService newsCommentService;

    /**
     * 新增评论
     * @author: longt
     * @Param: [newsCommentDO]
     * @return: cn.nicecoder.newssys.entity.comm.Resp
     * @date: 2020/12/25 下午5:25
     */
    @PostMapping("/saveOne")
    @ApiOperation(value="新增评论",notes="newsCommentDO必填")
    public Resp saveOne(@RequestBody NewsCommentDO newsCommentDO){
        int id = newsCommentService.saveOne(newsCommentDO);
        return Resp.ok(id);
    }


    /**
     * 评论列表-分页
     * @author: longt
     * @Param: [newsCommentDO]
     * @return: cn.nicecoder.newssys.entity.comm.Resp
     * @date: 2020/12/28 上午11:32
     */
    @GetMapping("/listPage")
    @ApiOperation(value="评论列表",notes="")
    public Resp listPage(){
        NewsCommentDO newsCommentDO = new NewsCommentDO();
        List<NewsCommentVO> newsCommentList = newsCommentService.listPageComment(newsCommentDO);
        NewsCommentPageVO newsCommentPageVO = new NewsCommentPageVO(newsCommentList, newsCommentDO.getCurrent(),
                newsCommentDO.getSize(), newsCommentDO.getPages());
        return Resp.ok(newsCommentPageVO);
    }


}

