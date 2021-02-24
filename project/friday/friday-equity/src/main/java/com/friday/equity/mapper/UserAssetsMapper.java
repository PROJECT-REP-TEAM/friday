package com.friday.equity.mapper;

import com.friday.equity.entity.UserAssets;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (UserAssets)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-24 15:18:24
 */
public interface UserAssetsMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param fixedId 主键
     * @return 实例对象
     */
    UserAssets queryById(Integer fixedId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserAssets> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userAssets 实例对象
     * @return 对象列表
     */
    List<UserAssets> queryAll(UserAssets userAssets);

    /**
     * 新增数据
     *
     * @param userAssets 实例对象
     * @return 影响行数
     */
    int insert(UserAssets userAssets);

    /**
     * 修改数据
     *
     * @param userAssets 实例对象
     * @return 影响行数
     */
    int update(UserAssets userAssets);

    /**
     * 通过主键删除数据
     *
     * @param fixedId 主键
     * @return 影响行数
     */
    int deleteById(Integer fixedId);

}