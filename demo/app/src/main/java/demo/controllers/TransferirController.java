package demo.controllers;

/*
import demo.models.Account;
import demo.models.Client;
import demo.models.Database;
import demo.views.TransferirView;

import java.util.List;

public class TransferirController {
        private String databaseFile = "src/main/java/demo/data/clients.json";
        private Database<Client> database;
        private TransferirView transferirView;

        public TransferirController(TransferirView transferirView) {
                this.database = new Database<>(Client.class);
                this.transferirView = transferirView;

                this.transferirView.transfer(e -> {
                        if (transferirView.getSourceAccountField().getText().isEmpty() ) {
                                transferirView.displayErrorMessage("cuenta de origen vacia");
                                return;
                        } else if (transferirView.getDestinationAccountField().getText().isEmpty()) {
                                transferirView.displayErrorMessage("cuenta de destino vacia");
                                return;
                        }

                        int accountNumber1 = Integer.parseInt(transferirView.getSourceAccountField().getText());
                        int accountNumber2 = Integer.parseInt(transferirView.getDestinationAccountField().getText());


                        Client client1 = null;
                        Client client2 = null;

                        int idx1 = 0;
                        int idx2 = 0;

                        List<Client> clients = database.readJsonFromFile(databaseFile);
                        boolean found1 = false;
                        boolean found2 = false;

                        if (clients != null) {
                                int idx = 0;
                                int accountNumber;

                                for (Client client : clients) {
                                        accountNumber = client.getAccount().getAccountNumber();

                                        if (accountNumber == accountNumber1) {
                                                found1 = true;
                                                client1 = client;
                                                idx1 = idx;
                                        } else if (accountNumber == accountNumber2) {
                                                found2 = true;
                                                client2 = client;
                                                idx2 = idx;
                                        }

                                        idx++;
                                }
                        }

                        if (!found1) {
                                transferirView.displayErrorMessage("Cuenta de origen no encontrada");
                                return;
                        } else if (!found2) {
                                transferirView.displayErrorMessage("Cuenta de destino no encontrada");
                                return;
                        } else if (!isCurrentAccount(client1)) {
                                transferirView.displayErrorMessage("Cuenta de origen debe ser cuenta corriente");
                                return;
                        } else if (!isCurrentAccount(client2)) {
                                transferirView.displayErrorMessage("Cuenta de destino debe ser cuenta corriente");
                                return;
                        }

                        int amount = Integer.parseInt(transferirView.getTransferAmountField().getText());

                        if (client1.getAccount().checkBalance() < amount) {
                                transferirView.displayErrorMessage("Fondos insuficientes");
                                return;
                        } else if (amount <= 0) {
                                transferirView.displayErrorMessage("Cantidad invalida");
                                return;
                        }

                        client1.getAccount().moneyTransfer(amount, accountNumber2);
                        client2.getAccount().deposit(amount);

                        this.database.updateJsonItem(client1, idx1, databaseFile);
                        this.database.updateJsonItem(client2, idx2, databaseFile);

                        transferirView.displayMessage("Transferencia realizada con éxito. Nuevo saldo: " + client1.getAccount().checkBalance());
                });
        }

        private boolean isCurrentAccount(Client client) {
                Account account = client.getAccount();
                if (account.getDescription().equals("Corriente")) {
                        return true;
                }

                return false;
        }
}
*/
