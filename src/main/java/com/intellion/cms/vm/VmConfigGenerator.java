package com.intellion.cms.vm;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;

import org.apache.commons.lang.StringUtils;
import com.intellion.cms.domain.Prescription;
import com.intellion.cms.domain.dto.PrescriptionDto;

public class VmConfigGenerator {
	private final String BASE_DIR = "report";
    private VelocityEngine ve = null;
   
    public VmConfigGenerator(){
        this.ve = initiateVelocityEngine();
    }
   

    private VelocityEngine initiateVelocityEngine() {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
        return ve;
    }
    
    public String generatePrescriptionConfiguration(PrescriptionDto prescriptionDto) {
    	String vtFileName = BASE_DIR + "/" + "prescription.vm";
    	
        try{
            if(ve == null){
                ve = initiateVelocityEngine();
            }
            Template t = ve.getTemplate(vtFileName);
            VelocityContext context = new VelocityContext();
            context.put("prescription", prescriptionDto);
            context.put("StringUtils", StringUtils.class);
            context.put("newline", "\n");
            StringWriter writer = new StringWriter();
            t.merge(context, writer);
            return writer.toString();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "";
	}
/*
    public static void main(String[]  args){
        try {
            System.out.println("started");
            VmConfigGenerator defaultConfigVisitor = new VmConfigGenerator();
            List<Table> tableList = VmConfigGeneratorTest.mockMap();
            //List<InvestigationReportDto> list = mockInvestigationReportRecord();
            //String config = defaultConfigVisitor.generateConfiguration1(list);
            //String config = defaultConfigVisitor.generateConfiguration(listOfList);
            System.out.println(tableList);
            Table table = tableList.get(0);
            //String config = defaultConfigVisitor.generateConfiguration(tableList,null,null);
            //System.out.println(config);
//            for(int i =0;i<3;i++) {
//                for(int c=0;c<6;c++) {
//                    String[][] list = table.getRowDataList();
//                    System.out.println("" + i + ":" + c + "--" + list[c][i]);
//                }
//            }
           
            //DownloadService downloadService = new DownloadService();
            //downloadService.downloadXLS(table);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    public String generateConfiguration(Table table)  {
        String vtFileName = "coretable.vm";
        try{
            if(ve == null){
                ve = initiateVelocityEngine();
            }
            Template t = ve.getTemplate(vtFileName);
            VelocityContext context = new VelocityContext();
            context.put("table", table);
            context.put("StringUtils", StringUtils.class);
            context.put("newline", "\n");
            StringWriter writer = new StringWriter();
            t.merge(context, writer);
            return writer.toString();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
    
    public String generateConfigurationPrintView(Table table,String title)  {
        String vtFileName = "printviewtemplate.vm";
        try{
            if(ve == null){
                ve = initiateVelocityEngine();
            }
            Template t = ve.getTemplate(vtFileName);
            VelocityContext context = new VelocityContext();
            context.put("table", table);
            context.put("title", title);
            context.put("StringUtils", StringUtils.class);
            context.put("newline", "\n");
            StringWriter writer = new StringWriter();
            t.merge(context, writer);
            return writer.toString();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
   
    public String generateConfiguration(List<Table> investigationReportList,
            ConsultationRecordDto consultationRecord,
            List<DrugsDto> drugsList,List<Table> therapyTableList)  {
        String vtFileName = "htmlreporttemplate.vm";
        try{
            if(ve == null){
                ve = initiateVelocityEngine();
            }
            Template t = ve.getTemplate(vtFileName);
            VelocityContext context = new VelocityContext();
            context.put("tableList", investigationReportList);
            context.put("therapyTableList", therapyTableList);
            context.put("consultationRecord", consultationRecord);
            context.put("drugsList", drugsList);
            context.put("StringUtils", StringUtils.class);
            context.put("newline", "\n");
            StringWriter writer = new StringWriter();
            t.merge(context, writer);
            return writer.toString();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
   
    public String generateConfiguration(List<PatientHealthSummary> patientHealthSummaryList)  {
        String vtFileName = "patienthealthsummary.vm";
        try{
            if(ve == null){
                ve = initiateVelocityEngine();
            }
            Template t = ve.getTemplate(vtFileName);
            VelocityContext context = new VelocityContext();
            context.put("patientHealthSummaryList", patientHealthSummaryList);
            context.put("StringUtils", StringUtils.class);
            context.put("newline", "\n");
            StringWriter writer = new StringWriter();
            t.merge(context, writer);
            return writer.toString();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
   
    public String generateInvoiceConfiguration(InvoiceVmData invoiceVmData)  {
        String vtFileName = "invoicevmtemplate.vm";
        try{
            if(ve == null){
                ve = initiateVelocityEngine();
            }
            Template t = ve.getTemplate(vtFileName);
            VelocityContext context = new VelocityContext();
            context.put("invoiceVmData", invoiceVmData);
            context.put("StringUtils", StringUtils.class);
            context.put("newline", "\n");
            StringWriter writer = new StringWriter();
            t.merge(context, writer);
            return writer.toString();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
    
    public String generateReceiptConfiguration(ReceiptVmData receiptVmData)  {
        String vtFileName = "receipttemplate.vm";
        try{
            if(ve == null){
                ve = initiateVelocityEngine();
            }
            Template t = ve.getTemplate(vtFileName);
            VelocityContext context = new VelocityContext();
            context.put("receiptVmData", receiptVmData);
            context.put("StringUtils", StringUtils.class);
            context.put("newline", "\n");
            StringWriter writer = new StringWriter();
            t.merge(context, writer);
            return writer.toString();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
    
    public String generateReceiptConfigurationView(ReceiptVmData receiptVmData)  {
        String vtFileName = "receipttemplateView.vm";
        try{
            if(ve == null){
                ve = initiateVelocityEngine();
            }
            Template t = ve.getTemplate(vtFileName);
            VelocityContext context = new VelocityContext();
            context.put("receiptVmData", receiptVmData);
            context.put("StringUtils", StringUtils.class);
            context.put("newline", "\n");
            StringWriter writer = new StringWriter();
            t.merge(context, writer);
            return writer.toString();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
    public String generateConfigurationWithoutHeader(List<PatientHealthSummary> patientHealthSummaryList)  {
        String vtFileName = "patienthealthsummarywithoutcss.vm";
        try{
            if(ve == null){
                ve = initiateVelocityEngine();
            }
            Template t = ve.getTemplate(vtFileName);
            VelocityContext context = new VelocityContext();
            context.put("patientHealthSummaryList", patientHealthSummaryList);
            context.put("StringUtils", StringUtils.class);
            context.put("newline", "\n");
            StringWriter writer = new StringWriter();
            t.merge(context, writer);
            return writer.toString();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
*/

	
   
}