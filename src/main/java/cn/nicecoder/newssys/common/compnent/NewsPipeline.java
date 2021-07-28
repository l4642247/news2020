package cn.nicecoder.newssys.common.compnent;

import cn.hutool.core.collection.CollectionUtil;
import cn.nicecoder.newssys.domain.entity.biz.News;
import cn.nicecoder.newssys.service.biz.NewsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * @description: 爬取内容存储
 * @author: longt
 * @date: 2020/12/18 上午9:38
 */
@Component
public class NewsPipeline implements Pipeline {

    private Logger logger = LoggerFactory.getLogger(Pipeline.class);

    @Autowired
    NewsService newsService;

    @Override
    public void process(ResultItems resultItems, Task task){
        List<String> title = resultItems.get("title");
        List<String> content = resultItems.get("content");
        if(CollectionUtil.isNotEmpty(title)) {
            String titleItem = title.get(0);
            News newsUpdate = newsService.getOne(new LambdaQueryWrapper<News>()
                    .eq(News::getTitle,titleItem.substring(4,titleItem.length()-5)));
            newsUpdate.setContent(content.get(0));
            newsService.updateById(newsUpdate);
            logger.info("成功插入爬取新闻-数据结果: 【{}】" ,newsUpdate);
        }
    }
}
