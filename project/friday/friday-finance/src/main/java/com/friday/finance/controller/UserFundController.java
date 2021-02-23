package com.friday.finance.controller;

import com.friday.finance.entity.UserFund;
import com.friday.finance.service.UserFundService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (UserFund)表控制层
 *
 * @author makejava
 * @since 2021-02-23 15:55:34
 */
@RestController
@RequestMapping("userFund")
public class UserFundController {
    /**
     * 服务对象
     */
    @Resource
    private UserFundService userFundService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserFund selectOne(Integer id) {
        return this.userFundService.queryById(id);
    }

}