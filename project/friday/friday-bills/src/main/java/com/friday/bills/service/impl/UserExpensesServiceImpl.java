package com.friday.bills.service.impl;

import com.friday.bills.entity.UserExpenses;
import com.friday.bills.mapper.UserExpensesMapper;
import com.friday.bills.service.UserExpensesService;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import jxl.Workbook;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * (UserExpenses)表服务实现类
 *
 * @author makejava
 * @since 2021-02-25 15:25:47
 */
@Service("userExpensesService")
public class UserExpensesServiceImpl implements UserExpensesService {
    @Resource
    private UserExpensesMapper userExpensesMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param expensesId 主键
     * @return 实例对象
     */
    @Override
    public UserExpenses queryById(Integer expensesId) {
        return this.userExpensesMapper.queryById(expensesId);
    }
    
     /**
     * 通过实体类查询分页数据
     *
     * @param userExpenses
     * @return 实例对象列表
     */
     @Override
    public PageInfo<UserExpenses> queryAllByEntity(UserExpenses userExpenses) {
        PageHelper.startPage(userExpenses.getOffset(),userExpenses.getLimit());
        List<UserExpenses> queryAll = userExpensesMapper.queryAll(userExpenses);
        PageInfo<UserExpenses> pageInfo = new PageInfo<>(queryAll);
        return pageInfo;
    }


    /**
     * 新增数据
     *
     * @param userExpenses 实例对象
     * @return 实例对象
     */
    @Override
    public UserExpenses insert(UserExpenses userExpenses) {
        this.userExpensesMapper.insert(userExpenses);
        return userExpenses;
    }

    /**
     * 修改数据
     *
     * @param userExpenses 实例对象
     * @return 实例对象
     */
    @Override
    public UserExpenses update(UserExpenses userExpenses) {
        this.userExpensesMapper.update(userExpenses);
        return this.queryById(userExpenses.getExpensesId());
    }

    /**
     * 通过主键删除数据
     *
     * @param expensesId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer expensesId) {
        return this.userExpensesMapper.deleteById(expensesId) > 0;
    }

    @Override
    public List<String> findType() {
        return userExpensesMapper.findType();
    }

    @Value("${friday.location}")
    String reportPath;

    @Override
    public void downloadFile(UserExpenses userExpenses) throws IOException, WriteException {
        List<UserExpenses> userExpensesList = userExpensesMapper.queryAll(userExpenses);
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //创建本地文件

        String filePath = reportPath + File.separator + "用户支出表.xls";
        File dbfFile = new File(filePath);
        //使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
        WritableWorkbook wwb = Workbook.createWorkbook(dbfFile);
        if (!dbfFile.exists() || dbfFile.isDirectory()) {
            dbfFile.createNewFile();
        }

        //设置字体;
        WritableFont font1 = new WritableFont(WritableFont.ARIAL,10,WritableFont.NO_BOLD,false, UnderlineStyle.NO_UNDERLINE);
        WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
        //设置背景颜色;
        cellFormat1.setBackground(Colour.LIGHT_GREEN);
        //设置边框;
        cellFormat1.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
        WritableSheet ws = wwb.createSheet("列表 1", 0);  //创建一个可写入的工作表
//        ws.setColumnView(1,85);
//        ws.setColumnView(2,12);
        //添加excel表头
        ws.addCell(new Label(0, 0, "订单号",cellFormat1));
        ws.addCell(new Label(1, 0, "支出时间",cellFormat1));
        ws.addCell(new Label(2, 0, "支出金额（元）",cellFormat1));
        ws.addCell(new Label(3, 0, "分类",cellFormat1));
        ws.addCell(new Label(4, 0, "备注",cellFormat1));
        ws.addCell(new Label(5, 0, "支出人id",cellFormat1));
        ws.addCell(new Label(6, 0, "支出人",cellFormat1));


        WritableCellFormat cellFormat2 = new WritableCellFormat(font1);
        cellFormat2.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);

        int index = 0;
        for (UserExpenses us : userExpensesList) {
            ws.addCell(new Label(0, index + 1, us.getExpensesId().toString(),cellFormat2));
            ws.addCell(new Label(1, index + 1, us.getExpensesTime(),cellFormat2));
            ws.addCell(new Label(2, index + 1, us.getExpensesNum(),cellFormat2));
            ws.addCell(new Label(3, index + 1, us.getExpensesSort(),cellFormat2));
            ws.addCell(new Label(4, index + 1, us.getExpensesRemark(),cellFormat2));
            ws.addCell(new Label(5, index + 1, us.getExpensesUserId(),cellFormat2));
            ws.addCell(new Label(6, index + 1, us.getExpensesUser(),cellFormat2));
            index++;
        }
        wwb.write();//从内存中写入文件中
        wwb.close();//关闭资源，释放内存
        response.setHeader("content-type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        String fileName = new String("用户支出表.xls".getBytes("utf-8"), "ISO-8859-1");
        response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
        File file = new File(filePath);
        Files.copy(file.toPath(), response.getOutputStream());
    }



    /**
     * @Description: 支出统计视图，根据参数不同查询本年、月、日
     * @Param:  userExpenses
     * @return:  map
     * @Author: XinWei Wu
     * @Date: 2021/3/10
     */
    @Override
    public Map<String, Object> getMonthExpenses(UserExpenses userExpenses) {
        Map<String,Object> map = new HashMap<>();
        map.put("expensesTotal",userExpensesMapper.expensesTotal(userExpenses.getDate1()));
        List<Map> timeList = userExpensesMapper.expensesList(userExpenses.getDate1());
        map.put("expensesList",timeList);
        List<Map> group = userExpensesMapper.expensesGroup(userExpenses.getDate1());
        map.put("expensesGroup" ,group);
        map.put("todayExpenses",userExpensesMapper.todayExpenses(userExpenses.getExpensesTime()));
        System.out.println(map);
        return map;
    }
}