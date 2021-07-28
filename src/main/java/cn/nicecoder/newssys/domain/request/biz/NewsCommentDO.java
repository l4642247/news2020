package cn.nicecoder.newssys.domain.request.biz;

import cn.nicecoder.newssys.domain.request.base.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * NewsCommentDO
 * @author: longt
 * @date: 2020/12/24 上午10:29
 */
@Data
@ToString
@ApiModel(value="NewsCommentDO",description="评论查询条件")
public class NewsCommentDO extends BaseDO {

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "评论对象")
    private Integer commTo;

    @ApiModelProperty(value = "评论来源")
    private Integer commFrom;

    @ApiModelProperty(value = "内容")
    private String content;
}
