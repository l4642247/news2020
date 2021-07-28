package cn.nicecoder.newssys.controller.biz;


import cn.nicecoder.newssys.domain.request.biz.NewsLikeDO;
import cn.nicecoder.newssys.domain.entity.biz.NewsLike;
import cn.nicecoder.newssys.domain.comm.Resp;
import cn.nicecoder.newssys.service.biz.NewsLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lon't
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/like")
@Api(tags="点赞相关接口Controller")
public class NewsLikeController {

    @Autowired
    NewsLikeService newsLikeService;

    @PostMapping("/saveOne")
    @ApiOperation(value="新增点赞",notes="newsLikeDO必填")
    public Resp saveOne(@RequestBody NewsLikeDO newsLikeDO){
        NewsLike newsLike = new NewsLike();
        // 1.文章"在看"，2.评论点赞
        newsLike.setType(newsLikeDO.getType());
        newsLike.setLikeFrom(newsLikeDO.getLikeFrom());
        newsLike.setLikeTo(newsLikeDO.getLikeTo());
        newsLikeService.save(newsLike);
        return Resp.success(newsLike);
    }
}

