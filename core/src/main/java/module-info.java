module core {
    requires x.snowroller.spi;
    requires com.google.gson;
    uses x.snowroller.spi.Page;
    uses x.snowroller.spi.CurrencyConverter;
    opens x.snowroller.models to com.google.gson;
}