package x.snowroller.plugin;

import x.snowroller.spi.Page;

public class SwedishPage implements Page {

    public SwedishPage() {
        System.out.println("Swedish constructor");
    }

    @Override
    public void execute() {
        System.out.println("This is the swedish chef!");
    }
}
