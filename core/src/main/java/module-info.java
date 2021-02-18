module core {
    requires x.snowroller.spi;
    requires x.snowroller.fileutils;
    requires com.google.gson;

    requires static lombok;

    uses x.snowroller.spi.Page;
    uses x.snowroller.spi.CurrencyConverter;
    opens x.snowroller.models to com.google.gson;
}

//    If using mongodb add the following requirements.
//    requires org.mongodb.driver.sync.client;
//    requires org.mongodb.driver.core;
//    requires org.mongodb.bson;
//    requires jdk.net;
//    requires org.slf4j;

//    If using mssql the following code is needed
//module core {
//        requires java.sql;
//        requires net.bytebuddy;
//        requires com.fasterxml.classmate;
//        requires java.persistence;
//        requires  java.xml.bind;
//        opens x.snowroller to com.google.gson, org.hibernate.orm.core;
//        }