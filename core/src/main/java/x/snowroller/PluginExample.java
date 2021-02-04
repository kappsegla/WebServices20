package x.snowroller;

import x.snowroller.spi.Page;

import java.util.ServiceLoader;

//Build with:
// mvn package
//Open Terminal and do:
// cd core\target
//On Windows Run with:
// java --module-path core-1.0-SNAPSHOT.jar;modules -m core/x.snowroller.PluginExample
//On Mac Run with:
// java --module-path core-1.0-SNAPSHOT.jar:modules -m core/x.snowroller.PluginExample


public class PluginExample {

    public static void main(String[] args) {
        ServiceLoader<Page> loader = ServiceLoader.load(Page.class);

        for( Page page : loader ) {
            page.execute();
        }
    }
}
