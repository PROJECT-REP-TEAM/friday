package com.friday.bills.service.impl;

import com.friday.bills.entity.UserIncome;
import com.friday.bills.mapper.UserIncomeMapper;
import com.friday.bills.service.UserIncomeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserIncome)表服务实现类
 *
 * @author makejava
 * @since 2021-02-23 15:10:05
 */
@Service("userIncomeService")
public class UserIncomeServiceImpl implements UserIncomeService {
    @Resource
    private UserIncomeMapper userIncomeMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param incomeId 主键
     * @return 实例对象
     */
    @Override
    public UserIncome queryById(Integer incomeId) {
        return this.userIncomeMapper.queryById(incomeId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserIncome> queryAllByLimit(int offset, int limit) {
        return this.userIncomeMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userIncome 实例对象
     * @return 实例对象
     */
    @Override
    public UserIncome insert(UserIncome userIncome) {
        this.userIncomeMapper.insert(userIncome);
        return userIncome;
    }

    /**
     * 修改数据
     *
     * @param userIncome 实例对象
     * @return 实例对象
     */
    @Override
    public UserIncome update(UserIncome userIncome) {
        this.userIncomeMapper.update(userIncome);
        return this.queryById(userIncome.getIncomeId());
    }

    /**
     * 通过主键删除数据
     *
     * @param incomeId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer incomeId) {
        return this.userIncomeMapper.deleteById(incomeId) > 0;
    }
}