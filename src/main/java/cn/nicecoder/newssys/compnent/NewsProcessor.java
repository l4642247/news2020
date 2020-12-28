package cn.nicecoder.newssys.compnent;

import cn.hutool.core.util.ObjectUtil;
import cn.nicecoder.newssys.entity.News;
import cn.nicecoder.newssys.entity.NewsCatalog;
import cn.nicecoder.newssys.service.NewsCatalogService;
import cn.nicecoder.newssys.service.NewsService;
import cn.nicecoder.newssys.util.RedisClient;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 爬取内容解析
 * @author: longt
 * @date: 2020/12/18 上午9:38
 */
@Component
public class NewsProcessor implements PageProcessor {
    private Logger logger = LoggerFactory.getLogger(NewsProcessor.class);

    private final static String URL_LIST_REG = "https://new.qq.com/omn/.*";
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Autowired
    private NewsPipeline newsPipeline;

    @Autowired
    private NewsCatalogService newsCatalogService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private RedisClient redisClient;

    @Override
    public void process(Page page) {
        // 判断是否是列表页
        if (!page.getUrl().regex(URL_LIST_REG).match()){
            ReadContext context = JsonPath.parse(page.getRawText());
            List<HashMap> list =  context.read("$.data.list");
            List<String> subUrl = new ArrayList<String>();
            List<News> batchList = new ArrayList<News>();
            for (HashMap item: list) {
                // 先把除内容以外的信息保存下来
                String title = item.get("title").toString();
                if(ObjectUtil.isNotEmpty(redisClient.get(title))){
                    continue;
                }
                redisClient.set(title, "value:" + title);
                News news = new News();
                news.setTitle(title);
                news.setCover(item.get("img").toString());
                news.setAuthor(item.get("media_name").toString());
                NewsCatalog newsCatalog = new NewsCatalog();
                newsCatalog.setName(item.get("category_cn").toString());
                Integer newsCatalogId = newsCatalogService.saveIfNotExist(newsCatalog);
                news.setCatalog(newsCatalogId);
                batchList.add(news);

                // 列表里的url
                subUrl.add(item.get("url").toString());
            }
            // 从页面发现后续的url地址来抓取
            page.addTargetRequests(subUrl);
            newsService.saveBatch(batchList);
        }
        page.putField("title", page.getHtml().css("div.LEFT h1").all());
        page.putField("content", page.getHtml().css("div.content-article").all());
    }

    @Override
    public Site getSite() {
        return site;
    }
}
