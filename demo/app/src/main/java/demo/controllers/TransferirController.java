package demo.controllers;

import demo.models.Account;
import demo.models.Client;
import demo.models.CurrentAccount;
import demo.models.Database;
import demo.views.TransferirView;

import java.util.List;

public class TransferirController {
        private String databaseFile = "src/main/java/demo/data/clients.json";
        private Database<Client> database;
        private TransferirView transferirView;

        public DepositController(TransferirView transferirView) {
                this.database = new Database<>(Client.class);
                this.transferirView = transferirView;

                this.transferirView.transfer(e -> {
                        int accountNumber1 = Integer.parseInt(transferirView.getSourceAccountField().getText());
                        int accountNumber2 = Integer.parseInt(transferirView.getDestinationAccountField().getText());

                        Client client1;
                        Client client2;

                        int idx1 = 0;
                        int idx2 = 0;

                        List<Client> clients = database.readJsonFromFile(databaseFile);
                        boolean found1 = false;
                        boolean found2 = false;

                        if (clients != null) {
                                int idx = 0;

                                for (Client client : clients) {
                                        int accountNumber = client.getAccount().getAccountNumber();

                                        if (accountNumber.equals(accountNumber1)) {
                                                found1 = true;
                                                idx1 = idx;
                                        } else if (accountNumber.equals(accountNumber2)) {
                                                found2 = true;
                                                idx2 = idx;
                                        }

                                        idx++;
                                }
                        }

                        if (!found1) {
                                transferirView.displayErrorMessage("Cuenta de origen no encontrada");
                        } else if (!found2) {
                                transferirView.displayErrorMessage("Cuenta de destino no encontrada");
                        } else if (isCurrentAccount(client1)) {
                                transferirView.displayErrorMessage("Cuenta de origen debe ser cuenta corriente");
                        } else if (isCurrentAccount(client2)) {
                                transferirView.displayErrorMessage("Cuenta de destino debe ser cuenta corriente");
                        }

                        transfer(client1, client2, idx1, idx2);
                });
        }

        private void transfer(Client client1, Client client2, int idx1, int idx2) {
                int amount = Integer.parseInt(transferirView.getTransferAmountField().getText());

                if (client1.getAccount().checkBalance() < amount) {
                        transferirView.displayErrorMessage("Fondos insuficientes");
                        return;
                } else if (amount <= 0) {
                        transferirView.displayErrorMessage("Cantidad invalida");
                        return;
                }

                // fix in the future
                ((CurrentAccount)client1.getAccount()).moneyTransfer(amount, 0);
                client2.getAccount().deposit(amount);

                this.database.updateJsonItem(client1, idx1, databaseFile);
                this.database.updateJsonItem(client2, idx2, databaseFile);

                transferirView.displayMessage("Transferencia realizada con éxito. Nuevo saldo: " + client1.getAccount().checkBalance());
        }

        private boolean isCurrentAccount(Client client, int accountNumber) {
                Account account = client.getAccount();
                if (account instanceof CurrentAccount) {
                        return true;
                }

                return false;
        }
}
