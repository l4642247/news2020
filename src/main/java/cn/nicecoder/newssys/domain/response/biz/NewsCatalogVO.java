package cn.nicecoder.newssys.domain.response.biz;

import lombok.Data;

import java.io.Serializable;

/**
 * 新闻类别视图
 * @author: longt
 * @date: 2020/12/23 下午3:33
 */
@Data
public class NewsCatalogVO implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;

    private String name;
}
