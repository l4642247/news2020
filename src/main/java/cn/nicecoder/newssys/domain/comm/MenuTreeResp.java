package cn.nicecoder.newssys.domain.comm;

import cn.nicecoder.newssys.domain.response.base.MenuNodeVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 树接口返回
 * @author: xxxxx
 * @date: 2020/12/24 上午11:13
 */
@Data
public class MenuTreeResp implements Serializable {

    private static final long serialVersionUID=1L;

    private Status status;
    private List<MenuNodeVO> data;

    public MenuTreeResp(List<MenuNodeVO> data){
        this.data = data;
        this.status = new Status(200, "操作成功");
    }
}

