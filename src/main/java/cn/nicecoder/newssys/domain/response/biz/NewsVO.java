package cn.nicecoder.newssys.domain.response.biz;

import lombok.Data;

/**
 * 文章列表视图
 * @author: longt
 * @date: 2020/12/22 下午3:22
 */
@Data
public class NewsVO {

    private Integer id;

    private String title;

    private String summary;

    private String cover;

    private String content;

    private String publishTime;

    private String publisher;

    private Integer click;

    private Integer watching;
}
