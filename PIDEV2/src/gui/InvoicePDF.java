package gui;

import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class InvoicePDF {
    public static void createInvoicePDF(String fileName, String invoiceContent) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            document.add(new Paragraph(invoiceContent));
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
