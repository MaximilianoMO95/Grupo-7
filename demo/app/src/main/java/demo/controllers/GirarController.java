package demo.controllers;

import demo.models.Account;
import demo.models.Client;
import demo.models.Database;
import demo.views.GirarView;

import java.util.List;

public class GirarController {
        private String databaseFile = "src/main/java/demo/data/clients.json";
        private Database<Client> database;
        private GirarView girarView;

        public GirarController(GirarView girarView) {
                this.database = new Database<>(Client.class);
                this.girarView = girarView;

                this.girarView.searchClient(e -> {
                        String rut = girarView.getRunTextField().getText();
                        List<Client> clients = database.readJsonFromFile(databaseFile);
                        boolean found = false;

                        if (clients != null) {
                                for (Client client : clients) {
                                        if (client.run.equals(rut)) {
                                                found = true;
                                                girarView.load(client);
                                                break;
                                        }
                                }
                        }

                        if (!found) {
                                girarView.displayErrorMessage("RUT no encontrado");
                        }
                });

                girarView.withdrawBtn(ev -> { 
                        String rut = girarView.getRunTextField().getText();
                        List<Client> clients = database.readJsonFromFile(databaseFile);
                        boolean found = false;

                        if (clients != null) {
                                int idx = 0;
                                for (Client client : clients) {
                                        if (client.run.equals(rut)) {
                                                found = true;
                                                withdrawFromAccount(client, idx);
                                                girarView.load(client);
                                                break;
                                        }

                                        idx++;
                                }
                        }
                        
                        if (!found) {
                                girarView.displayErrorMessage("RUT no encontrado");
                        }
                });
        }

        private void withdrawFromAccount(Client client, int idx) {
                int amount = Integer.parseInt(girarView.getWithdrawAmountField().getText());
                Account account = client.getAccount();

                if (account.checkBalance() >= amount) {
                        account.withdraw(amount);
                        this.database.updateJsonItem(client, idx, databaseFile);
                        girarView.displayMessage("Retiro realizado con éxito. Nuevo saldo: " + account.checkBalance());
                } else {
                        girarView.displayErrorMessage("Fondos insuficientes");
                }

        }
}
