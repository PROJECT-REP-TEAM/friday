package com.friday.bills.controller;

import com.friday.bills.entity.UserIncome;
import com.friday.bills.service.UserIncomeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (UserIncome)表控制层
 *
 * @author makejava
 * @since 2021-02-23 15:10:05
 */
@RestController
@RequestMapping("userIncome")
public class UserIncomeController {
    /**
     * 服务对象
     */
    @Resource
    private UserIncomeService userIncomeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserIncome selectOne(Integer id) {
        return this.userIncomeService.queryById(id);
    }

}