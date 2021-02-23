package com.friday.bills.service.impl;

import com.friday.bills.entity.UserExpenses;
import com.friday.bills.mapper.UserExpensesMapper;
import com.friday.bills.service.UserExpensesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserExpenses)表服务实现类
 *
 * @author makejava
 * @since 2021-02-23 15:16:45
 */
@Service("userExpensesService")
public class UserExpensesServiceImpl implements UserExpensesService {
    @Resource
    private UserExpensesMapper userExpensesMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param expensesId 主键
     * @return 实例对象
     */
    @Override
    public UserExpenses queryById(Integer expensesId) {
        return this.userExpensesMapper.queryById(expensesId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserExpenses> queryAllByLimit(int offset, int limit) {
        return this.userExpensesMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userExpenses 实例对象
     * @return 实例对象
     */
    @Override
    public UserExpenses insert(UserExpenses userExpenses) {
        this.userExpensesMapper.insert(userExpenses);
        return userExpenses;
    }

    /**
     * 修改数据
     *
     * @param userExpenses 实例对象
     * @return 实例对象
     */
    @Override
    public UserExpenses update(UserExpenses userExpenses) {
        this.userExpensesMapper.update(userExpenses);
        return this.queryById(userExpenses.getExpensesId());
    }

    /**
     * 通过主键删除数据
     *
     * @param expensesId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer expensesId) {
        return this.userExpensesMapper.deleteById(expensesId) > 0;
    }
}