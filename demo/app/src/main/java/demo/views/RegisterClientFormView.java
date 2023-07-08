package demo.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RegisterClientFormView extends JPanel {
        private GridBagConstraints gbc = new GridBagConstraints();
        private Map<String, JComponent> formData = new HashMap<>(7);
        private JButton submitButton;

        public RegisterClientFormView() {
                super(new GridBagLayout());

                // Titulo
                JLabel titleLabel = new JLabel("Registrar Cliente");
                Font titleFont = titleLabel.getFont();
                Font newFont = titleFont.deriveFont(titleFont.getStyle() | Font.BOLD, 24f);

                gbc.insets = new Insets(0, 0, 40, 0);
                titleLabel.setFont(newFont);
                add(titleLabel, gbc);

                // Margenes
                gbc.insets = new Insets(6, 6, 6, 6);
                gbc.gridy += 2;
                gbc.anchor = GridBagConstraints.WEST;

                // Campo rut
                JPanel rutPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

                gbc.gridy++;
                gbc.gridx = 0;
                add(new JLabel("Rut"), gbc);

                gbc.gridx++;
                JTextField runField = new JTextField(8);
                runField.setPreferredSize(new Dimension(200, runField.getPreferredSize().height));

                formData.put("Rut", runField);
                rutPanel.add(runField);

                rutPanel.add(new JLabel("-"));
                JTextField dvField = new JTextField(1);
                dvField.setPreferredSize(new Dimension(50, dvField.getPreferredSize().height));

                formData.put("dv", dvField);
                rutPanel.add(dvField);

                add(rutPanel, gbc);

                // Cuenta
                String[] accountTypes = { "Cuenta Corriente", "Cuenta Ahorro" };
                JComboBox<String> accountTypeComboBox = new JComboBox<>(accountTypes);

                gbc.anchor = GridBagConstraints.LAST_LINE_END;
                formData.put("Cuenta", accountTypeComboBox);
                add(accountTypeComboBox, gbc);
                gbc.gridy += 2;

                // Entrada
                String[] labelsInputText = {
                       "Nombre:40",  "Apellido Paterno:40", "Apellido Materno:40", "Telefono:40",
                        "Domicilio:40", "Comuna:40", "Numero Cuenta:40"
                };

                gbc.anchor = GridBagConstraints.WEST;
                Arrays.stream(labelsInputText).forEach(label -> {
                        String[] chunk = label.split(":", 2);
                        addLabelInputText(chunk[0], Integer.parseInt(chunk[1]));
                });

                // Registrar boton               
                gbc.gridy += 2;
                gbc.anchor = GridBagConstraints.EAST;
                submitButton = new JButton("Registrar");
                add(submitButton, gbc);
        }

        private void addLabelInputText(String label, int cols) {
                gbc.gridx = 0;
                gbc.gridy++;
                add(new JLabel(label), gbc);

                gbc.gridx = 1;
                JTextField field = new JTextField(cols);
                field.setPreferredSize(new Dimension(200, field.getPreferredSize().height));
                
                formData.put(label, field);
                add(field, gbc);
        }

        public String getFieldValue(String label) {
                JComponent component = formData.get(label);

                if (component instanceof JTextField) {
                        return ((JTextField) component).getText();
                } else if (component instanceof JComboBox) {
                        return ((JComboBox<?>) component).getSelectedItem().toString();
                }

                return null;
        }

        public void submitData(ActionListener actionListener) {
                submitButton.addActionListener(actionListener); 
        }

        public void reset() {
                for (JComponent component : formData.values()) {
                        if (component instanceof JTextField) {
                                ((JTextField) component).setText("");
                        } else if (component instanceof JComboBox) {
                                ((JComboBox<?>) component).setSelectedIndex(0);
                        }
                }
        }
}
