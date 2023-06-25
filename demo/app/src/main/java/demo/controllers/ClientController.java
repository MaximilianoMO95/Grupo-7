package demo.controllers;

import demo.views.ClientDetailsView;
import demo.views.RegisterClientFormView;
import demo.models.Account;
import demo.models.Client;
import demo.models.Database;

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
                        String name = this.form.getFieldValue("Nombre");
                        
                        if (name.isEmpty()) {
                                JOptionPane.showMessageDialog(this.form, "Campo nombre esta vacio", "Error",  JOptionPane.ERROR_MESSAGE);
                        }

                        // test database
                        this.database.writeJsonToFile(new Client("123", name, "surname", "addr", "comuna", "tel", new Account(1123123)), databaseFile);
                });

                this.clientDetails.searchClient(e -> {
                        List<Client> clients = this.database.readJsonFromFile(databaseFile);
                        this.clientDetails.loadClientData(clients.get(0));
                });
        }
        
}
