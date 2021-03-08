package com.friday.bills.service.impl;

import com.friday.bills.entity.UserIncome;
import com.friday.bills.mapper.UserIncomeMapper;
import com.friday.bills.service.UserIncomeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j;
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
@Log4j
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
     * 通过实体作为筛选条件查询
     *
     * @param userIncome 实例对象
     * @return 对象列表
     */
    @Override
    public PageInfo<UserIncome> queryAllByEntity(UserIncome userIncome) {
        PageHelper.startPage(userIncome.getOffset(),userIncome.getLimit());
        List<UserIncome> queryAll = userIncomeMapper.queryAll(userIncome);
        PageInfo<UserIncome> pageInfo = new PageInfo<>(queryAll);
        return pageInfo;
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

    @Override
    public List<String> findType() {
        return userIncomeMapper.findType();
    }
}