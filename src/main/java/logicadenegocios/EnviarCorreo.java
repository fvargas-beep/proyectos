package logicadenegocios;

/**
 *
 * @author felipecorralesvargas
 */
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EnviarCorreo {

  public static void enviarMail(String email){
    try {
      String host = "smtp.office365.com";
      String username = "felipaocorrales2310@outlook.com";
      String password = "Gastro1307@";
      int port = 587;
      Properties props = new Properties();
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", port);
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      Session session = Session.getInstance(props, null);
      
      
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(username));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
      message.setSubject("Información Solicitada");
      message.setText("holaaaaaa");
      Transport transport = session.getTransport("smtp");
      transport.connect(host, username, password);
      transport.sendMessage(message, message.getAllRecipients());
      transport.close();
    } catch (AddressException  ex) {
      Logger.getLogger(EnviarCorreo.class.getName()).log(Level.SEVERE, null, ex);
    } catch (MessagingException ex) {
      Logger.getLogger(EnviarCorreo.class.getName()).log(Level.SEVERE, null, ex);
    }
    
  }
    
  public void enviarMail(String email, String nombreDocumento){
    String host = "smtp.office365.com";
    String username = "felipaocorrales2310@outlook.com";
    String password = "Gastro1307@";
    int port = 587;
    Properties props = new Properties();
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.port", port);
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    Session session = Session.getInstance(props, null);

    try {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(username));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
      message.setSubject("Información Solicitada");
      MimeBodyPart mimeBodyPart = new MimeBodyPart();
      mimeBodyPart.attachFile(nombreDocumento);
      MimeMultipart mimeMultipart = new MimeMultipart();
      mimeMultipart.addBodyPart(mimeBodyPart);
      message.setContent(mimeMultipart);

      Transport transport = session.getTransport("smtp");
      transport.connect(host, username, password);
      transport.sendMessage(message, message.getAllRecipients());
      transport.close();
    } catch (MessagingException | IOException e) {
      e.printStackTrace();
    }    
  }
  
  public static void enviarMail1(){
        
        // Generar el archivo PDF
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("AtitUp.pdf"));
            document.open();
            document.add(new Paragraph("holaaa"));
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException ex) {
      Logger.getLogger(EnviarCorreo.class.getName()).log(Level.SEVERE, null, ex);
    }

        // Configurar la sesión de correo electrónico
        String host = "smtp.office365.com";
        String username = "atitupjomm@outlook.com";
        String password = "atitup2023";
        int port = 587;
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, null);

        try {
            // Crear el mensaje de correo electrónico
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("felipaocorrales2310@gmail.com"));
            message.setSubject("Información\n");
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.attachFile("AtitUp.pdf");
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(mimeBodyPart);
            message.setContent(mimeMultipart);

            // Enviar el correo electrónico
            Transport transport = session.getTransport("smtp");
            transport.connect(host, username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            System.out.println("El archivo PDF fue enviado a la dirección de correo electrónico: felipaocorrales2310@gmail.com");
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }         
  }
}
