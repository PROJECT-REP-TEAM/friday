package com.friday.equity.service;

import com.friday.equity.entity.ClaimsAndDebt;
import java.util.List;

/**
 * (ClaimsAndDebt)表服务接口
 *
 * @author makejava
 * @since 2021-02-23 16:14:49
 */
public interface ClaimsAndDebtService {

    /**
     * 通过ID查询单条数据
     *
     * @param cadId 主键
     * @return 实例对象
     */
    ClaimsAndDebt queryById(Integer cadId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ClaimsAndDebt> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param claimsAndDebt 实例对象
     * @return 实例对象
     */
    ClaimsAndDebt insert(ClaimsAndDebt claimsAndDebt);

    /**
     * 修改数据
     *
     * @param claimsAndDebt 实例对象
     * @return 实例对象
     */
    ClaimsAndDebt update(ClaimsAndDebt claimsAndDebt);

    /**
     * 通过主键删除数据
     *
     * @param cadId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer cadId);

}