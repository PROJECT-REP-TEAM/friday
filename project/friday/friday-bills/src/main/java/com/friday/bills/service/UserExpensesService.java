package com.friday.bills.service;

import com.friday.bills.entity.UserExpenses;

import java.io.IOException;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jxl.write.WriteException;

/**
 * (UserExpenses)表服务接口
 *
 * @author makejava
 * @since 2021-02-25 15:25:47
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
     * 通过实体类查询分页数据
     *
     * @param userExpenses
     * @return 实例对象列表
     */
    PageInfo<UserExpenses> queryAllByEntity(UserExpenses userExpenses);

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

    List<String> findType();

    void downloadFile(UserExpenses userExpenses) throws IOException, WriteException;
}