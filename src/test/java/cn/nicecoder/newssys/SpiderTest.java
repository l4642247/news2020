package cn.nicecoder.newssys;

import cn.nicecoder.newssys.service.biz.SpiderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description: 爬虫测试
 * @author: longt
 * @date: 2020/12/18 上午9:42
 */
@SpringBootTest
public class SpiderTest {

    @Autowired
    SpiderService spiderService;

    @Test
    public void say(){
        spiderService.start();
    }

}
