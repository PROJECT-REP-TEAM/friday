package com.friday.equity.service.impl;

import com.friday.equity.entity.UserAssets;
import com.friday.equity.mapper.UserAssetsMapper;
import com.friday.equity.service.UserAssetsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (UserAssets)表服务实现类
 *
 * @author makejava
 * @since 2021-02-25 16:31:20
 */
@Service("userAssetsService")
public class UserAssetsServiceImpl implements UserAssetsService {
    @Resource
    private UserAssetsMapper userAssetsMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param assetsId 主键
     * @return 实例对象
     */
    @Override
    public UserAssets queryById(Integer assetsId) {
        return this.userAssetsMapper.queryById(assetsId);
    }
    
     /**
     * 通过实体类查询分页数据
     *
     * @param userAssets
     * @return 实例对象列表
     */
     @Override
    public PageInfo<UserAssets> queryAllByEntity(UserAssets userAssets) {
        PageHelper.startPage(userAssets.getOffset(),userAssets.getLimit());
        List<UserAssets> queryAll = userAssetsMapper.queryAll(userAssets);
        PageInfo<UserAssets> pageInfo = new PageInfo<>(queryAll);
        return pageInfo;
    }


    /**
     * 新增数据
     *
     * @param userAssets 实例对象
     * @return 实例对象
     */
    @Override
    public UserAssets insert(UserAssets userAssets) {
        this.userAssetsMapper.insert(userAssets);
        return userAssets;
    }

    /**
     * 修改数据
     *
     * @param userAssets 实例对象
     * @return 实例对象
     */
    @Override
    public UserAssets update(UserAssets userAssets) {
        this.userAssetsMapper.update(userAssets);
        return this.queryById(userAssets.getAssetsId());
    }

    /**
     * 通过主键删除数据
     *
     * @param assetsId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer assetsId) {
        return this.userAssetsMapper.deleteById(assetsId) > 0;
    }
}