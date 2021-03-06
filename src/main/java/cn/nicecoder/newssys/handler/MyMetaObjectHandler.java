package cn.nicecoder.newssys.handler;

import cn.nicecoder.newssys.enums.StatusEnum;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybatisplus填充器
 * @author: longt
 * @Param:
 * @return:
 * @date: 2020/12/21 下午7:45
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private final static Integer CLICK_NUM_INIT = 0;

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.setFieldValByName("click", CLICK_NUM_INIT, metaObject);
        this.setFieldValByName("status", StatusEnum.NORMAL.getCode(), metaObject);
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
