package me.anjoismysign.itd.converter;

import java.util.List;

public class Hexadecimal implements Positional, Convertable {
    private final String value;

    public Hexadecimal(String value) {
        this.value = value;
    }

    public Hexadecimal(Decimal decimal) {
        Binary binary = new Binary(decimal);
        String binaryValue;
        switch (binary.binary().length() % 4) {
            case 1 -> binaryValue = "000" + binary.binary();
            case 2 -> binaryValue = "00" + binary.binary();
            case 3 -> binaryValue = "0" + binary.binary();
            default -> binaryValue = binary.binary();
        }
        StringBuilder hexadecimal = new StringBuilder();
        List<String> split = Stringer.splitString(binaryValue, 4);
        for (String s : split) {
            hexadecimal.append(binaryToHexadecimal(s));
        }
        this.value = hexadecimal.toString();
    }

    @Override
    public Decimal toDecimal() {
        return new Decimal(parse());
    }

    @Override
    public String print() {
        return value + "(hexadecimal)";
    }

    @Override
    public int decimalValue() {
        return parse();
    }

    /**
     * @param hex in case being out of range, it will return 000
     * @return binary value of hex
     */
    private String hexadecimalToBinary(char hex) {
        switch (hex) {
            case '1' -> {
                return "0001";
            }
            case '2' -> {
                return "0010";
            }
            case '3' -> {
                return "0011";
            }
            case '4' -> {
                return "0100";
            }
            case '5' -> {
                return "0101";
            }
            case '6' -> {
                return "0110";
            }
            case '7' -> {
                return "0111";
            }
            case '8' -> {
                return "1000";
            }
            case '9' -> {
                return "1001";
            }
            case 'A' -> {
                return "1010";
            }
            case 'B' -> {
                return "1011";
            }
            case 'C' -> {
                return "1100";
            }
            case 'D' -> {
                return "1101";
            }
            case 'E' -> {
                return "1110";
            }
            case 'F' -> {
                return "1111";
            }
            default -> {
                return "0000";
            }
        }
    }

    /**
     * @return Z in case of error
     */
    private char binaryToHexadecimal(String binary) {
        switch (binary) {
            case "0000" -> {
                return '0';
            }
            case "0001" -> {
                return '1';
            }
            case "0010" -> {
                return '2';
            }
            case "0011" -> {
                return '3';
            }
            case "0100" -> {
                return '4';
            }
            case "0101" -> {
                return '5';
            }
            case "0110" -> {
                return '6';
            }
            case "0111" -> {
                return '7';
            }
            case "1000" -> {
                return '8';
            }
            case "1001" -> {
                return '9';
            }
            case "1010" -> {
                return 'A';
            }
            case "1011" -> {
                return 'B';
            }
            case "1100" -> {
                return 'C';
            }
            case "1101" -> {
                return 'D';
            }
            case "1110" -> {
                return 'E';
            }
            case "1111" -> {
                return 'F';
            }
            default -> {
                System.out.println("Error converting binary to hexadecimal: " + binary + "(binary)");
                return 'Z';
            }
        }
    }

    @Override
    public int parse() {
        return hexadecimalToBinary().toDecimal().decimalValue();
    }

    private Binary hexadecimalToBinary() {
        int valueLength = value.length();
        String octal = value;
        switch (valueLength % 4) {
            case 1 -> {
                octal = "0" + value;
            }
            case 2 -> {
                octal = "00" + value;
            }
            case 3 -> {
                octal = "000" + value;
            }
        }
        List<Character> split = Stringer.splitStringInCharacters(octal);
        StringBuilder binary = new StringBuilder();
        for (char hex : split) {
            binary.append(hexadecimalToBinary((hex)));
        }
        return new Binary(binary.toString());
    }
}
