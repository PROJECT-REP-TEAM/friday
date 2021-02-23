package com.friday.finance.service;

import com.friday.finance.entity.UserFund;
import java.util.List;

/**
 * (UserFund)表服务接口
 *
 * @author makejava
 * @since 2021-02-23 15:55:33
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
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserFund> queryAllByLimit(int offset, int limit);

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