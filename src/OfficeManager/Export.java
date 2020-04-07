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

//This class allows the user to select a report and export the values to a excel sheet.
//Main function is to allow users to print out reports.


public class Export {

    public static void export(String excelTitle, ArrayList<TableView<Data2>> t1,String dateFrom,String dateTo){

        //Creating new sheet

        HSSFWorkbook hssfWorkbook=new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet(excelTitle);

        //Creating new rows to put values into.

        HSSFRow firstRow= hssfSheet.createRow(0);
        HSSFRow secondRow= hssfSheet.createRow(1);
        HSSFRow thirdRow= hssfSheet.createRow(2);
        HSSFRow sixRow= hssfSheet.createRow(5);
        HSSFRow seventhRow= hssfSheet.createRow(6);
        HSSFRow tableviewRow = hssfSheet.createRow(7);

        ///set titles of columns

        if (t1.size() == 6) {

            //Adding data such as the title of the report

            firstRow.createCell(0).setCellValue("Ticket Stock Turnover Report");
            firstRow.createCell(1).setCellValue("Ticket Stock Turnover Report");
            secondRow.createCell(0).setCellValue("Agent: AIR LINK");
            thirdRow.createCell(0).setCellValue(dateFrom + " - " + dateTo);

            //Merging cells.

            hssfSheet.addMergedRegion(CellRangeAddress.valueOf("A6:G6"));
            hssfSheet.addMergedRegion(CellRangeAddress.valueOf("H6:O6"));
            hssfSheet.addMergedRegion(CellRangeAddress.valueOf("P6:X6"));

            sixRow.createCell(0).setCellValue("RECEIVED BLANKS");
            sixRow.createCell(7).setCellValue("ASSIGNED/USED BLANKS");
            sixRow.createCell(15).setCellValue("FINAL AMOUNT");


            //Merging cells.

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


        //This part is used to add the TableViews seen in the program to an excel sheet.
        //Using O(n^3) to add x amount of TableViews
        //y amount of rows
        //and z amount of columns.

        //This procedure passed a list of TableViews as its parameters
        //Allowing the program to use a loot to add the data to the excel sheet.


        //This for loop is for the amount TableViews

        for (int i = 0; i < t1.size(); i++) {

            //This for loop adds the column names of the TableView to the excel sheet

            for (int j = 0; j < t1.get(i).getColumns().size();j++) {

                tableviewRow.createCell(j + columnCount).setCellValue(t1.get(i).getColumns().get(j).getText());

            }

            //Another for loop is added for each row in the TableView.

            for (int row = 0; row<t1.get(i).getItems().size();row++){

                try {
                    HSSFRow hssfRow= hssfSheet.getRow(row+8);

                    //Another for loop is added for each column in the TableView.

                    for (int j = 0; j < t1.get(i).getColumns().size(); j++) {
                        if(t1.get(i).getColumns().get(j).getCellData(row) != null) {

                            //The loop adds data to a single cell.

                            hssfRow.createCell(j + columnCount).setCellValue(t1.get(i).getColumns().get(j).getCellData(row).toString());
                        }
                        else {

                            //If null then it adds nothing.

                            hssfRow.createCell(j).setCellValue("");
                        }
                    }
                } catch (Exception e) {

                    //This catch statement is added if the row that we are writing to has not been created yet.

                    HSSFRow hssfRow= hssfSheet.createRow(row+8);

                    for (int j = 0; j < t1.get(i).getColumns().size(); j++) {

                        if(t1.get(i).getColumns().get(j).getCellData(row) != null) {

                            //The loop adds data to a single cell.

                            hssfRow.createCell(j + columnCount).setCellValue(t1.get(i).getColumns().get(j).getCellData(row).toString());

                        }

                        else {

                            hssfRow.createCell(j).setCellValue("");

                        }
                    }
                }


            }

            //This count variable allows for the loop to work on a new area without overlapping onto the other data added.

            columnCount = columnCount + t1.get(i).getColumns().size();
        }


        //This part is used to add the total of each report.

        if (t1.size() == 6) {

            //Here we create a new row to work on.

            HSSFRow totalRow= hssfSheet.createRow(t1.get(4).getItems().size() + 9);

            //Adding a cell that contains the string "TOTAL: "

            totalRow.createCell(0).setCellValue("TOTAL: ");


            //This variable is used to add up the total of each TableView's LAST column.

            int total = 0;

            //A loop to add up the total.

            for (Data2 item : t1.get(0).getItems()) {
                total = total + Integer.parseInt(item.getData23());
            }

            //Adding the total to a cell

            totalRow.createCell(2).setCellValue(total);

            //A loop to add up the total.

            total = 0;

            for (Data2 item : t1.get(1).getItems()) {
                total = total + Integer.parseInt(item.getData24());
            }

            //Adding the total to a cell

            totalRow.createCell(6).setCellValue(total);

            //A loop to add up the total.

            total = 0;

            for (Data2 item : t1.get(2).getItems()) {
                total = total + Integer.parseInt(item.getData24());
            }

            //Adding the total to a cell

            totalRow.createCell(10).setCellValue(total);

            //A loop to add up the total.

            total = 0;

            for (Data2 item : t1.get(3).getItems()) {
                total = total + Integer.parseInt(item.getData24());
            }

            //Adding the total to a cell

            totalRow.createCell(14).setCellValue(total);

            //A loop to add up the total.

            total = 0;

            for (Data2 item : t1.get(4).getItems()) {
                total = total + Integer.parseInt(item.getData24());
            }

            //Adding the total to a cell

            totalRow.createCell(18).setCellValue(total);

            //A loop to add up the total.

            total = 0;

            for (Data2 item : t1.get(5).getItems()) {
                total = total + Integer.parseInt(item.getData25());
            }

            //Adding the total to a cell

            totalRow.createCell(23).setCellValue(total);

        }

        //Here we are writing to a file

        try {
            hssfWorkbook.write(new FileOutputStream(excelTitle + ".xls"));
            hssfWorkbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}