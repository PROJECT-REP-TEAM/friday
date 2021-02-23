package com.friday.equity.service;

import com.friday.equity.entity.UserFixedAssets;
import java.util.List;

/**
 * (UserFixedAssets)表服务接口
 *
 * @author makejava
 * @since 2021-02-23 16:14:04
 */
public interface UserFixedAssetsService {

    /**
     * 通过ID查询单条数据
     *
     * @param fixedId 主键
     * @return 实例对象
     */
    UserFixedAssets queryById(Integer fixedId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserFixedAssets> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userFixedAssets 实例对象
     * @return 实例对象
     */
    UserFixedAssets insert(UserFixedAssets userFixedAssets);

    /**
     * 修改数据
     *
     * @param userFixedAssets 实例对象
     * @return 实例对象
     */
    UserFixedAssets update(UserFixedAssets userFixedAssets);

    /**
     * 通过主键删除数据
     *
     * @param fixedId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer fixedId);

}