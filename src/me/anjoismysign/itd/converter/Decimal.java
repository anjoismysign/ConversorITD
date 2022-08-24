package me.anjoismysign.itd.converter;

public class Decimal implements Positional {
    //Se encapsula el atributo "decimal"
    private final int decimal;

    public Decimal(int decimal) {
        this.decimal = decimal;
    }

    public static Decimal parse(int integer) {
        return new Decimal(integer);
    }

    @Override
    public String print() {
        return decimal + "(decimal)";
    }

    @Override
    public int decimalValue() {
        return decimal;
    }
}
