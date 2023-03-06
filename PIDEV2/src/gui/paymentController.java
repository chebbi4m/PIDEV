package gui;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import java.util.HashMap;
import java.util.Map;

import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentMethodAttachParams;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class paymentController {

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
    private void handlePayButtonClicked() {
        String cardNumber = cardNumberField.getText();
        String expirationMonth = expirationMonthField.getText();
        String expirationYear = expirationYearField.getText();
        String cvv = cvvField.getText();
        String amount = amountField.getText();
        String connectedAccountId = "acct_1MibVwELxEPJZ2RK"; // Replace with the connected account ID

        // Set the Stripe API key
        Stripe.apiKey = "sk_test_51MiIphHPT3tx5q3LQEoY7koOBMsG2uo9XX4z5GmWEzJy4NZvRQcpDFtKNtRj3JwKmvgkVKzpBrrnoH8TdkIDA4Il00SGe183ja"; // Replace with your API key


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
        try {
            // Create a payment method
            PaymentMethod paymentMethod = PaymentMethod.create(paymentMethodParams);

            // Get the customer ID
            String customerId = paymentMethod.getCustomer();

            if (customerId == null) {
                // Create a new customer if payment method doesn't have a customer ID
                Map<String, Object> customerParams = new HashMap<>();
                customerParams.put("email", "example@gmail.com");
                Customer customer = Customer.create(customerParams);

                // Update the payment method with the new customer ID
                paymentMethod = paymentMethod.update(new HashMap<String, Object>() {{
                    put("customer", customer.getId());
                }});

                customerId = customer.getId();
            }

            // Attach the payment method to the customer
            Customer customer = Customer.retrieve(customerId);
            PaymentMethodAttachParams attachParams = PaymentMethodAttachParams.builder().setCustomer(customer.getId()).build();
            paymentMethod.attach(attachParams);

            // Create a payment intent
            PaymentIntentCreateParams createParams = PaymentIntentCreateParams.builder()
                    .setAmount(Long.valueOf(amount))
                    .setCurrency("usd")
                    .setPaymentMethod(paymentMethod.getId())
                    .setTransferData(PaymentIntentCreateParams.TransferData.builder()
                            .setDestination(connectedAccountId)
                            .build())
                    .build();
            PaymentIntent paymentIntent = PaymentIntent.create(createParams);

            System.out.println("Payment intent created: " + paymentIntent.getId());
            System.out.println("Payment method attached to Customer: " + customerId);
        } catch (StripeException e) {
            System.out.println("Error creating Payment Intent: " + e.getMessage());
        }


    }}
