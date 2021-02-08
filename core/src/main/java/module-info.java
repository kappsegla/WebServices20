module core {
    requires x.snowroller.spi;
    requires x.snowroller.fileutils;
    requires com.google.gson;

//    requires org.mongodb.driver.sync.client;
//    requires org.mongodb.driver.core;
//    requires org.mongodb.bson;
//    requires jdk.net;
//    requires org.slf4j;

    requires static lombok;

    uses x.snowroller.spi.Page;
    uses x.snowroller.spi.CurrencyConverter;
    opens x.snowroller.models to com.google.gson;
}