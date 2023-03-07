package gui;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.param.PaymentIntentConfirmParams;
import com.stripe.param.PaymentMethodAttachParams;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class paiementController {

    private static final String STRIPE_SECRET_KEY = "your_stripe_secret_key_here";

    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField expirationMonthField;

    @FXML
    private TextField expirationYearField;

    @FXML
    private TextField cvvField;

    @FXML
    private TextField amountField;

    @FXML
    private Button payButton;

        // Create the email message


    private void sendPaymentConfirmationEmail(String recipientEmail, String amount, String paymentIntentId) throws MessagingException {
        String myAccountEmail = "mootez.nasri@esprit.tn"; // Replace with your email address
        String myAccountPassword = "223JMT4611"; // Replace with your email password

        // Set up the properties for the email session
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com"); // Replace with your email provider's SMTP host
        properties.put("mail.smtp.port", "587"); // Replace with your email provider's SMTP port

        // Authenticate with the email server
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, myAccountPassword);
            }
        });

        // Prepare the message
        Message message = prepareMessage(session, myAccountEmail, recipientEmail, amount, paymentIntentId);

        // Send the message
        Transport.send(message);
        System.out.println("Payment confirmation email sent successfully!");
    }

    private Message prepareMessage(Session session, String myAccountEmail, String recipientEmail, String amount, String paymentIntentId) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myAccountEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
        message.setSubject("Payment Confirmation");
        String htmlCode = "<h1>Thank you for your payment of $" + amount + "</h1>" +
                "<p>Your payment has been received and your payment ID is " + paymentIntentId + "</p>"+"<b>Payment Card Number:</b>"+cardNumberField.getText();
        message.setContent(htmlCode, "text/html");
        return message;
    }

    @FXML
    private  void handlePayButtonClicked() {
        String cardNumber = cardNumberField.getText();
        String expirationMonth = expirationMonthField.getText();
        String expirationYear = expirationYearField.getText();
        String cvv = cvvField.getText();
        String amount = amountField.getText();
        String connectedAccountId = "acct_1MibVwELxEPJZ2RK"; // Replace with the connected account ID

        // Set the Stripe API key
        Stripe.apiKey = "rk_test_51MiIphHPT3tx5q3Li2N2Uk9TYvnZ83IQhmE4maROllJyENwvPeXjU1TjwpqrIObF8akKdlDsSRBo4pWmu4tnZAOH00pQ3Vj9Ky"; // Replace with your API key

        // Create a map with the card details
        Map<String, Object> cardParams = new HashMap<>();
        cardParams.put("number", cardNumber);
        cardParams.put("exp_month", expirationMonth);
        cardParams.put("exp_year", expirationYear);
        cardParams.put("cvc", cvv);

        // Create a map with the payment method details
        Map<String, Object> paymentMethodParams = new HashMap<>();
        paymentMethodParams.put("type", "card");
        paymentMethodParams.put("card", cardParams);
        paymentMethodParams.put("billing_details", new HashMap<String, Object>() {{
            put("name", "John Doe");
        }});
        paymentMethodParams.put("metadata", new HashMap<String, Object>() {{
            put("order_id", "123456789");
        }});

        Customer customer = null;
        try {
            // Create a payment method
            PaymentMethod paymentMethod = PaymentMethod.create(paymentMethodParams);


            // Get the customer ID associated with the payment method, if it exists
            String customerId = paymentMethod.getCustomer();

            if (customerId == null) {
                // Create a new customer if payment method doesn't have a customer ID
                Map<String, Object> customerParams = new HashMap<>();
                customerParams.put("payment_method", paymentMethod.getId());
                customerParams.put("email", "mootez202@gmail.com");
                customer = Customer.create(customerParams);
                customerId = customer.getId();
            }


            // Attach the payment method to the customer
            PaymentMethodAttachParams paymentMethodAttachParams = PaymentMethodAttachParams.builder()
                    .setCustomer(customerId)
                    .build();
            paymentMethod.attach(paymentMethodAttachParams);

            // Create a map with the payment intent details
            Map<String, Object> paymentIntentParams = new HashMap<>();
            paymentIntentParams.put("amount", amount);
            paymentIntentParams.put("currency", "usd");
            paymentIntentParams.put("payment_method", paymentMethod.getId());
            paymentIntentParams.put("customer", customerId);
            paymentIntentParams.put("transfer_data", new HashMap<String, Object>() {{
                put("destination", connectedAccountId);
            }});

// Create the payment intent
            PaymentIntent paymentIntent = PaymentIntent.create(paymentIntentParams);


            System.out.println("Payment intent created: " + paymentIntent.getId());
            System.out.println("Payment method attached to Customer: " + customerId);
            PaymentIntentConfirmParams confirmParams = PaymentIntentConfirmParams.builder()
                    .setPaymentMethod(paymentMethod.getId())
                    .build();
            PaymentIntent confirmedPaymentIntent = paymentIntent.confirm(confirmParams);

            System.out.println("Payment intent confirmed: " + confirmedPaymentIntent.getId());
            // Send the payment confirmation email
            try {
                sendPaymentConfirmationEmail(customer.getEmail(), amount, confirmedPaymentIntent.getId());
            } catch (MessagingException e) {
                System.out.println("Error sending payment confirmation email: " + e.getMessage());
            }





        } catch (StripeException e) {
            System.out.println("Error creating Payment Intent: " + e.getMessage());
        }






    }
    }





