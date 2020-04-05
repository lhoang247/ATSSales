package OfficeManager;

import Entities.Data2;
import javafx.scene.control.TableView;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Export {

    public static void export(String excelTitle, ArrayList<TableView<Data2>> t1,String dateFrom,String dateTo){

        HSSFWorkbook hssfWorkbook=new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet(excelTitle);
        HSSFRow firstRow= hssfSheet.createRow(0);
        HSSFRow secondRow= hssfSheet.createRow(1);
        HSSFRow thirdRow= hssfSheet.createRow(2);
        HSSFRow sixRow= hssfSheet.createRow(5);
        HSSFRow seventhRow= hssfSheet.createRow(6);
        HSSFRow tableviewRow = hssfSheet.createRow(7);
        ///set titles of columns

        if (t1.size() == 6) {
            firstRow.createCell(0).setCellValue("Ticket Stock Turnover Report");
            firstRow.createCell(1).setCellValue("Ticket Stock Turnover Report");
            secondRow.createCell(0).setCellValue("Agent: AIR LINK");
            thirdRow.createCell(0).setCellValue(dateFrom + " - " + dateTo);
            hssfSheet.addMergedRegion(CellRangeAddress.valueOf("A6:G6"));
            hssfSheet.addMergedRegion(CellRangeAddress.valueOf("H6:O6"));
            hssfSheet.addMergedRegion(CellRangeAddress.valueOf("P6:X6"));
            sixRow.createCell(0).setCellValue("RECEIVED BLANKS");
            sixRow.createCell(7).setCellValue("ASSIGNED/USED BLANKS");
            sixRow.createCell(15).setCellValue("FINAL AMOUNT");
            hssfSheet.addMergedRegion(CellRangeAddress.valueOf("A7:C7"));
            hssfSheet.addMergedRegion(CellRangeAddress.valueOf("D7:G7"));
            hssfSheet.addMergedRegion(CellRangeAddress.valueOf("H7:K7"));
            hssfSheet.addMergedRegion(CellRangeAddress.valueOf("L7:O7"));
            hssfSheet.addMergedRegion(CellRangeAddress.valueOf("P7:S7"));
            hssfSheet.addMergedRegion(CellRangeAddress.valueOf("T7:X7"));
            seventhRow.createCell(0).setCellValue("AGENTS STOCKS");
            seventhRow.createCell(3).setCellValue("SUB AGENTS");
            seventhRow.createCell(7).setCellValue("ASSIGNED");
            seventhRow.createCell(11).setCellValue("USED");
            seventhRow.createCell(15).setCellValue("AGENT'S AMOUNT");
            seventhRow.createCell(19).setCellValue("SUB AGENT'S AMOUNT");
        }

        int columnCount = 0;

        for (int i = 0; i < t1.size(); i++) {

            for (int j = 0; j < t1.get(i).getColumns().size();j++) {

                tableviewRow.createCell(j + columnCount).setCellValue(t1.get(i).getColumns().get(j).getText());

            }


            for (int row = 0; row<t1.get(i).getItems().size();row++){

                try {
                    HSSFRow hssfRow= hssfSheet.getRow(row+8);

                    for (int j = 0; j < t1.get(i).getColumns().size(); j++) {
                        if(t1.get(i).getColumns().get(j).getCellData(row) != null) {
                            hssfRow.createCell(j + columnCount).setCellValue(t1.get(i).getColumns().get(j).getCellData(row).toString());
                        }
                        else {
                            hssfRow.createCell(j).setCellValue("");
                        }
                    }
                } catch (Exception e) {
                    HSSFRow hssfRow= hssfSheet.createRow(row+8);

                    for (int j = 0; j < t1.get(i).getColumns().size(); j++) {
                        if(t1.get(i).getColumns().get(j).getCellData(row) != null) {
                            hssfRow.createCell(j + columnCount).setCellValue(t1.get(i).getColumns().get(j).getCellData(row).toString());
                        }
                        else {
                            hssfRow.createCell(j).setCellValue("");
                        }
                    }
                }


            }
            //save excel file and close the workbook
            columnCount = columnCount + t1.get(i).getColumns().size();
        }

        if (t1.size() == 6) {
            HSSFRow totalRow= hssfSheet.createRow(t1.get(4).getItems().size() + 9);
            totalRow.createCell(0).setCellValue("TOTAL: ");
            int total = 0;
            for (Data2 item : t1.get(0).getItems()) {
                total = total + Integer.parseInt(item.getData23());
            }
            totalRow.createCell(2).setCellValue(total);

            total = 0;
            for (Data2 item : t1.get(1).getItems()) {
                total = total + Integer.parseInt(item.getData24());
            }
            totalRow.createCell(6).setCellValue(total);

            total = 0;
            for (Data2 item : t1.get(2).getItems()) {
                total = total + Integer.parseInt(item.getData24());
            }
            totalRow.createCell(10).setCellValue(total);

            total = 0;
            for (Data2 item : t1.get(3).getItems()) {
                total = total + Integer.parseInt(item.getData24());
            }
            totalRow.createCell(14).setCellValue(total);

            total = 0;
            for (Data2 item : t1.get(4).getItems()) {
                total = total + Integer.parseInt(item.getData24());
            }
            totalRow.createCell(18).setCellValue(total);

            total = 0;
            for (Data2 item : t1.get(5).getItems()) {
                total = total + Integer.parseInt(item.getData25());
            }
            totalRow.createCell(23).setCellValue(total);

        }

        try {
            hssfWorkbook.write(new FileOutputStream(excelTitle + ".xls"));
            hssfWorkbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}