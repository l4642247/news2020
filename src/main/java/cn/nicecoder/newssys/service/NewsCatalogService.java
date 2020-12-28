package cn.nicecoder.newssys.service;

import cn.nicecoder.newssys.entity.NewsCatalog;
import cn.nicecoder.newssys.entity.VO.NewsCatalogVO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lon't
 * @since 2020-12-18
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public interface NewsCatalogService extends IService<NewsCatalog> {

    /**
     * 保存类别
     * @author: longt
     * @Param: [newsCatalog]
     * @return: cn.nicecoder.newssys.entity.NewsCatalog
     * @date: 2020/12/18 下午2:54
     */
    Integer saveIfNotExist(NewsCatalog newsCatalog);

    /**
     * 查询所有类别信息
     * @author: longt
     * @Param: []
     * @return: java.util.List<cn.nicecoder.newssys.entity.VO.NewsCatalogVO>
     * @date: 2020/12/23 下午3:54
     */
    List<NewsCatalogVO> listAllCatalog();

}
