package cn.nicecoder.newssys.service.biz.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.nicecoder.newssys.domain.entity.biz.NewsCatalog;
import cn.nicecoder.newssys.domain.response.biz.NewsCatalogVO;
import cn.nicecoder.newssys.common.enums.StatusEnum;
import cn.nicecoder.newssys.mapper.biz.NewsCatalogMapper;
import cn.nicecoder.newssys.service.biz.NewsCatalogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lon't
 * @since 2020-12-18
 */
@Service
public class NewsCatalogServiceImpl extends ServiceImpl<NewsCatalogMapper, NewsCatalog> implements NewsCatalogService {

    @Autowired
    private NewsCatalogMapper newsCatalogMapper;

    @Override
    public Integer saveIfNotExist(NewsCatalog newsCatalog) {
        NewsCatalog result = newsCatalogMapper.selectOne(
                new LambdaQueryWrapper<NewsCatalog>().eq(NewsCatalog::getName, newsCatalog.getName()));
        if(ObjectUtil.isNotEmpty(result)){
            return result.getId();
        }
        newsCatalogMapper.insert(newsCatalog);
        return newsCatalog.getId();
    }

    @Override
    public List<NewsCatalogVO> listAllCatalog() {
        List<NewsCatalog> newsCatalogList = this.list(new LambdaQueryWrapper<NewsCatalog>()
                .eq(NewsCatalog::getStatus, StatusEnum.NORMAL.getCode()));
        List<NewsCatalogVO> newsCatalogVOList = newsCatalogList.stream().map(e->{
            NewsCatalogVO newsCatalogVO = new NewsCatalogVO();
            newsCatalogVO.setId(e.getId());
            newsCatalogVO.setName(e.getName());
            return newsCatalogVO;
        }).collect(Collectors.toList());
        return newsCatalogVOList;
    }
}
