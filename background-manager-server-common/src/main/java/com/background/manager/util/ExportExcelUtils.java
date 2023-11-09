package com.background.manager.util;

import cn.hutool.core.collection.CollectionUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: Excel文件导出工具类
 * @Author: 杜黎明
 * @Date: 2023/04/13 16:15:02
 * @Version: 1.0.0
 */
public class ExportExcelUtils {

    /**
     * Description 导出Excel文件
     * @param workbook excel文件
     * @param sheetNum 表格数量
     * @param sheetTitle 表格标题
     * @param headers 列标题
     * @param result 数据
     * @param maxColumnWidth 最大列宽
     * @param columnWidth 列宽
     * @return {@link HSSFWorkbook }
     * @author 杜黎明
     * @date 2023/04/13 16:19:11
     */
    public static HSSFWorkbook ExportExcel(HSSFWorkbook workbook, int sheetNum
                                            , String sheetTitle, String[] headers, List<List<String>> result
                                            , Integer maxColumnWidth, List<Integer> columnWidth){
        //导出Excel文件的宽度系数
        int widthFactor=256;
        //默认最大长度为20
        if (maxColumnWidth==null){
            maxColumnWidth=20;
        }
        //生成一个表格sheet
        HSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(sheetNum,sheetTitle);
        /**生成一个标题行的样式*/
        HSSFCellStyle style = workbook.createCellStyle();
        //设置这些样式的字体格式
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 14);
        font.setFontName("宋体");
        font.setBold(true);
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.GREY_80_PERCENT.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);

        Map<Integer,Integer> maxWidth=new HashMap<Integer,Integer>(headers.length);
        //表格标题行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i <headers.length ; i++) {
            HSSFCell cell = row.createCell((short) i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i].toString());
            cell.setCellValue(text.toString());
            maxWidth.put(i,text.toString().getBytes().length*widthFactor+400);
        }
        //遍历数据集合行，产生数据行
        if (result!=null){
            //记录需要合并的单元格
            ArrayList<Integer> hbList = new ArrayList<>();
            int index=1; //从第一行开始填充数据
            /**设置数据内容的style*/
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            //设置这些样式
            HSSFFont hssfFont = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setFontName("宋体");
            font.setBold(true);
            style.setFont(font);
            style.setFillForegroundColor(IndexedColors.GREY_80_PERCENT.index);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
            for (List<String> m : result){
                //当空值时，记录需要合并的单元格
                if (StringUtils.isBlank(m.get(1))){
                    hbList.add(index);
                }
                row = sheet.createRow(index);
                int cellIndex=0;
                for (String str:m){
                    HSSFCell cell = row.createCell((short) cellIndex);
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(str);
                    int length=0;
                    if (StringUtils.isBlank(str)){
                        length=str.getBytes().length*widthFactor;
                    }
                    length=length+400;
                    if (length>(maxColumnWidth*(widthFactor*3))){
                        length=(maxColumnWidth*(widthFactor*3));
                    }
                    maxWidth.put(cellIndex,Math.max(length,maxWidth.get(cellIndex)));
                    cellIndex++;
                }
                index++;
            }
            //循环合并单元格
            if (CollectionUtil.isNotEmpty(hbList)){
                for (Integer integer:hbList){
                    CellRangeAddress region = new CellRangeAddress(integer, integer, 0, 0);
                    sheet.addMergedRegion(region);
                }
            }

            for (int i = 0; i < headers.length; i++) {
                if (CollectionUtil.isNotEmpty(columnWidth)){
                    sheet.setColumnWidth(i,columnWidth.get(i)*(widthFactor*3));
                }else {
                    sheet.setColumnWidth(i,columnWidth.get(i));
                }
            }
        }
        return workbook;
    }

}
