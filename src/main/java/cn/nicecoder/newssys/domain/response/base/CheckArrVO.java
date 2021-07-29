package cn.nicecoder.newssys.domain.response.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 菜单勾选视图
 * @author: xxxxx
 * @date: 2021/3/19 下午3:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckArrVO implements Serializable {
    private static final long serialVersionUID=1L;

    /**
     * type必须从0
     */
    @ApiModelProperty(value = "节点类型")
    private String type;

    /**
     * checked属性的值范围为：0-未选中，1-选中，2-半选
     */
    @ApiModelProperty(value = "选中状态")
    private String checked;

}
