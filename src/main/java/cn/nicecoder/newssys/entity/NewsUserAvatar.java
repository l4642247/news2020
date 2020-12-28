package cn.nicecoder.newssys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lon't
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="NewsUserAvatar对象", description="")
public class NewsUserAvatar implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String src;

    private String desc;


}
