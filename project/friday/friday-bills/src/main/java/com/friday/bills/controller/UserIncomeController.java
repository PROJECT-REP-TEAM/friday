package com.friday.bills.controller;

import com.friday.bills.client.UserClient;
import com.friday.bills.entity.UserIncome;
import com.friday.bills.service.UserIncomeService;
import com.friday.common.utils.TimeUtils;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserClient userClient;
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
    public ResponseEntity<Map<String,Object>> selectAll(
            @RequestParam(value = "offset" ,required = false) Integer offset,
            Integer limit ,   UserIncome userIncome){
        Map<String,Object> map = new HashMap<>();
        if (offset == null || offset == 0){
            userIncome.setOffset(1);
        }else{
            userIncome.setOffset(offset);
        }
        if (StringUtils.isNotBlank(userIncome.getIncomeTime()) && !",".equals(userIncome.getIncomeTime())){
            String[] split = StringUtils.split(userIncome.getIncomeTime(), ',');
            userIncome.setDate1(split[0].replaceAll("-",""));
            userIncome.setDate2(split[1].replaceAll("-",""));
        }else {
            userIncome.setIncomeTime(null);
        }
        userIncome.setLimit(limit);
        PageInfo<UserIncome> allData = userIncomeService.queryAllByEntity(userIncome);
        map.put("count",allData.getTotal());
        map.put("data",allData.getList());
        return ResponseEntity.ok(map);
    }


    @GetMapping("findType")
    public ResponseEntity<List<String>> findType(){
        List<String> list = userIncomeService.findType();
        return ResponseEntity.ok(list);
    }


    /**
     * 新增数据
     *
     * @param userIncome 实例对象
     * @return Void
     */
    @GetMapping("insertIncome")
    public ResponseEntity<Void> insertIncome(UserIncome userIncome){
        userIncome.setIncomeTime(TimeUtils.getCurrentDateString("YYYYMMdd"));
        Integer userId = userClient.findId(userIncome.getIncomeUser()).getUserId();
        userIncome.setIncomeUserId(userId);
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
    @PostMapping("updateIncome")
    public ResponseEntity<Void> updateIncome(@RequestBody UserIncome userIncome){
        if (userIncome.getIncomeId() == null || userIncome.getIncomeId() == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        userIncomeService.update(userIncome);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}