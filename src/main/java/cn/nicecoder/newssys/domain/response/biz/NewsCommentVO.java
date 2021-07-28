package cn.nicecoder.newssys.domain.response.biz;

import lombok.Data;

import java.io.Serializable;

/**
 * 评论视图
 * @author: longt
 * @date: 2020/12/23 下午3:33
 */
@Data
public class NewsCommentVO implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 评论谁
     */
    private Integer toId;

    /**
     * 谁评论
     */
    private Integer fromId;

    private String avatar;

    private String name;

    private String content;

    private Integer agreeNum;

    private String createTime;

    /**
     * 二级以下评论
     */
    private NewsCommentPageVO subComments;
}
