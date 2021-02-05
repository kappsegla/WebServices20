package x.snowroller.plugin;

import x.snowroller.spi.CurrencyConverter;

public class SkrToEuro implements CurrencyConverter {
    @Override
    public float convert(float input) {
        return input / 10.11f;
    }
}
