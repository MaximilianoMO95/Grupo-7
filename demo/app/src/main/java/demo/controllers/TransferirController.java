package demo.controllers;

import demo.models.Account;
import demo.models.Client;
import demo.models.SqlClients;
import demo.validations.ValidationUtils;
import demo.views.TransferirView;

public class TransferirController {
        private TransferirView transferirView;
        private SqlClients sqlClients;
        private Client srcClient;
        private Client dstClient;

        public TransferirController(TransferirView transferirView) {
                this.transferirView = transferirView;
                this.sqlClients = new SqlClients();

                this.transferirView.transfer(e -> {
                        String srcAccountNum = transferirView.getSourceAccountField();
                        String dstAccountNum = transferirView.getDestinationAccountField();

                        if (srcAccountNum.isEmpty() ) {
                                transferirView.displayErrorMessage("cuenta de origen vacia");
                                return;
                        } else if (dstAccountNum.isEmpty()) {
                                transferirView.displayErrorMessage("cuenta de destino vacia");
                                return;
                        } else if (!ValidationUtils.validateAccountNumber(srcAccountNum)) {
                                transferirView.displayErrorMessage("cuenta de destino invalida");
                                return;
                         } else if (!ValidationUtils.validateAccountNumber(dstAccountNum)) {
                                transferirView.displayErrorMessage("cuenta de destino invalida");
                                return;
                        }


                        srcClient = sqlClients.searchByAccountNumber(srcAccountNum);
                        dstClient = sqlClients.searchByAccountNumber(dstAccountNum);

                        if (srcClient == null) {
                                transferirView.displayErrorMessage("Cuenta de origen no encontrada");
                                return;
                        } else if (dstClient == null) {
                                transferirView.displayErrorMessage("Cuenta de destino no encontrada");
                                return;
                        } else if (!isCurrentAccount(srcClient)) {
                                transferirView.displayErrorMessage("Cuenta de origen debe ser cuenta corriente");
                                return;
                        } else if (!isCurrentAccount(dstClient)) {
                                transferirView.displayErrorMessage("Cuenta de destino debe ser cuenta corriente");
                                return;
                        }

                        int amount = Integer.parseInt(transferirView.getTransferAmountField());

                        if (srcClient.getAccount().checkBalance() < amount) {
                                transferirView.displayErrorMessage("Fondos insuficientes");
                                return;
                        } else if (amount <= 0) {
                                transferirView.displayErrorMessage("Cantidad invalida");
                                return;
                        }

                        if (sqlClients.transfer(srcClient, dstClient)) {
                                srcClient.getAccount().moneyTransfer(amount, dstClient.getAccount());
                                transferirView.displayMessage("Transferencia realizada con éxito. Nuevo saldo: " + dstClient.getAccount().checkBalance());
                        } else {
                                transferirView.displayErrorMessage("Problemas con la base de datos");
                        }

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
