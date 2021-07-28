package cn.nicecoder.newssys.controller.biz;


import cn.nicecoder.newssys.domain.response.biz.NewsCatalogVO;
import cn.nicecoder.newssys.domain.comm.Resp;
import cn.nicecoder.newssys.service.biz.NewsCatalogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lon't
 * @since 2020-12-18
 */
@RestController
@RequestMapping("/newssys/news-catalog")
@Api(tags="文章分类相关接口")
public class NewsCatalogController {

    @Autowired
    NewsCatalogService newsCatalogService;

    /**
     * 查询所有类别
     * @author: longt
     * @Param: []
     * @return: cn.nicecoder.newssys.entity.comm.Resp
     * @date: 2021/2/19 上午9:13
     */
    @GetMapping("/listAllCatalog")
    @ApiOperation(value="类别列表",notes="")
    public Resp listAllCatalog(){
        List<NewsCatalogVO> list = newsCatalogService.listAllCatalog();
        return Resp.success(list);
    }
}

