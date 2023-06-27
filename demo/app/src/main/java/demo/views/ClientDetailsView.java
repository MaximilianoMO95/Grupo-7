package demo.views;

import demo.models.Account;
import demo.models.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientDetailsView extends JPanel {
        private SearchPanel searchPanel;
        private ClientDataPanel clientDataPanel;

        public ClientDetailsView() {
                setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

                searchPanel = new SearchPanel();
                clientDataPanel = new ClientDataPanel();

                add(searchPanel);
                add(clientDataPanel);
        }

        public void loadClientData(Client client) {
                clientDataPanel.loadClientData(client);
        }

        public void searchClient(ActionListener actionListener) {
                this.searchPanel.searchClient(actionListener);
        }

        public String getSearchFieldValue() {
                return searchPanel.getSearchFieldValue();
        }
}

class SearchPanel extends JPanel {
        private JTextField searchField;
        private JButton searchButton;

        public SearchPanel() {
                searchField = new JTextField(20);
                searchButton = new JButton("Buscar");

                JLabel searchLabel = new JLabel("Rut");

                setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));
                add(searchLabel);
                add(searchField);
                add(searchButton);
        }

        public String getSearchFieldValue() {
                return searchField.getText();
        }

        public void searchClient(ActionListener actionListener) {
                searchButton.addActionListener(actionListener);
        }
}

class ClientDataPanel extends JPanel {
        private JPanel personalDataPanel;
        private JPanel accountDataPanel;

        private JLabel notFoundLabel;

        public ClientDataPanel() {
                setLayout(new GridBagLayout());

                notFoundLabel = new JLabel("Cliente No Encontrado");
                notFoundLabel.setFont(notFoundLabel.getFont().deriveFont(Font.BOLD, 20f));
                notFoundLabel.setHorizontalAlignment(SwingConstants.CENTER);

                personalDataPanel = new JPanel();
                personalDataPanel.setBorder(BorderFactory.createTitledBorder("Datos Cliente"));
                personalDataPanel.setPreferredSize(new Dimension(600, 200));
                personalDataPanel.setLayout(new GridLayout(3, 2));

                accountDataPanel = new JPanel();
                accountDataPanel.setBorder(BorderFactory.createTitledBorder("Datos Cuenta"));
                accountDataPanel.setPreferredSize(new Dimension(200, 200));
                accountDataPanel.setLayout(new GridLayout(3, 0));

                
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.insets = new Insets(3, 3, 3, 3);

                add(notFoundLabel);
                notFoundLabel.setVisible(false);

                add(personalDataPanel, gbc);
                personalDataPanel.setVisible(false);

                gbc.gridx++;
                add(accountDataPanel, gbc);
                accountDataPanel.setVisible(false);
        }

        public void loadClientData(Client client) {
                if (client != null) {
                        notFoundLabel.setVisible(false);
                        personalDataPanel.setVisible(true);
                        accountDataPanel.setVisible(true);

                        updateRow("Nombre: ", client.name, personalDataPanel);
                        updateRow("Apellido: ", client.surname, personalDataPanel);
                        updateRow("Domicilio: ", client.address, personalDataPanel);
                        updateRow("Comuna: ", client.comuna, personalDataPanel);
                        updateRow("Telefono: ", client.address, personalDataPanel);

                        Account account = client.getAccount();
                        updateRow("Numero: ", Integer.toString(account.getAccountNumber()), accountDataPanel);
                        updateRow("Tipo: ", account.getDescription(), accountDataPanel);
                        updateRow("Saldo: ", Integer.toString(account.checkBalance()), accountDataPanel);
                } else {
                        notFoundLabel.setVisible(true);
                        personalDataPanel.setVisible(false);
                        accountDataPanel.setVisible(false);
                }

                revalidate();
                repaint();
        }

        public void addRow(String key, String value, JPanel panel) {
                JLabel label = new JLabel(key + " " + value);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                panel.add(label);
        }

        public void updateRow(String key, String value, JPanel panel) {
                
                for (Component component : panel.getComponents()) {
                        if (component instanceof JLabel) {
                                JLabel label = (JLabel) component;
                                if (label.getText().startsWith(key)) {
                                        label.setText(key + " " + value);
                                        return;
                                }
                        }
                }

                addRow(key, value, panel);
        }
}
