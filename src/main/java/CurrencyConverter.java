import javax.swing.*;import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CurrencyConverter extends Component implements ActionListener {

    private final JTextField textField;
    private final JComboBox<Object> comboBoxTO;
    private final JComboBox<Object> comboBoxFROM;
    private final JButton submitButton;


    private final String option1 = "USD (United States Dollars)";
    private final String option2 = "CAD (Canadian Dollars)";
    private final String option3 = "EUR (Euros)";


    public CurrencyConverter() {

        JLabel label1, label2, label3;
        label1 = new JLabel("Amount:");
        label2  = new JLabel("From:");
        label3 = new JLabel("To:");

        // User's Input Field
        textField = new JTextField();
        textField.setBounds(500, 500, 500, 500);
        textField.setColumns(8);

        // From Currency
        comboBoxFROM = new JComboBox<>();
        comboBoxFROM.addItem(option1);
        comboBoxFROM.addItem(option2);
        comboBoxFROM.addItem(option3);
        comboBoxFROM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateComboBoxTO();
            }
        });

        // To Currency
        comboBoxTO = new JComboBox<>();
        comboBoxTO.addItem(option1);
        comboBoxTO.addItem(option2);
        comboBoxTO.addItem(option3);

        //Convert Button
        submitButton = new JButton("Convert");
        submitButton.addActionListener(this);


        JPanel panel;
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setBounds(100, 100, 610, 610);
        panel.add(label1);
        panel.add(textField);
        panel.add(label2);
        panel.add(comboBoxFROM);
        panel.add(label3);
        panel.add(comboBoxTO);
        panel.add(submitButton);

        JFrame frame;
        frame = new JFrame();
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Currency Converter");
        frame.pack();
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

    }

    private void updateComboBoxTO() {
        comboBoxTO.removeAllItems(); // Clear existing items

        Object selectedFromItem = comboBoxFROM.getSelectedItem();

        if (selectedFromItem.equals(option1)) {
            comboBoxTO.addItem(option2);
            comboBoxTO.addItem(option3);

        } else if (selectedFromItem.equals(option2)) {
            comboBoxTO.addItem(option1);
            comboBoxTO.addItem(option3);

        } else if (selectedFromItem.equals(option3)) {
            comboBoxTO.addItem(option1);
            comboBoxTO.addItem(option2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitButton) {
            ConvertActionPerformed(e);
        }
    }

    public void ConvertActionPerformed(ActionEvent e) {
        String text = textField.getText();

        if (!text.isEmpty()) {
            double amount = Double.parseDouble(text);
            double conversionRate = 0.00;

            // USD to USD
            if (comboBoxFROM.getSelectedItem().equals(option1) &&
                    comboBoxTO.getSelectedItem().equals(option1)) {
                JOptionPane.showMessageDialog(null, "You've selected the same currency FROM/TO. Please make another selection.");
                conversionRate = 1.00;
            }

            // USD to CAD
            else if (comboBoxFROM.getSelectedItem().equals(option1) &&
                    comboBoxTO.getSelectedItem().equals(option2)) {
                conversionRate = 1.34;
            }

            //USD to EUR
            else if (comboBoxFROM.getSelectedItem().equals(option1) &&
                    comboBoxTO.getSelectedItem().equals(option3)) {
                conversionRate = 0.91;
            }

            //CAD to USD
            else if (comboBoxFROM.getSelectedItem().equals(option2) &&
                    comboBoxTO.getSelectedItem().equals(option1)) {
                conversionRate = 0.74;
            }

            // CAD to EUR
            else if (comboBoxFROM.getSelectedItem().equals(option2) &&
                    comboBoxTO.getSelectedItem().equals(option3)) {
                conversionRate = 0.68;
            }

            // EUR to USD
            else if (comboBoxFROM.getSelectedItem().equals(option3) &&
                    comboBoxTO.getSelectedItem().equals(option1)) {
                conversionRate = 1.10;
            }

            // EUR to CAD
            else if (comboBoxFROM.getSelectedItem().equals(option3) &&
                    comboBoxTO.getSelectedItem().equals(option2)) {
                conversionRate = 1.47;
            }
            double conversion = amount * conversionRate;
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String formattedConversion = decimalFormat.format(conversion);
            JOptionPane.showMessageDialog(null, "Converted amount: " + formattedConversion);

        } else {
            JOptionPane.showMessageDialog(null, "Please enter an amount to convert.");
        }
    }

    public static void main(String[] arg) {
        CurrencyConverter converter = new CurrencyConverter();
    }
}
