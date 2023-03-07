package gui;
import com.sendgrid.*;



import java.io.IOException;


public class PaymentConfirmationEmail {
    private static final String SENDGRID_API_KEY = "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";
    private static final String SENDER_EMAIL = "mootez.nasri@esprit.tn";

    public static void sendConfirmationEmail(String recipientEmail, String paymentAmount) {
        Email from = new Email(SENDER_EMAIL);
        Email to = new Email(recipientEmail);
        String subject = "Payment Confirmation";
        Content content = new Content("text/plain", "Thank you for your payment of " + paymentAmount + "!");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(SENDGRID_API_KEY);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            System.out.println("Error sending email: " + ex.getMessage());
        }
    }
}
