package com.friday.bills.controller;

import com.friday.bills.entity.UserExpenses;
import com.friday.bills.service.UserExpensesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (UserExpenses)表控制层
 *
 * @author makejava
 * @since 2021-02-23 15:16:45
 */
@RestController
@RequestMapping("userExpenses")
public class UserExpensesController {
    /**
     * 服务对象
     */
    @Resource
    private UserExpensesService userExpensesService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserExpenses selectOne(Integer id) {
        return this.userExpensesService.queryById(id);
    }

}