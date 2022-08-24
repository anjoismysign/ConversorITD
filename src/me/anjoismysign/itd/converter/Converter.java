package me.anjoismysign.itd.converter;

import me.anjoismysign.itd.libraries.PanelLib;

import javax.swing.*;
import java.awt.*;

public class Converter {

    public static final String DECIMAL = "Decimal";
    public static final String BINARY = "Binario";
    public static final String OCTAL = "Octal";
    public static final String HEXADECIMAL = "Hexadecimal";

    public static void panel() {
        JTextField inputField = new JTextField(20);
        JComboBox<String> current = new JComboBox<>(new String[]{DECIMAL, BINARY, OCTAL, HEXADECIMAL});
        JComboBox<String> goal = new JComboBox<>(new String[]{DECIMAL, BINARY, OCTAL, HEXADECIMAL});

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Ingrese un número"));
        panel.add(inputField);
        panel.add(new JLabel("Convertir de"));
        panel.add(current);
        panel.add(new JLabel("Hacia"));
        panel.add(goal);

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Conversor de Posicionales", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String currentSelection = (String) current.getSelectedItem();
            String goalSelection = (String) goal.getSelectedItem();
            if (currentSelection.equals(goalSelection))
                panel();
            String input = inputField.getText();
            String output = "";
            switch (currentSelection) {
                case "Decimal" -> {
                    int value = 0;
                    try {
                        value = Integer.parseInt(input);
                    } catch (NumberFormatException e) {
                        PanelLib.showMessage("El número ingresado no es válido");
                        panel();
                        return;
                    }
                    switch (goalSelection) {
                        case "Binario" -> {
                            output = new Binary(new Decimal(value)).print();
                        }
                        case "Octal" -> {
                            output = new Octal(new Decimal(value)).print();
                        }
                        case "Hexadecimal" -> {
                            output = new Hexadecimal(new Decimal(value)).print();
                        }
                    }
                }
                case "Binario" -> {
                    int value = 0;
                    try {
                        value = Integer.parseInt(input);
                    } catch (NumberFormatException e) {
                        PanelLib.showMessage("El número ingresado no es válido");
                        panel();
                        return;
                    }
                    switch (goalSelection) {
                        case "Decimal" -> {
                            output = new Decimal(new Binary(String.valueOf(value)).decimalValue()).print();
                        }
                        case "Octal" -> {
                            output = new Octal(new Decimal(new Binary(String.valueOf(value)).decimalValue())).print();
                        }
                        case "Hexadecimal" -> {
                            output = new Hexadecimal(new Decimal(new Binary(String.valueOf(value)).decimalValue())).print();
                        }
                    }
                }
                case "Octal" -> {
                    int value = 0;
                    try {
                        value = Integer.parseInt(input);
                    } catch (NumberFormatException e) {
                        PanelLib.showMessage("El número ingresado no es válido");
                        panel();
                        return;
                    }
                    switch (goalSelection) {
                        case "Decimal" -> {
                            output = new Decimal(new Octal(String.valueOf(value)).decimalValue()).print();
                        }
                        case "Binario" -> {
                            output = new Binary(new Decimal(new Octal(String.valueOf(value)).decimalValue())).print();
                        }
                        case "Hexadecimal" -> {
                            output = new Hexadecimal(new Decimal(new Octal(String.valueOf(value)).decimalValue())).print();
                        }
                    }
                }
                case "Hexadecimal" -> {
                    switch (goalSelection) {
                        case "Decimal" -> {
                            output = new Decimal(new Hexadecimal(input).decimalValue()).print();
                        }
                        case "Binario" -> {
                            output = new Binary(new Decimal(new Hexadecimal(input).decimalValue())).print();
                        }
                        case "Octal" -> {
                            output = new Octal(new Decimal(new Hexadecimal(input).decimalValue())).print();
                        }
                    }
                }
            }
            PanelLib.showMessage(output);
            panel();
        }
    }
}
