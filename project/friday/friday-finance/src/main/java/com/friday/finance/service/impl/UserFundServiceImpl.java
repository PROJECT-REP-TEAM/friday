package com.friday.finance.service.impl;

import com.friday.finance.entity.UserFund;
import com.friday.finance.mapper.UserFundMapper;
import com.friday.finance.service.UserFundService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserFund)表服务实现类
 *
 * @author makejava
 * @since 2021-02-23 15:55:33
 */
@Service("userFundService")
public class UserFundServiceImpl implements UserFundService {
    @Resource
    private UserFundMapper userFundMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param fundId 主键
     * @return 实例对象
     */
    @Override
    public UserFund queryById(Integer fundId) {
        return this.userFundMapper.queryById(fundId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserFund> queryAllByLimit(int offset, int limit) {
        return this.userFundMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userFund 实例对象
     * @return 实例对象
     */
    @Override
    public UserFund insert(UserFund userFund) {
        this.userFundMapper.insert(userFund);
        return userFund;
    }

    /**
     * 修改数据
     *
     * @param userFund 实例对象
     * @return 实例对象
     */
    @Override
    public UserFund update(UserFund userFund) {
        this.userFundMapper.update(userFund);
        return this.queryById(userFund.getFundId());
    }

    /**
     * 通过主键删除数据
     *
     * @param fundId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer fundId) {
        return this.userFundMapper.deleteById(fundId) > 0;
    }
}