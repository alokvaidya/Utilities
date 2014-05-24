import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {
    public MailSender(String subject, String content) {
        // Recipient's email ID needs to be mentioned.
        String to = "abbas@directologies.com";
        String cc = "alok@directologies.com";

        // Sender's email ID needs to be mentioned
        String from = "alok@directologies.com";

        // Assuming you are sending email from localhost
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        properties.setProperty("mail.smtp.starttls.enable", "true");

        properties.setProperty("mail.smtp.auth", "true");
        // Get the default Session object.
        //Session session = Session.getDefaultInstance(properties);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        "alok@directologies.com", "tqbfjotld2454");
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(content, "utf-8", "html");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
