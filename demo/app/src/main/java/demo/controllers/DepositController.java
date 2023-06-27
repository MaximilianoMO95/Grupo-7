package demo.controllers;

import demo.models.Client;
import demo.models.Database;
import demo.views.DepositView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DepositController {
    private String databaseFile = "src/main/java/demo/data/clients.json";
    private Database<Client> database;
    private DepositView depositView;

    public DepositController(DepositView depositView) {
        this.database = new Database<>(Client.class);
        this.depositView = depositView;

        this.depositView.getSearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchClient();
            }
        });
    }

    private void searchClient() {
        String rut = depositView.getRunTextField().getText();
        List<Client> clients = database.readJsonFromFile(databaseFile);

        for (Client client : clients) {
            if (client.run.equals(rut)) {
                depositView.showDepositAmountField();
                depositView.displayMessage("RUT encontrado");
                return;
            }
        }

        depositView.displayErrorMessage("RUT no encontrado");
    }
}
