package cn.nicecoder.newssys.domain.response.biz;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 文章分页视图
 * @author: longt
 * @date: 2020/12/22 下午3:22
 */
@Data
@AllArgsConstructor
public class NewsPageVO {

    private List<NewsVO> list;

    private Long current;

    private Long size;

    private Long pages;
}
