package demo.views;

import demo.models.Account;
import demo.models.Client;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class TransferirView extends JPanel {
    
    private JLabel titleLabel;
    private JLabel sourceAccountLabel;
    private JTextField sourceAccountField;
    private JLabel destinationAccountLabel;
    private JTextField destinationAccountField;
    private JLabel transferAmountLabel;
    private JTextField transferAmountField;
    private JButton transferButton;

    public TransferirView() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // "Transferir dinero"
        titleLabel = new JLabel("Transferir dinero");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 18f));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Número de cuenta de origen
        sourceAccountLabel = new JLabel("Número de cuenta de origen");
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(sourceAccountLabel, gbc);

        sourceAccountField = new JTextField(20);
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(sourceAccountField, gbc);

        // Número de cuenta de destino
        destinationAccountLabel = new JLabel("Número de cuenta de destino");
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(destinationAccountLabel, gbc);

        destinationAccountField = new JTextField(20);
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(destinationAccountField, gbc);

        // Monto a transferir
        transferAmountLabel = new JLabel("Monto a transferir");
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(transferAmountLabel, gbc);

        transferAmountField = new JTextField(10);
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(transferAmountField, gbc);

        // Botón Transferir
        transferButton = new JButton("Transferir");
        gbc.gridx = 0; // Cambiar el valor a 0
        gbc.gridy++; // Moverlo a la siguiente fila
        gbc.gridwidth = 2; // Abarcar 2 columnas
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el botón horizontalmente
        add(transferButton, gbc);

        // Espacio
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.weighty = 0.2;
        gbc.fill = GridBagConstraints.BOTH;
        add(new JPanel(), gbc);
    }

    public JTextField getSourceAccountField() {
        return sourceAccountField;
    }

    public JTextField getDestinationAccountField() {
        return destinationAccountField;
    }

    public JTextField getTransferAmountField() {
        return transferAmountField;
    }

       public void transfer(ActionListener actionListener) {
        transferButton.addActionListener(actionListener);
    }

     public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

     public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
