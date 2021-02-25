package com.friday.equity.mapper;

import com.friday.equity.entity.UserAssets;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (UserAssets)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-25 16:31:20
 */
public interface UserAssetsMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param assetsId 主键
     * @return 实例对象
     */
    UserAssets queryById(Integer assetsId);



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
     * @param assetsId 主键
     * @return 影响行数
     */
    int deleteById(Integer assetsId);

}