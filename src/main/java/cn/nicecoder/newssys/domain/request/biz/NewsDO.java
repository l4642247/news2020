package cn.nicecoder.newssys.domain.request.biz;

import cn.nicecoder.newssys.domain.request.base.BaseDO;
import lombok.Data;

/**
 * NewsDO
 * @author: longt
 * @date: 2020/12/22 下午3:22
 */
@Data
public class NewsDO extends BaseDO {

    private Integer catalogId;

    private String keys;

}
