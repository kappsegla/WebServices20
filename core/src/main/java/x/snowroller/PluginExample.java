package x.snowroller;

import x.snowroller.spi.Page;

import java.util.ServiceLoader;

//Run with:
//java -p "core.jar;spi.jar;plugin.jar" -m core/x.snowroller.PluginExample

public class PluginExample {

    public static void main(String[] args) {
        ServiceLoader<Page> loader = ServiceLoader.load(Page.class);

        for( Page page : loader ) {
            page.execute();
        }
    }
}
