package demo.controllers;

import demo.views.ClientDetailsView;
import demo.views.RegisterClientFormView;
import demo.models.*;
import demo.validations.ValidationUtils;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
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
                                return; 
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
                        } else if (searchByRun(run) != null) {
                                errorDialog("Cliente ya existe", this.form);
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
                        this.form.reset();
                        
                        JOptionPane.showMessageDialog(this.form, "Cliente Registrado", "Success", JOptionPane.INFORMATION_MESSAGE);
                });

              
                this.clientDetails.searchClient(e -> {
                        String targetRun = clientDetails.getSearchFieldValue();
                        Client wantedClient = searchByRun(targetRun);

                        this.clientDetails.loadClientData(wantedClient);
                });
        }

        private void errorDialog(String message, Component component) {
                JOptionPane.showMessageDialog(component, message, "Error",  JOptionPane.ERROR_MESSAGE);
        }

        @Nullable
        private Client searchByRun(String run) {
                List<Client> clients = this.database.readJsonFromFile(databaseFile);
                Client wantedClient = null;

                if (clients != null) {
                        for (Client client : clients) {
                                if (client.run.equals(run)) {
                                        wantedClient = client;
                                        break;
                                }
                        }
                }

                return wantedClient;
        }
}
