package cn.nicecoder.newssys.entity.DO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * NewsLikeDO
 * @author: longt
 * @date: 2020/12/24 上午10:29
 */
@Data
@ToString
@ApiModel(value="NewsLikeDO",description="点赞查询条件")
public class NewsLikeDO extends BaseDO{

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "点赞对象")
    private Integer likeTo;

    @ApiModelProperty(value = "点赞来源")
    private Integer likeFrom;

}
