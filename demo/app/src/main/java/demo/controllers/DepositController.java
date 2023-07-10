package demo.controllers;


import demo.models.Client;
import demo.models.SqlClients;
import demo.views.DepositView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepositController {
    private SqlClients sqlClients;
    private DepositView depositView;

    public DepositController(DepositView depositView) {
        this.sqlClients = new SqlClients();
        this.depositView = depositView;

        this.depositView.searchClient(new SearchClientListener());
        this.depositView.depositBtn(new DepositBtnListener());
    }

    private class SearchClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String run = depositView.getRunTextField().getText();
            String dv = depositView.getDvTextField().getText();
            run = run.replace(".", "").replace("-", "");

            Client client = sqlClients.searchByRun(run, dv);

            if (client != null) {
                depositView.load(client, client.getAccount());
            } else {
                depositView.displayErrorMessage("RUT no encontrado");
            }
        }
    }

    private class DepositBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String run = depositView.getRunTextField().getText();
            String dv = depositView.getDvTextField().getText();
            run = run.replace(".", "").replace("-", "");

            Client client = sqlClients.searchByRun(run, dv);

            if (client != null) {
                int amount = Integer.parseInt(depositView.getDepositAmountField().getText());
                boolean success = sqlClients.deposit(client, amount);

                if (amount <= 0) {
                        depositView.displayErrorMessage("Cantidad invalida");
                        return;
                }

                if (success) {
                    client.getAccount().deposit(amount);
                    depositView.load(client, client.getAccount());
                    depositView.displayMessage("Depósito realizado con éxito. Nuevo saldo: " + client.getAccount().checkBalance());
                } else {
                    depositView.displayErrorMessage("Error al realizar el depósito");
                }
            } else {
                depositView.displayErrorMessage("RUT no encontrado");
            }
        }
    }
}
