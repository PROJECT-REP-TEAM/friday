package com.friday.finance.service.impl;

import com.friday.finance.entity.UserFund;
import com.friday.finance.mapper.UserFundMapper;
import com.friday.finance.service.UserFundService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (UserFund)表服务实现类
 *
 * @author makejava
 * @since 2021-02-25 16:32:31
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
     * 通过实体类查询分页数据
     *
     * @param userFund
     * @return 实例对象列表
     */
     @Override
    public PageInfo<UserFund> queryAllByEntity(UserFund userFund) {
        PageHelper.startPage(userFund.getOffset(),userFund.getLimit());
        List<UserFund> queryAll = userFundMapper.queryAll(userFund);
        PageInfo<UserFund> pageInfo = new PageInfo<>(queryAll);
        return pageInfo;
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