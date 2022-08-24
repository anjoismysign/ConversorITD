package me.anjoismysign.itd.converter;

public class Binary implements Positional, Convertable {

    private final String value;

    public Binary(String value) {
        this.value = value;
    }

    public Binary(Decimal decimal) {
        int decimalValue = decimal.decimalValue();
        StringBuilder binary = new StringBuilder();
        while (decimalValue > 0) {
            binary.append(decimalValue % 2);
            decimalValue /= 2;
        }
        this.value = binary.reverse().toString();
    }

    public String binary() {
        return value;
    }

    @Override
    public Decimal toDecimal() {
        return new Decimal(parse());
    }

    @Override
    public String print() {
        return value + "(binario)";
    }

    @Override
    public int decimalValue() {
        return parse();
    }

    @Override
    public int parse() {
        int x = 0;
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == '1') {
                x += Math.pow(2, value.length() - i - 1);
            }
        }
        return x;
    }
}
