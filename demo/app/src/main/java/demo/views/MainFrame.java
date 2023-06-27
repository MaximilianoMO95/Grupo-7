package demo.views;

import demo.controllers.*;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
        private static final int WINDOW_WIDTH = 1200;
        private static final int WINDOW_HEIGHT = 700;

        private CardLayout cardLayout;

        public MainFrame() {
                super("West Bank");

                // Initialization
                cardLayout = new CardLayout();

                NavBarView navBar = new NavBarView();
                RegisterClientFormView form = new RegisterClientFormView();
                ClientDetailsView clientDetails = new ClientDetailsView();
                HomeView home = new HomeView();
                DepositView depositView = new DepositView();

                new ClientController(form, clientDetails);
                new HomeController(home);

                // Setup
                setLayout(cardLayout);
                setJMenuBar(navBar);

                // Panels
                add(home, "home");
                add(form, "form");
                add(clientDetails, "clientDetails");
                add(depositView, "deposit");
                // Actions
                navBar.showClientData(e -> cardLayout.show(MainFrame.this.getContentPane(), "clientDetails"));
                navBar.showClientForm(e -> cardLayout.show(MainFrame.this.getContentPane(), "form"));
                navBar.showHome(e -> cardLayout.show(MainFrame.this.getContentPane(), "home"));
                navBar.showDeposit(e -> cardLayout.show(MainFrame.this.getContentPane(), "deposit"));

                // Window
                ImageIcon imageIcon = new ImageIcon("src/main/java/demo/assets/favicon.png");
                setIconImage(imageIcon.getImage());

                setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setVisible(true);
        }
}
