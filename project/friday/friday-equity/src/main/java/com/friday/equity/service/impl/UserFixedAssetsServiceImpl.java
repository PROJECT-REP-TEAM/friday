package com.friday.equity.service.impl;

import com.friday.equity.entity.UserFixedAssets;
import com.friday.equity.mapper.UserFixedAssetsMapper;
import com.friday.equity.service.UserFixedAssetsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserFixedAssets)表服务实现类
 *
 * @author makejava
 * @since 2021-02-23 16:14:04
 */
@Service("userFixedAssetsService")
public class UserFixedAssetsServiceImpl implements UserFixedAssetsService {
    @Resource
    private UserFixedAssetsMapper userFixedAssetsMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param fixedId 主键
     * @return 实例对象
     */
    @Override
    public UserFixedAssets queryById(Integer fixedId) {
        return this.userFixedAssetsMapper.queryById(fixedId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserFixedAssets> queryAllByLimit(int offset, int limit) {
        return this.userFixedAssetsMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userFixedAssets 实例对象
     * @return 实例对象
     */
    @Override
    public UserFixedAssets insert(UserFixedAssets userFixedAssets) {
        this.userFixedAssetsMapper.insert(userFixedAssets);
        return userFixedAssets;
    }

    /**
     * 修改数据
     *
     * @param userFixedAssets 实例对象
     * @return 实例对象
     */
    @Override
    public UserFixedAssets update(UserFixedAssets userFixedAssets) {
        this.userFixedAssetsMapper.update(userFixedAssets);
        return this.queryById(userFixedAssets.getFixedId());
    }

    /**
     * 通过主键删除数据
     *
     * @param fixedId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer fixedId) {
        return this.userFixedAssetsMapper.deleteById(fixedId) > 0;
    }
}