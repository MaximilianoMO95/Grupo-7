package demo.views;

import javax.swing.*;
import java.awt.*;

public class HomeView extends JPanel {
        public HomeView() {
                setLayout(new BorderLayout());

                // Create a JLabel for the app logo
                JLabel logoLabel = new JLabel();
                ImageIcon logoIcon = new ImageIcon("src/main/java/demo/assets/favicon.png");
                logoLabel.setIcon(logoIcon);

                logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
                logoLabel.setVerticalAlignment(SwingConstants.CENTER);

                // Add the logo label to the center of the panel
                add(logoLabel, BorderLayout.CENTER);
        }
}
