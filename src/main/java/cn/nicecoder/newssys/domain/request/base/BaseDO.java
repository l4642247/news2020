package cn.nicecoder.newssys.domain.request.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * BaseDO,其他类的不用重复写了，继承就完事了
 * @author: longt
 * @date: 2020/12/24 上午10:31
 */
@Data
public class BaseDO implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;

    private Date timeFrom;

    private Date timeTo;

    private String status;

    private long current;

    private long size;

    private long pages;

}
