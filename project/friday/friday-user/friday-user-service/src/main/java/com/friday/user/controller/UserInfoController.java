package com.friday.user.controller;

import com.friday.user.entity.UserInfo;
import com.friday.user.service.UserInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户(UserInfo)表控制层
 *
 * @author makejava
 * @since 2021-02-23 11:19:12
 */
@RestController
@RequestMapping("userInfo")
public class UserInfoController {
    /**
     * 服务对象
     */
    @Resource
    private UserInfoService userInfoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserInfo selectOne(Integer id) {
        return this.userInfoService.queryById(id);
    }
    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    @GetMapping("query")
    public ResponseEntity<UserInfo> queryUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        UserInfo user = this.userInfoService.queryUser(username, password);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(user);
    }
}