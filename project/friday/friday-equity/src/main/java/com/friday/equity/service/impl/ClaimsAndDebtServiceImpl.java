package com.friday.equity.service.impl;

import com.friday.equity.entity.ClaimsAndDebt;
import com.friday.equity.mapper.ClaimsAndDebtMapper;
import com.friday.equity.service.ClaimsAndDebtService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (ClaimsAndDebt)表服务实现类
 *
 * @author makejava
 * @since 2021-03-30 14:35:58
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
     * 通过实体类查询分页数据
     *
     * @param claimsAndDebt
     * @return 实例对象列表
     */
     @Override
    public PageInfo<ClaimsAndDebt> queryAllByEntity(ClaimsAndDebt claimsAndDebt) {
        PageHelper.startPage(claimsAndDebt.getOffset(),claimsAndDebt.getLimit());
        List<ClaimsAndDebt> queryAll = claimsAndDebtMapper.queryAll(claimsAndDebt);
        PageInfo<ClaimsAndDebt> pageInfo = new PageInfo<>(queryAll);
        return pageInfo;
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

    @Override
    public Map<String, Object> getCADCollection(ClaimsAndDebt claimsAndDebt) {
        Map<String,Object> map = new HashMap<>();
//        债务占比图
        List<Map> claimsGroup = claimsAndDebtMapper.claimsGroup();
        map.put("claimsGroup",claimsGroup);

//        债权占比图
        List<Map> debtGroup = claimsAndDebtMapper.debtGroup();
        map.put("debtGroup",debtGroup);
//        债务总额
        map.put("sumClaim",claimsAndDebtMapper.sumClaim());

//        债权总额
        map.put("sumDebt",claimsAndDebtMapper.sumDebt());

//        最高债务额
        map.put("maxClaim",claimsAndDebtMapper.maxClaim());

//        最高债权额
        map.put("maxDebt",claimsAndDebtMapper.maxDebt());

        return map;
    }


}