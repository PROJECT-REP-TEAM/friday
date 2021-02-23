package com.friday.bills.service;

import com.friday.bills.entity.UserIncome;
import java.util.List;

/**
 * (UserIncome)表服务接口
 *
 * @author makejava
 * @since 2021-02-23 15:10:04
 */
public interface UserIncomeService {

    /**
     * 通过ID查询单条数据
     *
     * @param incomeId 主键
     * @return 实例对象
     */
    UserIncome queryById(Integer incomeId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserIncome> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userIncome 实例对象
     * @return 实例对象
     */
    UserIncome insert(UserIncome userIncome);

    /**
     * 修改数据
     *
     * @param userIncome 实例对象
     * @return 实例对象
     */
    UserIncome update(UserIncome userIncome);

    /**
     * 通过主键删除数据
     *
     * @param incomeId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer incomeId);

}