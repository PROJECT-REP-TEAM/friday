package com.friday.equity.mapper;

import com.friday.equity.entity.ClaimsAndDebt;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (ClaimsAndDebt)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-30 14:35:58
 */
public interface ClaimsAndDebtMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param cadId 主键
     * @return 实例对象
     */
    ClaimsAndDebt queryById(Integer cadId);



    /**
     * 通过实体作为筛选条件查询
     *
     * @param claimsAndDebt 实例对象
     * @return 对象列表
     */
    List<ClaimsAndDebt> queryAll(ClaimsAndDebt claimsAndDebt);

    /**
     * 新增数据
     *
     * @param claimsAndDebt 实例对象
     * @return 影响行数
     */
    int insert(ClaimsAndDebt claimsAndDebt);

    /**
     * 修改数据
     *
     * @param claimsAndDebt 实例对象
     * @return 影响行数
     */
    int update(ClaimsAndDebt claimsAndDebt);

    /**
     * 通过主键删除数据
     *
     * @param cadId 主键
     * @return 影响行数
     */
    int deleteById(Integer cadId);

}