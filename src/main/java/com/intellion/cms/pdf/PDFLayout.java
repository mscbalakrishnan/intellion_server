package com.intellion.cms.pdf;


import java.awt.Color;
import java.util.List;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;



public class PDFLayout extends PdfPageEventHelper{
	
	private static LineSeparator UNDERLINE =  new LineSeparator(0.8f, 100, null, Element.ALIGN_CENTER, 2);
	private static final int LINE_SPACE = 10;
	private static final int LEFT_MARGIN = 52;
	private static final int FONT_SIZE = 11;
	private static final int HEADER_FONT_SIZE = 13;
	private static final int TILE_FONT_SIZE = 20;
	private static final int SUB_TILE_FONT_SIZE = 16;
	private static final int TABLE_FONT_SIZE = 8;
	private static final String LABEL_SEPERATOR = ":";
	
	private static Font getTitleFont(int fontSize){
		Font font =  FontFactory.getFont("Times-Roman", fontSize, Font.BOLD, Color.BLUE);
		font.setColor(28, 131, 198); //Dell icon color
		//font.setColor(0, 0, 255);
		return font;
	}
	
	private static Font getDefaultFont(int depth){
		float font_size = FONT_SIZE - (depth > 0 ? 1f : 0);
		return FontFactory.getFont("Times-Roman", font_size, Font.NORMAL, Color.BLACK);
	}
	
	private static Font getLabelFont(int depth){
		float font_size = FONT_SIZE - (depth > 0 ? 1f : 0);
		System.out.println("FOnt size:"+font_size);
		return FontFactory.getFont("Times-Roman", font_size, Font.BOLD, Color.BLACK);
	}
	
	private static Font getHeaderFont(int depth){
		float font_size = HEADER_FONT_SIZE - (depth > 0 ? 1f : 0);
		Font font = FontFactory.getFont("Times-Roman", font_size, Font.UNDERLINE, Color.BLACK);
		font.setColor(28, 131, 198);
		return font;
	}
	
	private static Font getTableFont(){
		return FontFactory.getFont("Times-Roman", TABLE_FONT_SIZE, Font.NORMAL, Color.BLACK);
	}
	
	public static Image addTilteImage(String imagePath) throws Exception{
		
		Image image = Image.getInstance(imagePath);
        image.scaleToFit(45, 45);
        image.setAlignment(Image.LEFT | Image.TEXTWRAP);
        return image;
	}
	
	public static Element generateTitleSection(String title, String subTitle, List<String> subTitless) throws Exception{
		
		Paragraph titleSection = new Paragraph();
		
		titleSection.setAlignment(Element.ALIGN_CENTER);
		titleSection.add(new Chunk(title, getTitleFont(TILE_FONT_SIZE)));
		titleSection.add(new Chunk(Chunk.NEWLINE));
		if(subTitle != null){
			titleSection.add(new Chunk(subTitle, getTitleFont(SUB_TILE_FONT_SIZE)));
			titleSection.add(new Chunk(Chunk.NEWLINE));
		}
		titleSection.add(UNDERLINE);
		
		return titleSection;
		
	}

	
public static Element generateLabelGroupData(List<PropertyInfo> labelData, Integer depth){
		
		Paragraph section = new Paragraph();
		section.setSpacingBefore(LINE_SPACE);
		PdfPTable  pdfTable = new PdfPTable(2);
		pdfTable.setHorizontalAlignment(Element.ALIGN_CENTER);
		for(PropertyInfo data: labelData){
			
			PdfPCell cellLabel = new PdfPCell(new Phrase(data.getProperty()+LABEL_SEPERATOR, getLabelFont(depth)));
			cellLabel.disableBorderSide(Rectangle.BOX);
			//cellLabel.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellLabel.setPaddingTop(LINE_SPACE);
			cellLabel.setPaddingLeft(depth*LINE_SPACE);
			pdfTable.addCell(cellLabel);
			PdfPCell cellVal = new PdfPCell(new Phrase(data.getValue(), getDefaultFont(depth)));
			//cellVal.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellVal.disableBorderSide(Rectangle.BOX);
			cellVal.setPaddingTop(LINE_SPACE);
			pdfTable.addCell(cellVal);
		}
		
		section.add(pdfTable);
		
		return section;
	}
	
	public static Element generateSubHeader(String subHeaderTitle, Integer depth){
		Paragraph section = new Paragraph();
		
		if(subHeaderTitle == null || subHeaderTitle.isEmpty()){
			return section;
		}
		section.setAlignment(Element.ALIGN_LEFT);
		section.setSpacingBefore(LINE_SPACE);
		section.setIndentationLeft(depth * LINE_SPACE + LEFT_MARGIN);
		section.add(new Phrase(subHeaderTitle, getHeaderFont(depth)));
		
		return section;
	}
	
	public static Element addPaddingTop(int space){
		Paragraph section = new Paragraph();
		section.setSpacingBefore(space);
		return section;
	}

	public static Element generateTableData(List<TableData> tableDataList,
			Integer depth) {
		PdfPTable  pdfTable = null;
		for(TableData tableData: tableDataList){
			List<String> headings = tableData.getTableHeading();
			List<List<String>> data =  tableData.getTableData();
			pdfTable = new PdfPTable(headings.size());
			
			// Add Table Headers
			for(String heading: headings){
				PdfPCell cellLabel = new PdfPCell(new Phrase(heading, getLabelFont(0)));
				cellLabel.setHorizontalAlignment(Element.ALIGN_CENTER);
				//cellLabel.setPaddingTop(LINE_SPACE);
				cellLabel.setPaddingLeft(depth*LINE_SPACE);
				pdfTable.addCell(cellLabel);
			}
			
			// Add Table data
			for(List<String> rowData: data){
				for(String cellData: rowData){
					PdfPCell cellLabel = new PdfPCell(new Phrase(cellData, getTableFont()));
					cellLabel.setHorizontalAlignment(Element.ALIGN_CENTER);
					//cellLabel.setPaddingTop(LINE_SPACE);
					cellLabel.setPaddingLeft(depth*LINE_SPACE);
					pdfTable.addCell(cellLabel);
				}
			}
		}
		return pdfTable;
	}
	
	@Override
	public void onEndPage (PdfWriter writer, Document document) {
			try {
				//document.add(UNDERLINE); 
				ColumnText.showTextAligned(writer.getDirectContent(),  Element.ALIGN_CENTER, new Phrase(String.format("Page %d", writer.getPageNumber()), getDefaultFont(0)), 320f, 20f, 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
	      
	}
}
