package cn.nicecoder.newssys.controller.biz;

import cn.nicecoder.newssys.domain.request.biz.NewsCommentDO;
import cn.nicecoder.newssys.domain.entity.biz.NewsUser;
import cn.nicecoder.newssys.domain.response.biz.NewsCommentPageVO;
import cn.nicecoder.newssys.domain.response.biz.NewsCommentVO;
import cn.nicecoder.newssys.domain.comm.Resp;
import cn.nicecoder.newssys.service.biz.NewsCommentService;
import cn.nicecoder.newssys.service.biz.NewsUserService;
import cn.nicecoder.newssys.common.util.IPUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@Api(tags="评论相关接口")
public class NewsCommentController {

    @Autowired
    NewsCommentService newsCommentService;

    @Autowired
    NewsUserService newsUserService;

    /**
     * 新增评论
     * @author: longt
     * @Param: [newsCommentDO]
     * @return: cn.nicecoder.newssys.entity.comm.Resp
     * @date: 2020/12/25 下午5:25
     */
    @PostMapping("/saveOne")
    @ApiOperation(value="新增评论",notes="newsCommentDO必填")
    public Resp saveOne(HttpServletRequest request, @RequestBody NewsCommentDO newsCommentDO){
        String ip = IPUtil.getIpAddress(request);
        NewsUser user = new NewsUser();
        user.setIp(ip);
        user.setName(ip);
        user.setAvatar(1);
        user = newsUserService.saveNotExist(user);
        newsCommentDO.setCommFrom(user.getId());
        int id = newsCommentService.saveOne(newsCommentDO);
        return Resp.success(id);
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
        return Resp.success(newsCommentPageVO);
    }


}

