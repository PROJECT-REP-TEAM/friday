package com.friday.bills.service.impl;

import com.friday.bills.entity.UserIncome;
import com.friday.bills.entity.UserIncome;
import com.friday.bills.mapper.UserIncomeMapper;
import com.friday.bills.service.UserIncomeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * (UserIncome)表服务实现类
 *
 * @author makejava
 * @since 2021-02-23 15:10:05
 */
@Service("userIncomeService")
@Log4j
public class UserIncomeServiceImpl implements UserIncomeService {
    @Resource
    private UserIncomeMapper userIncomeMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param incomeId 主键
     * @return 实例对象
     */
    @Override
    public UserIncome queryById(Integer incomeId) {
        return this.userIncomeMapper.queryById(incomeId);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param userIncome 实例对象
     * @return 对象列表
     */
    @Override
    public PageInfo<UserIncome> queryAllByEntity(UserIncome userIncome) {
        PageHelper.startPage(userIncome.getOffset(),userIncome.getLimit());
        List<UserIncome> queryAll = userIncomeMapper.queryAll(userIncome);
        PageInfo<UserIncome> pageInfo = new PageInfo<>(queryAll);
        return pageInfo;
    }

    /**
     * 新增数据
     *
     * @param userIncome 实例对象
     * @return 实例对象
     */
    @Override
    public UserIncome insert(UserIncome userIncome) {
        this.userIncomeMapper.insert(userIncome);
        return userIncome;
    }

    /**
     * 修改数据
     *
     * @param userIncome 实例对象
     * @return 实例对象
     */
    @Override
    public UserIncome update(UserIncome userIncome) {
        this.userIncomeMapper.update(userIncome);
        return this.queryById(userIncome.getIncomeId());
    }

    /**
     * 通过主键删除数据
     *
     * @param incomeId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer incomeId) {
        return this.userIncomeMapper.deleteById(incomeId) > 0;
    }

    @Override
    public List<String> findType() {
        return userIncomeMapper.findType();
    }

    @Value("${friday.location}")
    String reportPath;

    @Override
    public void downloadFile(UserIncome userIncome) throws IOException, WriteException {
        List<UserIncome> userIncomeList = userIncomeMapper.queryAll(userIncome);
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //创建本地文件

        String filePath = reportPath + File.separator + "用户收入表.xls";
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
        ws.addCell(new Label(1, 0, "收入时间",cellFormat1));
        ws.addCell(new Label(2, 0, "收入金额（元）",cellFormat1));
        ws.addCell(new Label(3, 0, "分类",cellFormat1));
        ws.addCell(new Label(4, 0, "备注",cellFormat1));
        ws.addCell(new Label(5, 0, "收入人id",cellFormat1));
        ws.addCell(new Label(6, 0, "收入人",cellFormat1));


        WritableCellFormat cellFormat2 = new WritableCellFormat(font1);
        cellFormat2.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);

        int index = 0;
        for (UserIncome us : userIncomeList) {
            ws.addCell(new Label(0, index + 1, us.getIncomeId().toString(),cellFormat2));
            ws.addCell(new Label(1, index + 1, us.getIncomeTime(),cellFormat2));
            ws.addCell(new Label(2, index + 1, us.getIncomeNum(),cellFormat2));
            ws.addCell(new Label(3, index + 1, us.getIncomeSort(),cellFormat2));
            ws.addCell(new Label(4, index + 1, us.getIncomeRemark(),cellFormat2));
            ws.addCell(new Label(5, index + 1, us.getIncomeUserId().toString(),cellFormat2));
            ws.addCell(new Label(6, index + 1, us.getIncomeUser(),cellFormat2));
            index++;
        }
        wwb.write();//从内存中写入文件中
        wwb.close();//关闭资源，释放内存
        response.setHeader("content-type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        String fileName = new String("用户收入表.xls".getBytes("utf-8"), "ISO-8859-1");
        response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
        File file = new File(filePath);
        Files.copy(file.toPath(), response.getOutputStream());
    }
}