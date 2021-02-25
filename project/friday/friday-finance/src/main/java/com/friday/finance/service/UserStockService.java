package com.friday.finance.service;

import com.friday.finance.entity.UserStock;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (UserStock)表服务接口
 *
 * @author makejava
 * @since 2021-02-25 16:34:00
 */
public interface UserStockService {

    /**
     * 通过ID查询单条数据
     *
     * @param stockId 主键
     * @return 实例对象
     */
    UserStock queryById(Integer stockId);

    /**
     * 通过实体类查询分页数据
     *
     * @param userStock
     * @return 实例对象列表
     */
    PageInfo<UserStock> queryAllByEntity(UserStock userStock);

    /**
     * 新增数据
     *
     * @param userStock 实例对象
     * @return 实例对象
     */
    UserStock insert(UserStock userStock);

    /**
     * 修改数据
     *
     * @param userStock 实例对象
     * @return 实例对象
     */
    UserStock update(UserStock userStock);

    /**
     * 通过主键删除数据
     *
     * @param stockId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer stockId);

}