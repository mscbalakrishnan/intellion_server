package com.intellion.cms.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intellion.cms.util.CommonUtil;

/*import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;*/

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;

public class PDFGenerator {
	
	private static PDFGenerator pdfGenerator = null;
	private static String pdfLocation;
	private static String imageLocation;
	private static String imageName;
	private String FILE_SEPERATOR = File.separator;
	private static Document document = null;
	private final static Logger logger = LoggerFactory.getLogger(PDFGenerator.class.getClass());
	private static Integer depth = 0;
	private PDFGenerator(){
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String path = classLoader.getResource("").getPath();
		path = CommonUtil.getFilePath(path);

		imageLocation = path+".."+FILE_SEPERATOR+".."+FILE_SEPERATOR+"export"+FILE_SEPERATOR+"jrxml"+FILE_SEPERATOR;
		pdfLocation = path+"../../Reports/";
		imageName = "test.png";
	}
	
	private static PDFGenerator getInstance(){
		
		if(pdfGenerator == null){
			pdfGenerator = new PDFGenerator();
		}
		return pdfGenerator;
	}
	
	public static void generatePdfSummaryReport(SummaryRequest summaryData, String fileName){
		try {
			getInstance();
			document = new Document(PageSize.A4, 20, 20, 20, 50);
//			System.out.println(pdfLocation);
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfLocation + fileName));
			PDFLayout event = new PDFLayout();
			writer.setPageEvent(event);
			document.open();
//			System.out.println("document opened");
			//Add title section
			//document.add(PDFLayout.addTilteImage(imageLocation+ imageName));
			document.add(PDFLayout.generateTitleSection(summaryData.getTitle(), summaryData.getSubTitle(), null));
			document.add(PDFLayout.addPaddingTop(20));
			processSectionSummaryData(summaryData.getSectionList());
			document.close();
		} catch (Exception e) {
			logger.error("Exception::", e);
		}
	}
	
	private static void processSectionSummaryData(List<SummarySection> sectionList) throws Exception{
		
		if(sectionList != null)
		for(SummarySection section: sectionList){
			
			if(section.getTitle() != null && !section.getTitle().isEmpty()){
				document.add(PDFLayout.generateSubHeader(section.getTitle(), depth));
				depth++;
			}
			
			if(section.getSectionValues() != null){
				
				document.add(PDFLayout.generateLabelGroupData(section.getSectionValues(), depth));
				if(depth > 0)
					depth--;
			}else if(section.getSubSection() != null){
				System.out.println("#####"+depth);
				
				processSectionSummaryData(section.getSubSection());
				if(depth > 0)
					depth--;
			}else if(section.getTableDataList() != null && !section.getTableDataList().isEmpty()){
				document.add(PDFLayout.addPaddingTop(20));
				document.add(PDFLayout.generateTableData(section.getTableDataList(), depth));
			}
		}
		
	}
	
	public static void main(String arg[]){
		SummaryRequest summary = new SummaryRequest();
		summary.setTitle("AFM SUMMARY REPORT");
		summary.setSubTitle("Design Summary");
		
		//Label Data
		List<SummarySection> sectionList = new ArrayList<SummarySection>();
		
		SummarySection labelSection = new SummarySection();
		List<PropertyInfo> propertyList = new ArrayList<PropertyInfo>();
		propertyList.add(new PropertyInfo("Fabric Name:", "EastCore"));
		propertyList.add(new PropertyInfo("Fabric Type:", "Layer 2 Fabric - Virtual Link Trunking (VLT)"));
		propertyList.add(new PropertyInfo("Deployment Type:", "Type 3 - 10 Gb Top of Rack"));
		propertyList.add(new PropertyInfo("Total Spines:", "2"));
		propertyList.add(new PropertyInfo("Total Leafs:", "2"));
		propertyList.add(new PropertyInfo("Spine Device Type:", "Z9000"));
		labelSection.setValues(propertyList);
		sectionList.add(labelSection);
		
		// Section data
		
		SummarySection subSection = new SummarySection();
		subSection.setTitle("Uplink Ports");
		List<PropertyInfo> feildList = new ArrayList<PropertyInfo>();
		feildList.add(new PropertyInfo("Current uplink ports:", "2"));
		feildList.add(new PropertyInfo("User specified expansion uplink ports:", "0"));
		feildList.add(new PropertyInfo("Remaining future expansion uplink ports:", "6"));
		feildList.add(new PropertyInfo("Total uplink ports:", "2"));
		feildList.add(new PropertyInfo("Total Leafs:", "2"));
		feildList.add(new PropertyInfo("Bandwidth (in Gb):", "40"));
		subSection.setValues(feildList);
		sectionList.add(subSection);
		
		
		
		// Sub section List
		SummarySection subSectionData = new SummarySection();
		subSectionData.setTitle("Cable Suggestion");
		List<SummarySection> subSectionList = new ArrayList<SummarySection>();
		
		SummarySection subSection1 = new SummarySection();
		subSection1.setTitle("Copper Cables for Fabric Link, VLTi Link and Uplink");
		List<PropertyInfo> feildList1 = new ArrayList<PropertyInfo>();
		feildList1.add(new PropertyInfo("Number of 40G-40G DACs:", "10"));
		feildList1.add(new PropertyInfo("Number of 10G-10G DACs:", "0"));
		feildList1.add(new PropertyInfo("Number of 10G-40G DACs :", "0"));
		subSection1.setValues(feildList1);
		
		SummarySection subSection2 = new SummarySection();
		subSection2.setTitle("Optical Fibers Cables/Optics:");
		List<PropertyInfo> feildList2 = new ArrayList<PropertyInfo>();
		feildList2.add(new PropertyInfo("* Number of Fiber Cables:", "10"));
		feildList2.add(new PropertyInfo("* SFP+ Modules (10G):", "0"));
		feildList2.add(new PropertyInfo("* QSFP Modules (40G):", "18"));
		subSection2.setValues(feildList2);
		
		subSectionList.add(subSection1);
		subSectionList.add(subSection2);
		subSectionData.setSubSection(subSectionList);
		sectionList.add(subSectionData);
		
		//Adding table data
		SummarySection tableSection = new SummarySection();
		tableSection.setTitle("Uplink Details");
		List<TableData> tableDataList = new ArrayList<TableData>();
		TableData tableData = new TableData();
		
		ArrayList<String> headings = new ArrayList<String>(Arrays.asList("Switch Name", "Port Number", "Local IP", "Remote IP", "Area ID"));
		tableData.setTableHeading(headings);
		List<List<String>> rowData = new ArrayList<List<String>>();
		ArrayList<String> row1 = new ArrayList<String>(Arrays.asList("EASTCORE-SPINE-1", "18", "10.1.1.1", "10.1.1.1", "0"));
		ArrayList<String> row2 = new ArrayList<String>(Arrays.asList("EASTCORE-SPINE-1", "18", "10.1.1.1", "10.1.1.1", "0"));
		ArrayList<String> row3 = new ArrayList<String>(Arrays.asList("EASTCORE-SPINE-1", "18", "10.1.1.1", "10.1.1.1", "0"));
		ArrayList<String> row4 = new ArrayList<String>(Arrays.asList("EASTCORE-SPINE-1", "18", "10.1.1.1", "10.1.1.1", "0"));
		rowData.add(row1);
		rowData.add(row2);
		rowData.add(row3);
		rowData.add(row4);
		tableData.setTableData(rowData);
		tableDataList.add(tableData);
		tableSection.setTableDataList(tableDataList);
		sectionList.add(tableSection);
		
		SummarySection labelSectionEx = new SummarySection();
		List<PropertyInfo> propertyListEX = new ArrayList<PropertyInfo>();
		propertyListEX.add(new PropertyInfo("Fabric Name:", "EastCore"));
		propertyListEX.add(new PropertyInfo("Fabric Type:", "Layer 2 Fabric - Virtual Link Trunking (VLT)"));
		propertyListEX.add(new PropertyInfo("Deployment Type:", "Type 3 - 10 Gb Top of Rack"));
		propertyListEX.add(new PropertyInfo("Total Spines:", "2"));
		propertyListEX.add(new PropertyInfo("Total Leafs:", "2"));
		propertyListEX.add(new PropertyInfo("Spine Device Type:", "Z9000"));
		propertyListEX.add(new PropertyInfo("Fabric Name:", "EastCore"));
		propertyListEX.add(new PropertyInfo("Fabric Type:", "Layer 2 Fabric - Virtual Link Trunking (VLT)"));
		propertyListEX.add(new PropertyInfo("Deployment Type:", "Type 3 - 10 Gb Top of Rack"));
		propertyListEX.add(new PropertyInfo("Total Spines:", "2"));
		propertyListEX.add(new PropertyInfo("Total Leafs:", "2"));
		propertyListEX.add(new PropertyInfo("Spine Device Type:", "Z9000"));
		propertyListEX.add(new PropertyInfo("Fabric Name:", "EastCore"));
		propertyListEX.add(new PropertyInfo("Fabric Type:", "Layer 2 Fabric - Virtual Link Trunking (VLT)"));
		propertyListEX.add(new PropertyInfo("Deployment Type:", "Type 3 - 10 Gb Top of Rack"));
		propertyListEX.add(new PropertyInfo("Total Spines:", "2"));
		propertyListEX.add(new PropertyInfo("Total Leafs:", "2"));
		propertyListEX.add(new PropertyInfo("Spine Device Type:", "Z9000"));
		labelSectionEx.setValues(propertyListEX);
		sectionList.add(labelSectionEx);
		
		summary.setSectionList(sectionList);
		
		generatePdfSummaryReport(summary, "SummaryReport.pdf");
	}
}
