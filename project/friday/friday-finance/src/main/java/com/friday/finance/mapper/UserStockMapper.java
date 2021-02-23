package com.friday.finance.mapper;

import com.friday.finance.entity.UserStock;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (UserStock)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-23 15:57:57
 */
public interface UserStockMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param stockId 主键
     * @return 实例对象
     */
    UserStock queryById(Integer stockId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserStock> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userStock 实例对象
     * @return 对象列表
     */
    List<UserStock> queryAll(UserStock userStock);

    /**
     * 新增数据
     *
     * @param userStock 实例对象
     * @return 影响行数
     */
    int insert(UserStock userStock);

    /**
     * 修改数据
     *
     * @param userStock 实例对象
     * @return 影响行数
     */
    int update(UserStock userStock);

    /**
     * 通过主键删除数据
     *
     * @param stockId 主键
     * @return 影响行数
     */
    int deleteById(Integer stockId);

}