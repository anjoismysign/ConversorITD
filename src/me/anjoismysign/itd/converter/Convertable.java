package me.anjoismysign.itd.converter;

public interface Convertable {
    /**
     * @return valor en Decimal
     */
    Decimal toDecimal();

    /**
     * @return alternativa a Positional#decimalValue
     */
    int parse();
}
