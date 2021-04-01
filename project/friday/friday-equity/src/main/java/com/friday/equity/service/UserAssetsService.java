package com.friday.equity.service;

import com.friday.equity.entity.UserAssets;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (UserAssets)表服务接口
 *
 * @author makejava
 * @since 2021-03-30 14:33:52
 */
public interface UserAssetsService {

    /**
     * 通过ID查询单条数据
     *
     * @param assetsId 主键
     * @return 实例对象
     */
    UserAssets queryById(Integer assetsId);

    /**
     * 通过实体类查询分页数据
     *
     * @param userAssets
     * @return 实例对象列表
     */
    PageInfo<UserAssets> queryAllByEntity(UserAssets userAssets);

    /**
     * 新增数据
     *
     * @param userAssets 实例对象
     * @return 实例对象
     */
    UserAssets insert(UserAssets userAssets);

    /**
     * 修改数据
     *
     * @param userAssets 实例对象
     * @return 实例对象
     */
    UserAssets update(UserAssets userAssets);

    /**
     * 通过主键删除数据
     *
     * @param assetsId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer assetsId);

    Map<String, Object> getAssetsCollection(UserAssets userAssets);
}