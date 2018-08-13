import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class EXCELReport {

    Logic logic = new Logic();

      public void createReport(){
          Set<Employee> setOfEmployee = new LinkedHashSet<>();
          setOfEmployee.addAll(logic.readEmployeesFromFile());

          Workbook wb = new HSSFWorkbook();
          Sheet sheet = wb.createSheet("report");

          CellStyle styleHead = wb.createCellStyle();
          styleHead.setBorderBottom(BorderStyle.DASH_DOT);
          styleHead.setBorderLeft(BorderStyle.DASH_DOT);
          styleHead.setBorderRight(BorderStyle.DASH_DOT);
          styleHead.setBorderTop(BorderStyle.DASH_DOT);

          Font fontHeader = wb.createFont();
          fontHeader.setBold(true);
          fontHeader.setFontHeightInPoints((short) 12);

          styleHead.setFont(fontHeader);

          Iterator<Employee> iterator = setOfEmployee.iterator();

          for (int row_i = 0; row_i <= setOfEmployee.size(); row_i++) {

             // ArrayList<Employee> list = new ArrayList<>(setOfEmployee);

                  Row row = sheet.createRow(row_i);
                  if (row_i == 0) {
                      Cell head0 = row.createCell(0);
                      head0.setCellValue("ID");
                      head0.setCellStyle(styleHead);

                      Cell head1 = row.createCell(1);
                      head1.setCellValue("Last-name");
                      head1.setCellStyle(styleHead);

                      Cell head2 = row.createCell(2);
                      head2.setCellValue("Name");
                      head2.setCellStyle(styleHead);

                      Cell head3 = row.createCell(3);
                      head3.setCellValue("Surname");
                      head3.setCellStyle(styleHead);

                      Cell head4 = row.createCell(4);
                      head4.setCellValue("Birthday");
                      head4.setCellStyle(styleHead);

                      Cell head5 = row.createCell(5);
                      head5.setCellValue("Position");
                      head5.setCellStyle(styleHead);

                      Cell head6 = row.createCell(6);
                      head6.setCellValue("Sub-division");
                      head6.setCellStyle(styleHead);

                      Cell head7 = row.createCell(7);
                      head7.setCellValue("Room number");
                      head7.setCellStyle(styleHead);

                      Cell head8 = row.createCell(8);
                      head8.setCellValue("Official Telefon");
                      head8.setCellStyle(styleHead);

                      Cell head9 = row.createCell(9);
                      head9.setCellValue("eMail");
                      head9.setCellStyle(styleHead);

                      Cell head10 = row.createCell(10);
                      head10.setCellValue("Salary");
                      head10.setCellStyle(styleHead);

                      Cell head11 = row.createCell(11);
                      head11.setCellValue("Date of hiring");
                      head11.setCellStyle(styleHead);

                      Cell head12 = row.createCell(12);
                      head12.setCellValue("Notes");
                      head12.setCellStyle(styleHead);

                  } else {
                     // Employee  employee = list.get(row_i-1);
                      Employee employee = iterator.next();

                      for (int column = 0; column < 13; column++) {
                          Cell cell = row.createCell(column);
                          switch (column) {
                              case 0:
                                  cell.setCellValue(employee.getID());
                                  break;
                              case 1:
                                  cell.setCellValue(employee.getLastName());
                                  break;
                              case 2:
                                  cell.setCellValue(employee.getName());
                                  break;
                              case 3:
                                  cell.setCellValue(employee.getSurname());
                                  break;
                              case 4:
                                  cell.setCellValue(employee.getBirthDay().toString());
                                  break;
                              case 5:
                                  cell.setCellValue(employee.getPosition());
                                  break;
                              case 6:
                                  cell.setCellValue(employee.getSubDivision());
                                  break;
                              case 7:
                                  cell.setCellValue(employee.getRoomNumber());
                                  break;
                              case 8:
                                  cell.setCellValue(employee.getOfficialTelefon());
                                  break;
                              case 9:
                                  cell.setCellValue(employee.geteMail());
                                  break;
                              case 10:
                                  cell.setCellValue(employee.getSalary());
                                  break;
                              case 11:
                                  cell.setCellValue(employee.getDateOfHiring().toString());
                                  break;
                              case 12:
                                  cell.setCellValue(employee.getNotes());
                                  break;
                          }
                          sheet.autoSizeColumn(column);
                      }
                  }
          }
          try {
              OutputStream fos = new FileOutputStream("E:/employee.xls");
              ((HSSFWorkbook) wb).write(fos);
              fos.close();
              wb.close();
              System.out.println("report has been created");
          } catch (java.io.IOException e) {
              e.printStackTrace();
              System.out.println("can't create a report");
          }
      }
}
