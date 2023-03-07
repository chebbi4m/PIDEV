package gui;

import com.sendgrid.*;
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


import java.util.HashMap;
import java.util.Map;

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
            Email CUSTOMER_EMAIL = new Email(customer.getEmail());
            String paymentAmount = String.valueOf(paymentIntent.getAmount() / 100);
            PaymentConfirmationEmail.sendConfirmationEmail(String.valueOf(CUSTOMER_EMAIL), paymentAmount);


        } catch (StripeException e) {
            System.out.println("Error creating Payment Intent: " + e.getMessage());
        }






    }
    }





