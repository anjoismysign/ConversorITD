package me.anjoismysign.itd.converter;

import java.util.List;

public class Octal implements Positional, Convertable {

    private final String value;

    public Octal(String value) {
        this.value = value;
    }

    public Octal(Decimal decimal) {
        Binary binary = new Binary(decimal);
        String binaryValue;
        switch (binary.binary().length() % 3) {
            case 1 -> binaryValue = "00" + binary.binary();
            case 2 -> binaryValue = "0" + binary.binary();
            default -> binaryValue = binary.binary();
        }
        StringBuilder octal = new StringBuilder();
        List<String> split = Stringer.splitString(binaryValue, 3);
        for (String s : split) {
            octal.append(binaryToOctal(s));
        }
        this.value = octal.toString();

    }

    @Override
    public Decimal toDecimal() {
        return new Decimal(parse());
    }

    @Override
    public String print() {
        return value + "(octal)";
    }

    @Override
    public int decimalValue() {
        return parse();
    }

    /**
     * @param octal in case being out of range, it will return 000
     * @return binary value of octal
     */
    private String octalToBinary(int octal) {
        switch (octal) {
            case 1 -> {
                return "001";
            }
            case 2 -> {
                return "010";
            }
            case 3 -> {
                return "011";
            }
            case 4 -> {
                return "100";
            }
            case 5 -> {
                return "101";
            }
            case 6 -> {
                return "110";
            }
            case 7 -> {
                return "111";
            }
            default -> {
                return "000";
            }
        }
    }

    /**
     * @return -1 in case of error
     */
    private String binaryToOctal(String binary) {
        switch (binary) {
            case "000" -> {
                return "0";
            }
            case "001" -> {
                return "1";
            }
            case "010" -> {
                return "2";
            }
            case "011" -> {
                return "3";
            }
            case "100" -> {
                return "4";
            }
            case "101" -> {
                return "5";
            }
            case "110" -> {
                return "6";
            }
            case "111" -> {
                return "7";
            }
            default -> {
                System.out.println("Error converting binary to octal: " + binary + "(binary)");
                return "null";
            }
        }
    }

    @Override
    public int parse() {
        return octalToBinary().toDecimal().decimalValue();
    }

    private Binary octalToBinary() {
        int valueLength = value.length();
        String octal = value;
        if (valueLength % 3 == 1) {
            octal = "0" + value;
        }
        if (valueLength % 3 == 2) {
            octal = "00" + value;
        }
        List<String> split = Stringer.splitString(octal, 3);
        StringBuilder binary = new StringBuilder();
        for (String group : split) {
            binary.append(octalToBinary(Integer.parseInt(group)));
        }
        return new Binary(binary.toString());
    }
}
