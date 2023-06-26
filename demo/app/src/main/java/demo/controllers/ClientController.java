package demo.controllers;

import demo.views.ClientDetailsView;
import demo.views.RegisterClientFormView;
import demo.models.*;
import demo.validations.ValidationUtils;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class ClientController {
        private String databaseFile = "src/main/java/demo/data/clients.json";
        private Database<Client> database;
        private RegisterClientFormView form;
        private ClientDetailsView clientDetails;

        public ClientController(RegisterClientFormView registerClientFormView, ClientDetailsView clientDetailsView) {
                this.database = new Database<Client>(Client.class);
                this.form = registerClientFormView;
                this.clientDetails = clientDetailsView;

                // Register new client
                this.form.submitData(e -> {
                        String[] fieldNames = {"Nombre", "Apellido", "Telefono", "Domicilio", "Comuna", "Numero Cuenta"};
                        List<String> emptyFields = new ArrayList<>();

                        for (String fieldName : fieldNames) {
                                String fieldValue = this.form.getFieldValue(fieldName);
                                if (fieldValue == null || fieldValue.trim().isEmpty()) {
                                        emptyFields.add(fieldName);
                                }
                        }

                        if (!emptyFields.isEmpty()) {
                                String errorMessage = "Los siguientes campos están vacíos: " + String.join(", ", emptyFields);
                                errorDialog(errorMessage, this.form);
                                return; // Exit the method if there are empty fields
                        }

                        String run = this.form.getFieldValue("Rut");
                        String name = this.form.getFieldValue("Nombre");
                        String surname = this.form.getFieldValue("Apellido");
                        String addr = this.form.getFieldValue("Domicilio");
                        String comuna = this.form.getFieldValue("Comuna");
                        String tel = this.form.getFieldValue("Telefono");
                        String accountNum = this.form.getFieldValue("Numero Cuenta");
                        Account account;

                        if (!ValidationUtils.validateRun(run)) {
                                errorDialog("Rut es invalido", this.form);
                                return;
                        } else if (!ValidationUtils.validateTel(tel)) {
                                errorDialog("Telefono es invalido", this.form);
                                return;
                        } else if (!ValidationUtils.validateAccountNumber(accountNum)) {
                                errorDialog("Numero de cuenta es invalido", this.form);
                                return;
                        }

                        if (this.form.getFieldValue("Cuenta") == "Cuenta Ahorro") {
                                account = new SavingAccount(Integer.parseInt(accountNum));
                        } else {
                                account = new CurrentAccount(Integer.parseInt(accountNum));
                        }

                        Client client = new Client(run, name, surname, addr, comuna, tel, account);
                        this.database.writeJsonToFile(client, databaseFile);
                });

                // Search a client by run
                this.clientDetails.searchClient(e -> {
                        List<Client> clients = this.database.readJsonFromFile(databaseFile);
                        Client wantedClient = null;

                        String targetRun = clientDetails.getSearchFieldValue();
                        for (Client client : clients) {
                                if (client.run.equals(targetRun)) {
                                        wantedClient = client;
                                        break;
                                }
                        }

                        this.clientDetails.loadClientData(wantedClient);
                });
        }

        public void errorDialog(String message, Component component) {
                JOptionPane.showMessageDialog(component, message, "Error",  JOptionPane.ERROR_MESSAGE);
        }
        
}
