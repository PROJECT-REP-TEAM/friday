package com.friday.finance.controller;

import com.friday.finance.entity.UserStock;
import com.friday.finance.service.UserStockService;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

/**
 * (UserStock)表控制层
 *
 * @author makejava
 * @since 2021-02-25 16:34:00
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

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @param  userStock
     * @return 对象列表
     */
    @GetMapping("selectAll")
    public ResponseEntity<Map<String,Object>> selectAll(Integer offset ,Integer limit , UserStock userStock){
        Map<String,Object> map = new HashMap<>();
        userStock.setOffset(offset);
        userStock.setLimit(limit);
        PageInfo<UserStock> allData = userStockService.queryAllByEntity(userStock);
        map.put("count",allData.getTotal());
        map.put("data",allData.getList());
        return ResponseEntity.ok(map);

    }



    /**
     * 新增数据
     *
     * @param  userStock
     * @return Void
     */
    @PostMapping("insert")
    public ResponseEntity<Void> insert(@RequestBody UserStock userStock){
        userStockService.insert(userStock);
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
        boolean b = userStockService.deleteById(id);
        return ResponseEntity.ok(b);
    }


    /**
     * 修改数据
     *
     * @param userStock
     * @return Void
     */
    @PutMapping("update")
    public ResponseEntity<Void> update(@RequestBody UserStock userStock){
        userStockService.update(userStock);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}