package com.friday.finance.controller;

import com.friday.finance.entity.UserStock;
import com.friday.finance.service.UserStockService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (UserStock)表控制层
 *
 * @author makejava
 * @since 2021-02-23 15:57:57
 */
@RestController
@RequestMapping("userStock")
public class UserStockController {
    /**
     * 服务对象
     */
    @Resource
    private UserStockService userStockService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserStock selectOne(Integer id) {
        return this.userStockService.queryById(id);
    }

}