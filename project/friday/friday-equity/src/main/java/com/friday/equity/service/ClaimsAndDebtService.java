package com.friday.equity.service;

import com.friday.equity.entity.ClaimsAndDebt;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (ClaimsAndDebt)表服务接口
 *
 * @author makejava
 * @since 2021-03-30 14:35:58
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
     * 通过实体类查询分页数据
     *
     * @param claimsAndDebt
     * @return 实例对象列表
     */
    PageInfo<ClaimsAndDebt> queryAllByEntity(ClaimsAndDebt claimsAndDebt);

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