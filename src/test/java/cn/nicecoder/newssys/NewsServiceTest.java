package cn.nicecoder.newssys;

import cn.nicecoder.newssys.entity.News;
import cn.nicecoder.newssys.service.NewsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description: 新闻服务测试
 * @author: longt
 * @date: 2020/12/18 上午9:42
 */
@SpringBootTest
public class NewsServiceTest {

    @Autowired
    NewsService newsService;

    @Test
    public void say(){
        Page<News> page = new Page<>(1,5);
        IPage<News> result = newsService.page(page, new QueryWrapper<News>().orderByDesc("create_time"));
        for (News record : result.getRecords()) {
            System.out.println(record);
        }
    }

}
