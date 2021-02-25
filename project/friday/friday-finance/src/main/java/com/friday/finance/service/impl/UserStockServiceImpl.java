package com.friday.finance.service.impl;

import com.friday.finance.entity.UserStock;
import com.friday.finance.mapper.UserStockMapper;
import com.friday.finance.service.UserStockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (UserStock)表服务实现类
 *
 * @author makejava
 * @since 2021-02-25 16:34:00
 */
@Service("userStockService")
public class UserStockServiceImpl implements UserStockService {
    @Resource
    private UserStockMapper userStockMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param stockId 主键
     * @return 实例对象
     */
    @Override
    public UserStock queryById(Integer stockId) {
        return this.userStockMapper.queryById(stockId);
    }
    
     /**
     * 通过实体类查询分页数据
     *
     * @param userStock
     * @return 实例对象列表
     */
     @Override
    public PageInfo<UserStock> queryAllByEntity(UserStock userStock) {
        PageHelper.startPage(userStock.getOffset(),userStock.getLimit());
        List<UserStock> queryAll = userStockMapper.queryAll(userStock);
        PageInfo<UserStock> pageInfo = new PageInfo<>(queryAll);
        return pageInfo;
    }


    /**
     * 新增数据
     *
     * @param userStock 实例对象
     * @return 实例对象
     */
    @Override
    public UserStock insert(UserStock userStock) {
        this.userStockMapper.insert(userStock);
        return userStock;
    }

    /**
     * 修改数据
     *
     * @param userStock 实例对象
     * @return 实例对象
     */
    @Override
    public UserStock update(UserStock userStock) {
        this.userStockMapper.update(userStock);
        return this.queryById(userStock.getStockId());
    }

    /**
     * 通过主键删除数据
     *
     * @param stockId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer stockId) {
        return this.userStockMapper.deleteById(stockId) > 0;
    }
}