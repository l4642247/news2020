package cn.nicecoder.newssys.domain.comm;

import cn.nicecoder.newssys.common.enums.CommonEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Rest接口通用返回
 * @author: xxxxx
 * @Param:
 * @return:
 * @date: 2020/12/24 上午11:13
 */
@Data
@AllArgsConstructor
@ToString
public class Resp {

    @ApiModelProperty(value = "响应码")
    private final Integer code;

    @ApiModelProperty(value = "响应消息")
    private final String msg;

    @ApiModelProperty(value = "响应数据")
    private final Object data;

    @ApiModelProperty(value = "数据总数")
    private final Long count;

    @ApiModelProperty(value = "时间戳")
    private final LocalDateTime timestamp = LocalDateTime.now();

    public static cn.nicecoder.newssys.domain.comm.Resp success() {
        return new cn.nicecoder.newssys.domain.comm.Resp(CommonEnum.RESP_LAYUI_OK.getCode(),
                CommonEnum.RESP_LAYUI_OK.getDesc(), null, null);
    }

    public static cn.nicecoder.newssys.domain.comm.Resp success(Object data) {
        return new cn.nicecoder.newssys.domain.comm.Resp(CommonEnum.RESP_LAYUI_OK.getCode(),
                CommonEnum.RESP_LAYUI_OK.getDesc(), data, null);
    }

    public static cn.nicecoder.newssys.domain.comm.Resp success(Object data, Long count) {
        if(count == 0){
            return new cn.nicecoder.newssys.domain.comm.Resp(CommonEnum.RESP_LAYUI_EMPTY.getCode(),
                    CommonEnum.RESP_LAYUI_EMPTY.getDesc(), data, count);
        }
        return new cn.nicecoder.newssys.domain.comm.Resp(CommonEnum.RESP_LAYUI_OK.getCode(),
                CommonEnum.RESP_LAYUI_OK.getDesc(), data, count);
    }

    public static cn.nicecoder.newssys.domain.comm.Resp success(Object data, String msg, Long count) {
        return new cn.nicecoder.newssys.domain.comm.Resp(0, msg, data, count);
    }

    public static cn.nicecoder.newssys.domain.comm.Resp fail(String msg) {
        return new cn.nicecoder.newssys.domain.comm.Resp(HttpStatus.INTERNAL_SERVER_ERROR.value() , msg, null, null);
    }
}

