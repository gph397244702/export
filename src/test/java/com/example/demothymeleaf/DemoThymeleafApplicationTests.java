package com.example.demothymeleaf;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoThymeleafApplicationTests {

    @Test
    public void contextLoads() {
        try {
            List<ExcelExportEntity> colList = new ArrayList<ExcelExportEntity>();
            ExcelExportEntity colEntity = new ExcelExportEntity("商品名称", "title");
            colEntity.setNeedMerge(true);
            colList.add(colEntity);

            colEntity = new ExcelExportEntity("供应商", "supplier");
            colEntity.setNeedMerge(true);
            colList.add(colEntity);

            ExcelExportEntity deliColGroup = new ExcelExportEntity("得力", "deli");
            colList.add(deliColGroup);

            ExcelExportEntity jdColGroup = new ExcelExportEntity("京东", "jd");
            colList.add(jdColGroup);


            List <Map<String, Object>> list = new ArrayList <Map<String, Object>>();
            for (int i = 0; i < 10; i++) {
                Map<String, Object> valMap = new HashMap <String, Object>();
                valMap.put("title", "名称." + i);
                valMap.put("supplier", "供应商." + i);

                List<Map<String, Object>> deliDetailList = new ArrayList<Map<String, Object>>();
                for (int j = 0; j < 3; j++) {
                    Map <String, Object> deliValMap = new HashMap<String, Object>();
                    deliValMap.put("orgPrice", "得力.市场价." + j);
                    deliValMap.put("salePrice", "得力.专区价." + j);
                    deliDetailList.add(deliValMap);
                }
                valMap.put("deli", deliDetailList);

                List<Map<String, Object>> jdDetailList = new ArrayList<Map<String, Object>>();
                for (int j = 0; j < 2; j++) {
                    valMap.put("jd", "deli");
                }

                list.add(valMap);
            }

            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("价格分析表", "数据"), colList,
                    list);
            FileOutputStream fos = new FileOutputStream("D:/价格分析表.tt.xls");
            workbook.write(fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
