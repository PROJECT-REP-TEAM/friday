package com.friday.bills.controller;

import com.friday.bills.entity.UserIncome;
import com.friday.bills.service.UserIncomeService;
import com.github.pagehelper.PageInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @GetMapping("selectAll")
    public ResponseEntity<Map<String,Object>> selectAll(Integer offset ,Integer limit ,  UserIncome userIncome){
        Map<String,Object> map = new HashMap<>();
        userIncome.setOffset(offset);
        userIncome.setLimit(limit);
        PageInfo<UserIncome> userIncomes = userIncomeService.queryAllByEntity(userIncome);
        map.put("count",userIncomes.getTotal());
        map.put("data",userIncomes.getList());
        return ResponseEntity.ok(map);
    }



    /**
     * 新增数据
     *
     * @param userIncome 实例对象
     * @return Void
     */
    @PostMapping("insertIncome")
    public ResponseEntity<Void> insertIncome(@RequestBody UserIncome userIncome){
        userIncomeService.insert(userIncome);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    /**
     * 通过主键删除数据
     *
     * @param incomeId 主键
     * @return 是否成功
     */
    @GetMapping("deleteIncome")
    public ResponseEntity<Boolean> deleteIncome(Integer incomeId){
        boolean b = userIncomeService.deleteById(incomeId);
        return ResponseEntity.ok(b);
    }


    /**
     * 修改数据
     *
     * @param userIncome 实例对象
     * @return Void
     */
    @PutMapping("updateIncome")
    public ResponseEntity<Void> updateIncome(@RequestBody UserIncome userIncome){
        userIncomeService.update(userIncome);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}