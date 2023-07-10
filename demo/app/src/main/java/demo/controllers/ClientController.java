package demo.controllers;

import demo.views.ClientDetailsView;
import demo.views.RegisterClientFormView;
import demo.validations.ValidationUtils;
import demo.models.*;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class ClientController {
        private SqlClients sqlClient;
        private RegisterClientFormView form;
        private ClientDetailsView clientDetails;

        public ClientController(RegisterClientFormView registerClientFormView, ClientDetailsView clientDetailsView) {
                this.form = registerClientFormView;
                this.clientDetails = clientDetailsView;
                this.sqlClient = new SqlClients();
                
                this.form.submitData(e -> {
                        String[] fieldNames = {"Rut", "dv", "Nombre", "Apellido Paterno", "Apellido Materno", "Telefono", "Domicilio", "Comuna", "Numero Cuenta"};
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
                        String dv = this.form.getFieldValue("dv");
                        String name = this.form.getFieldValue("Nombre");
                        String ap_paterno = this.form.getFieldValue("Apellido Paterno");
                        String ap_materno = this.form.getFieldValue("Apellido Materno");
                        String addr = this.form.getFieldValue("Domicilio");
                        String comuna = this.form.getFieldValue("Comuna");
                        String tel = this.form.getFieldValue("Telefono");
                        String accountNum = this.form.getFieldValue("Numero Cuenta");
                        Account account = null;

                        if (!ValidationUtils.validateRun(run)) {
                                errorDialog("Rut es invalido", this.form);
                                return;
                        } else if (!ValidationUtils.validateDv(dv)) {
                                errorDialog("Digito Verificador es invalido", this.form);
                                return;
                        } else if (this.sqlClient.searchByRun(run) != null) {
                                errorDialog("Cliente ya existe", this.form);
                                return;
                        } else if (!ValidationUtils.validateTel(tel)) {
                                errorDialog("Telefono es invalido", this.form);
                                return;
                        } else if (!ValidationUtils.validateAccountNumber(accountNum)) {
                                errorDialog("Numero de cuenta es invalido", this.form);
                                return;
                        } else if (this.sqlClient.searchByAccountNumber(accountNum) != null){
                                errorDialog("Numero de cuenta ya esta en uso", this.form);
                                return;
                        }

                        if (this.form.getFieldValue("Cuenta").equals("Cuenta Ahorro")) {
                                account = new SavingAccount(Integer.parseInt(accountNum));
                        } else {
                                account = new CurrentAccount(Integer.parseInt(accountNum));
                        }

                        Client client = new Client(run, dv, name, ap_paterno, ap_materno, addr, comuna, tel, account);

                        if (this.sqlClient.register(client)) {
                                JOptionPane.showMessageDialog(this.form, "Cliente Registrado", "Success", JOptionPane.INFORMATION_MESSAGE);
                                this.form.reset();
                        } else {
                                errorDialog("Problema con la base de datos", this.form);
                        }
                });

              
                this.clientDetails.searchClient(e -> {
                        String targetRun = clientDetails.getRunFieldValue();
                        String targetDv = clientDetails.getDvFieldValue();
                        Client wantedClient = this.sqlClient.searchByRun(targetRun, targetDv);

                        this.clientDetails.loadClientData(wantedClient);
                });
        }

        private void errorDialog(String message, Component component) {
                JOptionPane.showMessageDialog(component, message, "Error",  JOptionPane.ERROR_MESSAGE);
        }
}
