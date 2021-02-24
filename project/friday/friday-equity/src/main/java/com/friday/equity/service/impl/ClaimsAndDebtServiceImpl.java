package com.friday.equity.service.impl;

import com.friday.equity.entity.ClaimsAndDebt;
import com.friday.equity.mapper.ClaimsAndDebtMapper;
import com.friday.equity.service.ClaimsAndDebtService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ClaimsAndDebt)表服务实现类
 *
 * @author makejava
 * @since 2021-02-23 16:14:49
 */
@Service("claimsAndDebtService")
public class ClaimsAndDebtServiceImpl implements ClaimsAndDebtService {
    @Resource
    private ClaimsAndDebtMapper claimsAndDebtMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param cadId 主键
     * @return 实例对象
     */
    @Override
    public ClaimsAndDebt queryById(Integer cadId) {
        return this.claimsAndDebtMapper.queryById(cadId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<ClaimsAndDebt> queryAllByLimit(int offset, int limit) {
        return this.claimsAndDebtMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param claimsAndDebt 实例对象
     * @return 实例对象
     */
    @Override
    public ClaimsAndDebt insert(ClaimsAndDebt claimsAndDebt) {
        this.claimsAndDebtMapper.insert(claimsAndDebt);
        return claimsAndDebt;
    }

    /**
     * 修改数据
     *
     * @param claimsAndDebt 实例对象
     * @return 实例对象
     */
    @Override
    public ClaimsAndDebt update(ClaimsAndDebt claimsAndDebt) {
        this.claimsAndDebtMapper.update(claimsAndDebt);
        return this.queryById(claimsAndDebt.getCadId());
    }

    /**
     * 通过主键删除数据
     *
     * @param cadId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer cadId) {
        return this.claimsAndDebtMapper.deleteById(cadId) > 0;
    }
}