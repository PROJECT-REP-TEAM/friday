package com.friday.equity.controller;

import com.friday.equity.entity.UserFixedAssets;
import com.friday.equity.service.UserFixedAssetsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (UserFixedAssets)表控制层
 *
 * @author makejava
 * @since 2021-02-23 16:14:05
 */
@RestController
@RequestMapping("userFixedAssets")
public class UserFixedAssetsController {
    /**
     * 服务对象
     */
    @Resource
    private UserFixedAssetsService userFixedAssetsService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserFixedAssets selectOne(Integer id) {
        return this.userFixedAssetsService.queryById(id);
    }

}