package demo.views;

import demo.models.Account;
import demo.models.Client;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class GirarView extends JPanel {

        private JLabel titleLabel;
        private JLabel messageLabel;
        private JTextField runTextField;
        private JButton searchButton;
        private JPanel infoPanel;
        private JLabel withdrawAmountLabel;
        private JTextField withdrawAmountField;
        private JButton withdrawButton;

        public GirarView() {
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

                // Client info
                infoPanel = new JPanel(new GridLayout(2, 0, 10, 10));
                gbc.gridx = 0;
                gbc.gridy++;
                gbc.anchor = GridBagConstraints.WEST;
                add(infoPanel, gbc);
                infoPanel.setVisible(false);

                // Monto a girar
                withdrawAmountLabel = new JLabel("Monto a girar");
                gbc.gridx = 0;
                gbc.gridy++;
                gbc.gridwidth = 2;
                gbc.anchor = GridBagConstraints.CENTER;
                add(withdrawAmountLabel, gbc);

                withdrawAmountField = new JTextField(10);
                gbc.gridy++;
                gbc.gridwidth = 2;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                add(withdrawAmountField, gbc);

                // Botón Girar
                withdrawButton = new JButton("Girar");
                gbc.gridx = 1;
                gbc.gridy++;
                add(withdrawButton, gbc);
                withdrawButton.setVisible(false);

                // Espacio
                gbc.gridx = 0;
                gbc.gridy++;
                gbc.gridwidth = 2;
                gbc.weighty = 0.2;
                gbc.fill = GridBagConstraints.BOTH;
                add(new JPanel(), gbc);

                // Ocultar el campo inicial
                withdrawAmountLabel.setVisible(false);
                withdrawAmountField.setVisible(false);
        }

        public JTextField getRunTextField() {
                return runTextField;
        }

        public void load(Client client) {
                Account account = client.getAccount();
                JLabel accountType = new JLabel("Cuenta De " + account.getDescription());
                JLabel balance = new JLabel("Saldo: " + Integer.toString(account.checkBalance()));
                accountType.setFont(accountType.getFont().deriveFont(Font.BOLD));
                balance.setFont(balance.getFont().deriveFont(Font.BOLD));

                infoPanel.removeAll();
                infoPanel.add(accountType);
                infoPanel.add(balance);
                infoPanel.setVisible(true);
                withdrawButton.setVisible(true);
                showWithdrawAmountField();

                revalidate();
                repaint();
        }

        private void showWithdrawAmountField() {
                withdrawAmountLabel.setVisible(true);
                withdrawAmountField.setVisible(true);
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

        public JButton getWithdrawButton() {
                return withdrawButton;
        }

        public void withdrawBtn(ActionListener actionListener) {
                withdrawButton.addActionListener(actionListener);
        }

        public JTextField getWithdrawAmountField() {
                return withdrawAmountField;
        }
}
