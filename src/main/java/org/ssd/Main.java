package org.ssd;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import static java.lang.String.valueOf;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<Word_> raw_words = new ArrayList<>();
        List<String> words = new ArrayList<>();
        List<String> toSort = new ArrayList<>();

        try {
            FileInputStream file = new FileInputStream("/home/dennis/lab/wordsutils/queryFiles.xlsx");///home/dennis/lab/wordsutils/queryFiles.xlsx
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            Map<Integer, List<String>> data = new HashMap<>();


            int i = 0;
            for (Row row : sheet) {
                data.put(i, new ArrayList<String>());
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING, NUMERIC:
                           // System.out.println(cell.getRichStringCellValue().getString());
                         //   word = new Word_(i,cell.getRichStringCellValue().getString());
                          //  word.(new Word_(i,cell.getRichStringCellValue().getString()));
                            String[] s = new String[]{};

                            s = cell.getRichStringCellValue().getString().split(" ");
                            Collections.addAll(toSort, s);
                           // raw_words.add(new Word_(i,cell.getRichStringCellValue().getString()));

                           // data.put(1,cell.getRichStringCellValue().getString());
                           // data.get(valueOf(i)).add(cell.getRichStringCellValue().getString());
                             break;
                        case BOOLEAN:
                             break;

                        default: data.get(valueOf(i)).add(" ");
                    }
                }
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String[] ss = toSort.toArray(new String[0]);
        Arrays.sort(ss);


        int c=0;
        String a=ss[c];
        int wordCount=0;
        List<Result> results = new ArrayList<>();


       // c++;
       // for(String w : ss){
          for(c = 1 ; c<ss.length; c++){

            if(a.equals(ss[c])) {

                wordCount++;
              //  System.out.println("y " + wordCount);
            }else{
               // System.out.println(ss[c-1] + " " +  wordCount);
                results.add(new Result(c,ss[c-1],wordCount));
                a=ss[c];
                wordCount=1;

            }


        }


        Result[] rs = results.toArray(new Result[0]);
        Arrays.sort(rs, Comparator.comparing(Result::getWord_count).reversed());


        XSSFWorkbook workbook = new XSSFWorkbook();
        int rownum = 0;
//Create a blank sheet
        Sheet sheet = workbook.createSheet("Employee Data");

          for(Result r: rs){

              Row row = sheet.createRow(rownum++);
              Cell cell1 = row.createCell(0);
              cell1.setCellValue(r.getValue_() );
              Cell cell2 = row.createCell(1);
              cell2.setCellValue(r.getWord_count() );
             // System.out.println(r.getValue_() + " " + r.getWord_count());

          }
        try {
            FileOutputStream out = new FileOutputStream(new File("/home/dennis/lab/wordsutils/result.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("result.xlsx written successfully on disk.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}