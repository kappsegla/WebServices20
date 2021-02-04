package x.snowroller;

import x.snowroller.spi.Page;

import java.util.ServiceLoader;

//Build with maven package
//Run with:
//java -p "core\target\core-1.0-SNAPSHOT.jar;spi\target\spi-1.0-SNAPSHOT.jar;plugin\target\plugin-1.0-SNAPSHOT.jar" -m core/x.snowroller.PluginExample

//Running with module system, provide module path(s) and main class
//java --module-path core-1.0-SNAPSHOT.jar;modules -m core/x.snowroller.PluginExample
public class PluginExample {

    public static void main(String[] args) {
        ServiceLoader<Page> loader = ServiceLoader.load(Page.class);

        for( Page page : loader ) {
            page.execute();
        }
    }
}
