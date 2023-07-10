package demo.controllers;

import demo.models.*;
import demo.validations.ValidationUtils;
import demo.views.GirarView;

public class GirarController {
        private SqlClients sqlClient;
        private GirarView girarView;
        private Client client;

        public GirarController(GirarView girarView) {
                this.girarView = girarView;
                this.sqlClient = new SqlClients();

                this.girarView.searchClient(e -> {
                        String run = girarView.getRunField();
                        String dv = girarView.getDvField();

                        if (!ValidationUtils.validateDv(dv)) {
                                girarView.displayErrorMessage("Digito verificador invalido");
                                return;
                        } else if (!ValidationUtils.validateRun(run)) {
                                girarView.displayErrorMessage("RUT invalido");
                                return;
                        }

                        client = sqlClient.searchByRun(run, dv);

                        if (client == null) {
                                girarView.displayErrorMessage("RUT no encontrado");
                                return;
                        }

                        girarView.load(client);

                });

                girarView.withdrawBtn(ev -> { 
                        withdrawFromAccount(client);
                        girarView.load(client);
                });
        }

        private void withdrawFromAccount(Client client) {
                int amount = Integer.parseInt(girarView.getWithdrawAmountField());
                Account account = client.getAccount();

                if (account.checkBalance() < amount) {
                        girarView.displayErrorMessage("Fondos insuficientes");
                        return;
                } else if (amount < 0) {
                        girarView.displayErrorMessage("Nice Try");
                        return;
                }

                if (sqlClient.withdraw(client, amount)) {
                        account.withdraw(amount);
                        girarView.displayMessage("Retiro realizado con éxito. Nuevo saldo: " + account.checkBalance());
                } else {
                        girarView.displayErrorMessage("Problemas de connecion intente mas tarde");
                }
        }
}
