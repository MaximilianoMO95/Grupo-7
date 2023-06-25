package demo.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NavBarView extends JMenuBar {
        private CustomJButton home = new CustomJButton("Home");

        private CustomJMenuItem c1 = new CustomJMenuItem("Registrar Cliente");
        private CustomJMenuItem c2 = new CustomJMenuItem("Ver Datos Cliente");

        private CustomJMenuItem a1 = new CustomJMenuItem("Depositar");
        private CustomJMenuItem a2 = new CustomJMenuItem("Girar");
        private CustomJMenuItem a3 = new CustomJMenuItem("Transferir");

        public NavBarView() {
                setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

                CustomJMenu client = new CustomJMenu("Clientes");
                CustomJMenu accounts = new CustomJMenu("Cuentas");

                client.add(c1);
                client.add(c2);

                accounts.add(a1);
                accounts.add(a2);
                accounts.add(a3);

                setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

                // bua faltaba esto :)
                add(home);
                add(client);
                add(accounts);
        }

        public void showHome(ActionListener actionListener) {
                home.addActionListener(actionListener);
        }

        public void showClientForm(ActionListener actionListener) {
                c1.addActionListener(actionListener);
        }

        public void showClientData(ActionListener actionListener) {
                c2.addActionListener(actionListener);
        }
}

class CustomJMenu extends JMenu {
        private static final int MENU_WIDTH = 200;
        private static final int MENU_HEIGHT = 30;

        public CustomJMenu(String text) {
                super(text);
                setHorizontalAlignment(SwingConstants.CENTER);
        }
    
        @Override
        public Dimension getPreferredSize() {
                return new Dimension(MENU_WIDTH, MENU_HEIGHT);
        }
}

class CustomJMenuItem extends JMenuItem {
        private static final int ITEM_WIDTH = 200;
        private static final int ITEM_HEIGHT = 30;
    
        public CustomJMenuItem(String text) {
                super(text);
                setHorizontalAlignment(SwingConstants.CENTER);
        }

        @Override
        public Dimension getPreferredSize() {
                return new Dimension(ITEM_WIDTH, ITEM_HEIGHT);
        }
}

class CustomJButton extends JButton {
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 30;

    public CustomJButton(String text) {
        super(text);
        setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        setHorizontalAlignment(SwingConstants.CENTER);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
    }
}

