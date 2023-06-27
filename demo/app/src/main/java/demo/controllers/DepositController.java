package demo.controllers;

import demo.models.Account;
import demo.models.Client;
import demo.models.CurrentAccount;
import demo.models.Database;
import demo.views.DepositView;

import java.util.List;

public class DepositController {
    private String databaseFile = "src/main/java/demo/data/clients.json";
    private Database<Client> database;
    private DepositView depositView;

    public DepositController(DepositView depositView) {
        this.database = new Database<>(Client.class);
        this.depositView = depositView;

        this.depositView.searchClient(e -> {
                String rut = depositView.getRunTextField().getText();
                List<Client> clients = database.readJsonFromFile(databaseFile);
                boolean found = false;

                if (clients != null) {
                        for (Client client : clients) {
                            if (client.run.equals(rut)) {
                                found = true;
                                depositView.load(client);
                                break;
                            }
                        }
                }

                if (!found) {
                        depositView.displayErrorMessage("RUT no encontrado");
                }
        });
           
        this.depositView.searchClient(e -> {
        String rut = depositView.getRunTextField().getText();
        List<Client> clients = database.readJsonFromFile(databaseFile);
        boolean found = false;

        if (clients != null) {
            for (Client client : clients) {
                if (client.run.equals(rut)) {
                    found = true;
                    depositView.load(client);
                    depositView.getDepositButton().addActionListener(ev -> depositToAccount(client));
                    break;
                }
            }
        }

        if (!found) {
            depositView.displayErrorMessage("RUT no encontrado");
        }
    });
        
        
    }
    private void depositToAccount(Client client) {
    double amount = Double.parseDouble(depositView.getDepositAmountField().getText());
    client.getAccount().deposit(amount);
    depositView.displayMessage("Depósito realizado con éxito. Nuevo saldo: " + client.getAccount().checkBalance());
    // this.database.updateJsonItem(client, databaseFile);
}
}
