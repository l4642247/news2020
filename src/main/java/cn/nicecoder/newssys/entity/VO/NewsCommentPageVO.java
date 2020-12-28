package cn.nicecoder.newssys.entity.VO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 评论分页视图
 * @author: longt
 * @date: 2020/12/23 下午3:33
 */
@Data
@AllArgsConstructor
public class NewsCommentPageVO {
    private List<NewsCommentVO> list;

    private Long current;

    private Long size;

    private Long pages;
}
