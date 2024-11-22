package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private TextField display;

    private String operator = "";
    private double firstOperand = 0;
    private boolean startNewNumber = true;

    @FXML
    public void handleButton(ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonText = button.getText();

        switch (buttonText) {
            case "C": // Clear the display
                display.clear();
                operator = "";
                firstOperand = 0;
                startNewNumber = true;
                break;

            case "=": // Perform the calculation
                if (!operator.isEmpty()) {
                    double secondOperand = Double.parseDouble(display.getText());
                    double result = calculate(firstOperand, secondOperand, operator);
                    display.setText(String.valueOf(result));
                    operator = "";
                    startNewNumber = true;
                }
                break;

            case "+": case "-": case "*": case "/": // Set the operator
                operator = buttonText;
                firstOperand = Double.parseDouble(display.getText());
                startNewNumber = true;
                break;

            default: // Handle numbers
                if (startNewNumber) {
                    display.clear();
                    startNewNumber = false;
                }
                display.appendText(buttonText);
                break;
        }
    }

    private double calculate(double a, double b, String operator) {
        switch (operator) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return a / b;
            default: throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}