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

                cardLayout = new CardLayout();

                NavBarView navBar = new NavBarView();
                RegisterClientFormView form = new RegisterClientFormView();
                ClientDetailsView clientDetails = new ClientDetailsView();
                HomeView home = new HomeView();
                DepositView depositView = new DepositView();
                GirarView girarView = new GirarView();
                TransferirView transferirView = new TransferirView();

                new ClientController(form, clientDetails);
                new HomeController(home);
                new DepositController(depositView);
                new GirarController(girarView);
                new TransferirController(transferirView);

                setLayout(cardLayout);
                setJMenuBar(navBar);
                
                add(home, "home");
                add(form, "form");
                add(clientDetails, "clientDetails");
                add(depositView, "deposit");
                add(girarView, "girar");
                add(transferirView, "transferir");
                
                navBar.showClientData(e -> cardLayout.show(MainFrame.this.getContentPane(), "clientDetails"));
                navBar.showClientForm(e -> cardLayout.show(MainFrame.this.getContentPane(), "form"));
                navBar.showHome(e -> cardLayout.show(MainFrame.this.getContentPane(), "home"));
                navBar.showDeposit(e -> cardLayout.show(MainFrame.this.getContentPane(), "deposit"));
                navBar.showGirar(e -> cardLayout.show(MainFrame.this.getContentPane(), "girar"));
                navBar.showTransferir(e -> cardLayout.show(MainFrame.this.getContentPane(), "transferir"));

                
                ImageIcon imageIcon = new ImageIcon("src/main/java/demo/assets/favicon.png");
                setIconImage(imageIcon.getImage());

                setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setVisible(true);
        }
}
