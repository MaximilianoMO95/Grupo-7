package demo.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DepositView extends JPanel {
    
    private JLabel titleLabel;
    private JLabel messageLabel;
    public JTextField runTextField;
    public JButton searchButton;
    private JLabel depositAmountLabel;
    private JTextField depositAmountField;

    public DepositView() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // "Buscar cliente"
        titleLabel = new JLabel("Buscar cliente");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 18f));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Ingrese el rut del cliente
        messageLabel = new JLabel("Ingrese el rut del cliente");
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(messageLabel, gbc);

        // Rut
        runTextField = new JTextField(20);
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(runTextField, gbc);

        // Buscar
        searchButton = new JButton("Buscar");
        gbc.gridx = 1;
        add(searchButton, gbc);

        // Monto a depositar
        depositAmountLabel = new JLabel("Monto a depositar");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(depositAmountLabel, gbc);

        depositAmountField = new JTextField(10);
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(depositAmountField, gbc);

        // Espacio
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.weighty = 0.2;
        gbc.fill = GridBagConstraints.BOTH;
        add(new JPanel(), gbc);

        // Ocultar el campo de monto a depositar inicialmente
        depositAmountLabel.setVisible(false);
        depositAmountField.setVisible(false);
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JTextField getRunTextField() {
        return runTextField;
    }

    public void showDepositAmountField() {
        depositAmountLabel.setVisible(true);
        depositAmountField.setVisible(true);

        revalidate();
        repaint();
    }

    public void searchClient(ActionListener actionListener) {
        searchButton.addActionListener(actionListener);
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
     
