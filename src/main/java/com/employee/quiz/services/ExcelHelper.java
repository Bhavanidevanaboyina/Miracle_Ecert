package com.employee.quiz.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.employee.quiz.domain.Quiz;



@Service
public class ExcelHelper {

	public ArrayList readExcelSheet(Sheet mySheet) {
				ArrayList cellVectorHolder = new ArrayList<>();
		try {

			Iterator rowIter = mySheet.rowIterator();
			while (rowIter.hasNext()) {
				Row myRow = (Row) rowIter.next();
				Iterator cellIter = myRow.cellIterator();
				ArrayList cellStoreVector = new ArrayList();
				while (cellIter.hasNext()) {
					Cell myCell = (Cell) cellIter.next();
					cellStoreVector.add(myCell);
				}
				cellVectorHolder.add(cellStoreVector);
			}

		} catch (Exception e) {
			
		}
		
		return cellVectorHolder;
	}
	
	}


