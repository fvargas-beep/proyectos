package logicadenegocios;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;

public class CrearPDF {
  public void generarPDF(String texto, String pNombrePDF){
    Document document = new Document();
    try {
      PdfWriter.getInstance(document, new FileOutputStream(pNombrePDF));
      document.open();
      Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);
      Paragraph chunk = new Paragraph(texto, font);
      document.add(chunk);
      document.close();
    } catch (DocumentException | IOException e) {
      e.printStackTrace();
    }
  }
}
