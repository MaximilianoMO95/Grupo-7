package demo;

import com.formdev.flatlaf.FlatIntelliJLaf;
import demo.views.MainFrame;

import javax.swing.*;

// Source (https://github.com/ashiishme/java-swing-mvc)
public class App {
        public static void main(String[] args) {
                FlatIntelliJLaf.setup();
                SwingUtilities.invokeLater(MainFrame::new);
        }
}
