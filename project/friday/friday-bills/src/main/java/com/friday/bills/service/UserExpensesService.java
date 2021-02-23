package com.friday.bills.service;

import com.friday.bills.entity.UserExpenses;
import java.util.List;

/**
 * (UserExpenses)表服务接口
 *
 * @author makejava
 * @since 2021-02-23 15:16:45
 */
public interface UserExpensesService {

    /**
     * 通过ID查询单条数据
     *
     * @param expensesId 主键
     * @return 实例对象
     */
    UserExpenses queryById(Integer expensesId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserExpenses> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userExpenses 实例对象
     * @return 实例对象
     */
    UserExpenses insert(UserExpenses userExpenses);

    /**
     * 修改数据
     *
     * @param userExpenses 实例对象
     * @return 实例对象
     */
    UserExpenses update(UserExpenses userExpenses);

    /**
     * 通过主键删除数据
     *
     * @param expensesId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer expensesId);

}