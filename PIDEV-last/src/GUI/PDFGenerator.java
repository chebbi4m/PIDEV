package GUI;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFGenerator {

    public static void generateMdtPdf(String fileName, List<String> mdtDetails) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        // Create a content stream for adding to the PDF
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Set the font
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);

        // Define the content to be added to the PDF
        List<String> content = Arrays.asList(
                "Marque: " + mdtDetails.get(0),
                "Type: " + mdtDetails.get(1),
                "Matricule: " + mdtDetails.get(2)
        );

        // Set the position for adding the content
        float startX = 100;
        float startY = page.getMediaBox().getHeight() - 100;

        // Add the content to the PDF
        for (String line : content) {
            contentStream.beginText();
            contentStream.newLineAtOffset(startX, startY);
            contentStream.showText(line);
            contentStream.endText();
            startY -= 30;
        }

        // Close the content stream
        contentStream.close();

        // Save the PDF document
        document.save(fileName);

        // Close the PDF document
        document.close();
    }

}
