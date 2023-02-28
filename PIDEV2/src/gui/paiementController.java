package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class paiementController {

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
    private void initialize() {
        // Add any initialization code here
    }

    @FXML
    private void handlePayButtonClicked() {
        // Handle the pay button click event here
        String cardNumber = cardNumberField.getText();
        String expirationMonth = expirationMonthField.getText();
        String expirationYear = expirationYearField.getText();
        String cvv = cvvField.getText();
        String amount = amountField.getText();
        // Perform payment processing here
    }


}
