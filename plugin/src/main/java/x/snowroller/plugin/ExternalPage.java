package x.snowroller.plugin;

import x.snowroller.spi.Page;

public class ExternalPage implements Page {
    @Override
    public void execute() {
        System.out.println("This is External Page");
    }
}
