package cn.nicecoder.newssys.service.impl;

import cn.nicecoder.newssys.compnent.NewsPipeline;
import cn.nicecoder.newssys.compnent.NewsProcessor;
import cn.nicecoder.newssys.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

/**
 * @description: 爬虫服务实现类
 * @author: longt
 * @date: 2020/12/18 上午9:48
 */
@Service
public class SpiderServiceImpl implements SpiderService {

    @Value("${spider.configuration.url}")
    private String root_url;

    @Value("${spider.configuration.thread}")
    private Integer thread_num;

    @Value("${spider.configuration.limit}")
    private Integer list_limit;

    @Autowired
    private NewsPipeline newsPipeline;

    @Autowired
    NewsProcessor newsProcessor;

    @Override
    public void start() {
        Spider.create(newsProcessor)
                .addUrl(root_url + list_limit)
                // 开启5个线程抓取
                .thread(thread_num)
                .addPipeline(newsPipeline)
                // 启动爬虫
                .run();
    }
}
