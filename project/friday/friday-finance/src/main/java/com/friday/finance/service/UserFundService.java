package com.friday.finance.service;

import com.friday.finance.entity.UserFund;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * (UserFund)表服务接口
 *
 * @author makejava
 * @since 2021-02-25 16:32:31
 */
public interface UserFundService {

    /**
     * 通过ID查询单条数据
     *
     * @param fundId 主键
     * @return 实例对象
     */
    UserFund queryById(Integer fundId);

    /**
     * 通过实体类查询分页数据
     *
     * @param userFund
     * @return 实例对象列表
     */
    PageInfo<UserFund> queryAllByEntity(UserFund userFund);

    /**
     * 新增数据
     *
     * @param userFund 实例对象
     * @return 实例对象
     */
    UserFund insert(UserFund userFund);

    /**
     * 修改数据
     *
     * @param userFund 实例对象
     * @return 实例对象
     */
    UserFund update(UserFund userFund);

    /**
     * 通过主键删除数据
     *
     * @param fundId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer fundId);

}