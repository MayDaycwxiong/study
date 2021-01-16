package com.infinite.life.frame.poi.excel;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author cuiwx
 * @version 1.0  2020/10/20
 * @description
 */
@Slf4j
public class ReadExcelUtils {

    private static final String XLS = ".xls";
    private static final String XLSX = ".xlsx";

    public static void main(String[] args) throws Exception {
        String path = "D:\\CodeOfCompany\\IDEA\\MayDay\\Project\\study\\doc\\学习记录.xlsx";
        dealExcel(path);
    }

    private static void dealExcel(String path) throws Exception {
        // 读取Excel表格
        Workbook workbook = readExcel(path);
        if (workbook == null) {
            log.warn("Excel 表格为空");
            return;
        }
        readSheet(workbook);
    }

    private static void readSheet(Workbook workbook) {
        int sheetNum = workbook.getNumberOfSheets();
        for (int sheetIndex = 0; sheetIndex < sheetNum; sheetIndex++) {
            log.info("开始遍历第[{}]个sheet,名字为[{}]", sheetIndex, workbook.getSheetName(sheetIndex));
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            int rowNum = sheet.getLastRowNum();
            log.info("第[{}]个sheet中一共有[{}]行", sheetIndex, rowNum);
            // 遍历Row
            for (int rowIndex = 0; rowIndex <= rowNum; rowIndex++) {
                log.info("开始遍历表格第[{}]行", rowIndex);
                Row row = sheet.getRow(rowIndex);
                if (row == null) {
                    log.info("第[{}]行为空行，跳出本行", rowIndex);
                    continue;
                }
                // 得到每行Row的单元格数
                int cellNum = row.getLastCellNum();
                log.info("第[{}]个sheet中第[{}]行共有[{}]个单元格", sheetIndex, rowIndex, cellNum);

                for (int cellIndex = 0; cellIndex < cellNum; cellIndex++) {
                    Cell cell = row.getCell(cellIndex);
                    log.info("开始遍历第[{}]个单元格,单元格值为[{}]", cellIndex, getCellFormatValue(cell));
                }
                log.warn("第[{}]行遍历完毕", rowIndex);
            }
            log.warn("####################第[{}]个 Sheet,名字为[{}] 遍历完毕###################", sheetIndex, workbook.getSheetName(sheetIndex));
        }
    }

    private static Object getCellFormatValue(Cell cell) {
        Object cellValue;
        if (cell != null) {
            // 判断cell类型
            switch (cell.getCellType()) {
                // 空值单元格
                case BLANK:
                    log.info("空单元格");
                    cellValue = StrUtil.EMPTY;
                    break;
                case NUMERIC:
                    // 判断cell是否为日期格式
                    if (DateUtil.isCellDateFormatted(cell)) {
                        log.info("日期格式单元格");
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat format = new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
                        cellValue = format.format(date);
                    } else {
                        // 数字格式
                        log.info("数字格式");
                        cellValue = cell.getNumericCellValue();
                    }
                    break;
                // 公式型单元格
                case FORMULA:
                    log.info("公式型格式,[{}]", cell.getCellFormula());
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                case STRING:
                    log.info("字符串格式");
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                case BOOLEAN:
                    log.info("布尔值格式");
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                default:
                    cellValue = StrUtil.EMPTY;

            }
        } else {
            cellValue = StrUtil.EMPTY;
        }
        return cellValue;
    }

    private static Workbook readExcel(String path) throws Exception {
        if (StrUtil.isEmpty(path)) {
            log.warn("路径为空");
            return null;
        }
        String suffix = path.substring(path.lastIndexOf("."));
        log.info("后缀：[{}]", suffix);
        InputStream is = new FileInputStream(path);
        if (XLS.equals(suffix)) {
            return new HSSFWorkbook(is);
        } else if (XLSX.equals(suffix)) {
            return new XSSFWorkbook(is);
        }
        return null;
    }
}
