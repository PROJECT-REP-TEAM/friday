package com.friday.finance.service.impl;

import com.friday.finance.entity.UserStock;
import com.friday.finance.mapper.UserStockMapper;
import com.friday.finance.service.UserStockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserStock)表服务实现类
 *
 * @author makejava
 * @since 2021-02-23 15:57:57
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
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserStock> queryAllByLimit(int offset, int limit) {
        return this.userStockMapper.queryAllByLimit(offset, limit);
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