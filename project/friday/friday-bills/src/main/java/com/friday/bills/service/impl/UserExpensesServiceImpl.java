package com.friday.bills.service.impl;

import com.friday.bills.entity.UserExpenses;
import com.friday.bills.mapper.UserExpensesMapper;
import com.friday.bills.service.UserExpensesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (UserExpenses)表服务实现类
 *
 * @author makejava
 * @since 2021-02-25 15:25:47
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
     * 通过实体类查询分页数据
     *
     * @param userExpenses
     * @return 实例对象列表
     */
     @Override
    public PageInfo<UserExpenses> queryAllByEntity(UserExpenses userExpenses) {
        PageHelper.startPage(userExpenses.getOffset(),userExpenses.getLimit());
        List<UserExpenses> queryAll = userExpensesMapper.queryAll(userExpenses);
        PageInfo<UserExpenses> pageInfo = new PageInfo<>(queryAll);
        return pageInfo;
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

    @Override
    public List<String> findType() {
        return userExpensesMapper.findType();
    }
}