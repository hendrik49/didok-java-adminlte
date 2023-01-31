package com.didok.adminlte.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.didok.adminlte.model.Transaksis;

public class ExcelGenerator {
    private List<Transaksis> TransaksisList;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelGenerator(List<Transaksis> TransaksisList) {
        this.TransaksisList = TransaksisList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeader() {
        sheet = workbook.createSheet("Transaksis");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "ID", style);
        createCell(row, 1, "Tanggal", style);
        createCell(row, 2, "Nama Perusahaan", style);
        createCell(row, 3, "Nama Barang", style);
        createCell(row, 4, "Harga", style);
        createCell(row, 5, "Qty", style);
        createCell(row, 6, "Grand Total", style);
        createCell(row, 7, "Sisa Stock", style);

    }

    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }

    private void write() {
        int rowCount = 1; 
        CellStyle style = workbook.createCellStyle(); 
        XSSFFont font = workbook.createFont(); 
        font.setFontHeight(14); 
        style.setFont(font); 
        
        for (Transaksis record:TransaksisList) {
            Row row = sheet.createRow(rowCount++ ); 
            int columnCount = 0; 
            DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
            createCell(row, columnCount++ , record.getId(), style); 
            createCell(row, columnCount++ , dateFormatter.format(record.getDate()), style); 
            createCell(row, columnCount++ , record.getPerusahaan().getNama(), style); 
            createCell(row, columnCount++ , record.getBarang().getNama(), style); 
            createCell(row, columnCount++ , record.getBarang().getHarga().intValue(), style); 
            createCell(row, columnCount++ , record.getQty(), style); 
            createCell(row, columnCount++ , record.getGrand_total().intValue(), style); 
            createCell(row, columnCount++ , record.getBarang().getStock().intValue(), style); 
        }
    }

    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
