package com.example.demothymeleaf;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.example.demothymeleaf.entity.EasyPOIModel;
import com.example.demothymeleaf.entity.User;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author gaopanhong
 * @description: TODO
 * @since 2019-09-30
 */

@Controller
@RequestMapping(value = "exportexcel")
public class ExportExcel {

    @RequestMapping(value="exportexceltest")
    @ResponseBody
    public String exportExcelTest(HttpServletResponse response){
        // 获取workbook对象
        Workbook workbook = exportSheetByTemplate() ;
        // 判断数据
        if(workbook == null) {
            return "fail";
        }
        // 设置excel的文件名称
        String excelName = "测试excel" ;
        // 重置响应对象
        response.reset();
        // 当前日期，用于导出文件名称
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = "测试";
        // 指定下载的文件名--设置响应头
        response.setHeader("Content-Disposition", "attachment;filename=" +excelName+".xlsx");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 写出数据输出流到页面
        try {
            OutputStream output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            workbook.write(bufferedOutPut);
            bufferedOutPut.flush();
            bufferedOutPut.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * 模版单sheet导出示例
     * @return
     */
    public Workbook exportSheetByTemplate(){
        // 查询数据,此处省略
        List <EasyPOIModel> list = new ArrayList <EasyPOIModel>();
        int count1 = 0 ;
        EasyPOIModel easyPOIModel11 = new EasyPOIModel(String.valueOf(count1++),"信科",new User("张三","男",20)) ;
        EasyPOIModel easyPOIModel12 = new EasyPOIModel(String.valueOf(count1++),"生工",new User("李四","男",17)) ;
        EasyPOIModel easyPOIModel13 = new EasyPOIModel(String.valueOf(count1++),"化工",new User("淑芬","女",34)) ;
        EasyPOIModel easyPOIModel14 = new EasyPOIModel(String.valueOf(count1++),"信科",new User("仲达","男",55)) ;
        EasyPOIModel easyPOIModel15 = new EasyPOIModel(String.valueOf(count1++),"弘",new User("弘","男",55)) ;
        EasyPOIModel easyPOIModel16 = new EasyPOIModel(String.valueOf(count1++),"搞",new User("弘","男",55)) ;
        list.add(easyPOIModel11) ;
        easyPOIModel11 = null ;
        list.add(easyPOIModel12) ;
        easyPOIModel12 = null ;
        list.add(easyPOIModel13) ;
        easyPOIModel13 = null ;
        list.add(easyPOIModel14) ;
        list.add(easyPOIModel15) ;
        list.add(easyPOIModel16) ;
        easyPOIModel14 = null ;
        // 设置导出配置
        // 获取导出excel指定模版
        TemplateExportParams params = new TemplateExportParams("templates/excel/easypoiTest.xlsx",true);

        // 设置sheetName，若不设置该参数，则使用得原本得sheet名称
        params.setSheetName("班级信息");
        Map <String,Object> map = new HashMap <String,Object>() ;
        map.put("list",list) ;
        // 导出excel
        return ExcelExportUtil.exportExcel(params, map);
    }

}
