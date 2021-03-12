package com.friday.finance.controller;

import com.friday.finance.entity.UserFund;
import com.friday.finance.service.UserFundService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

/**
 * (UserFund)表控制层
 *
 * @author makejava
 * @since 2021-02-25 16:32:41
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

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @param  userFund
     * @return 对象列表
     */
    @GetMapping("selectAll")
    public ResponseEntity<Map<String,Object>> selectAll(Integer offset ,Integer limit , UserFund userFund){
        Map<String,Object> map = new HashMap<>();
        if (offset == null || offset == 0){
            userFund.setOffset(1);
        }else{
            userFund.setOffset(offset);
        }
        userFund.setLimit(limit);
        PageInfo<UserFund> allData = userFundService.queryAllByEntity(userFund);
        map.put("count",allData.getTotal());
        map.put("data",allData.getList());
        return ResponseEntity.ok(map);
    }



    /**
     * 新增数据
     *
     * @param  userFund
     * @return Void
     */
    @PostMapping("insert")
    public ResponseEntity<Void> insert(@RequestBody UserFund userFund){
        userFundService.insert(userFund);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @GetMapping("delete")
    public ResponseEntity<Boolean> delete(Integer id){
        boolean b = userFundService.deleteById(id);
        return ResponseEntity.ok(b);
    }


    /**
     * 修改数据
     *
     * @param userFund
     * @return Void
     */
    @PutMapping("update")
    public ResponseEntity<Void> update(@RequestBody UserFund userFund){
        userFundService.update(userFund);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("findType")
    public ResponseEntity<List<String>> findType(){
        List<String> list = userFundService.findType();
        return ResponseEntity.ok(list);
    }
}