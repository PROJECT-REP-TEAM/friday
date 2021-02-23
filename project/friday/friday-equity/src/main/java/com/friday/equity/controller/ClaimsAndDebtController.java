package com.friday.equity.controller;

import com.friday.equity.entity.ClaimsAndDebt;
import com.friday.equity.service.ClaimsAndDebtService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (ClaimsAndDebt)表控制层
 *
 * @author makejava
 * @since 2021-02-23 16:14:49
 */
@RestController
@RequestMapping("claimsAndDebt")
public class ClaimsAndDebtController {
    /**
     * 服务对象
     */
    @Resource
    private ClaimsAndDebtService claimsAndDebtService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public ClaimsAndDebt selectOne(Integer id) {
        return this.claimsAndDebtService.queryById(id);
    }

}