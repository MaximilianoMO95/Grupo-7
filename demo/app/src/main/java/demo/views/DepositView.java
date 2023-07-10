package demo.views;

import demo.models.Account;
import demo.models.Client;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class DepositView extends JPanel {

        private JLabel titleLabel;
        private JLabel messageLabel;
        private JTextField runTextField;
        private JTextField dvTextField;
        private JButton searchButton;
        private JPanel infoPanel;
        private JLabel depositAmountLabel;
        private JTextField depositAmountField;
        private JButton depositButton;

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
                runTextField = new JTextField(15);
                gbc.gridy = 2;
                gbc.gridwidth = 1;
                add(runTextField, gbc);
                
                // DV
                dvTextField = new JTextField(1);
                gbc.gridx = 1;
                add(dvTextField, gbc);

                // Buscar
                searchButton = new JButton("Buscar");
                gbc.gridx = 3;
                add(searchButton, gbc);

                // info cliente
                infoPanel = new JPanel(new GridLayout(2, 0, 10, 10));
                gbc.gridx = 0;
                gbc.gridy++;
                gbc.anchor = GridBagConstraints.WEST;
                add(infoPanel, gbc);
                infoPanel.setVisible(false);

                // Monto a depositar
                depositAmountLabel = new JLabel("Monto a depositar");
                gbc.gridx = 0;
                gbc.gridy++;
                gbc.gridwidth = 2;
                gbc.anchor = GridBagConstraints.CENTER;
                add(depositAmountLabel, gbc);

                depositAmountField = new JTextField(10);
                gbc.gridy++;
                gbc.gridwidth = 2;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                add(depositAmountField, gbc);

                // boton depositar
                depositButton = new JButton("Depositar");
                gbc.anchor = GridBagConstraints.PAGE_END;
                gbc.gridy++;
                add(depositButton, gbc);
                depositButton.setVisible(false);

                // Espacio
                gbc.gridx = 0;
                gbc.gridy++;
                gbc.gridwidth = 2;
                gbc.weighty = 0.2;
                gbc.fill = GridBagConstraints.BOTH;
                add(new JPanel(), gbc);

                // Ocultar el campo inicial
                depositAmountLabel.setVisible(false);
                depositAmountField.setVisible(false);
        }

        public JTextField getRunTextField() {
                return runTextField;
        }
         public JTextField getDvTextField() {
                return dvTextField;
        }

        public void load(Client client, Account account) {
                JLabel accountType = new JLabel("Cuenta " + account.getDescription());
                JLabel balance = new JLabel("Saldo: " + Integer.toString(account.checkBalance()));
                accountType.setFont(accountType.getFont().deriveFont(Font.BOLD));
                balance.setFont(balance.getFont().deriveFont(Font.BOLD));

                infoPanel.removeAll();
                infoPanel.add(accountType);
                infoPanel.add(balance);
                infoPanel.setVisible(true);
                depositButton.setVisible(true);
                showDepositAmountField();

                revalidate();
                repaint();
        }

        private void showDepositAmountField() {
                depositAmountLabel.setVisible(true);
                depositAmountField.setVisible(true);
        }

        public void searchClient(ActionListener actionListener) {
                searchButton.addActionListener(actionListener);
        }

        public void depositBtn(ActionListener actionListener) {
                depositButton.addActionListener(actionListener);
        }

        public void displayErrorMessage(String errorMessage) {
                JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }

        public void displayMessage(String message) {
                JOptionPane.showMessageDialog(this, message);
        }

        public JTextField getDepositAmountField() {
                return depositAmountField;
        }
}
