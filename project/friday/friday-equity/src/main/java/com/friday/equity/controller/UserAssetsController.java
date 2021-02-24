package com.friday.equity.controller;

import com.friday.equity.entity.UserAssets;
import com.friday.equity.service.UserAssetsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (UserAssets)表控制层
 *
 * @author makejava
 * @since 2021-02-24 15:18:24
 */
@RestController
@RequestMapping("userAssets")
public class UserAssetsController {
    /**
     * 服务对象
     */
    @Resource
    private UserAssetsService userAssetsService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserAssets selectOne(Integer id) {
        return this.userAssetsService.queryById(id);
    }

}