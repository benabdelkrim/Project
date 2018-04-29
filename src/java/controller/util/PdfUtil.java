package controller.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author simob
 */
public class PdfUtil {

    private static String absoluteJasperPath;
    private static String absoluteWebPath;


    public static void generatePdf(List myList, Map<String, Object> params, String outPoutFileName, String bilan) throws JRException, IOException {
        if (myList == null || myList.isEmpty()) {
            myList = new ArrayList();
            myList.add(new Object());
        }
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(myList);
        JasperPrint jasperPrint = JasperFillManager.fillReport(PdfUtil.class.getResourceAsStream(bilan), params, jrBeanCollectionDataSource);
        OutputStream outputStream = getResponseOutput(outPoutFileName);
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
    }
  
    private static OutputStream getResponseOutput(String fileName) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        httpServletResponse.addHeader("Pragma", "no-cache"); // HTTP 1.0.
        httpServletResponse.addHeader("Expires", "0"); // Proxies.
        httpServletResponse.addHeader("Content-Type", "application/pdf");
        httpServletResponse.addHeader("Content-Disposition", "attachment; filename=" + fileName + ".pdf");
        return httpServletResponse.getOutputStream();
    }

 


}
