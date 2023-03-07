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
    private void sendPaymentConfirmationEmail(String customerEmail, String amount, String paymentIntentId) throws MessagingException {

        // Set up the email server properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Set up the email session
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("mootez.nasri@esprit.tn", "223JMT4611");
                    }
                });

        // Create the email message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("mootez.nasri@esprit.tn"));
        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(customerEmail));
        message.setSubject("Payment Confirmation");
        message.setText("Dear Customer,\n\nYour payment of $" + amount + " has been processed successfully.\n\nPayment ID: " + paymentIntentId);

        // Send the email
        Transport.send(message);
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





