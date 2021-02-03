package x.snowroller;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

public class PluginExample {

    public static void main(String[] args) {
        ServiceLoader<Page> loader = ServiceLoader.load(Page.class);

        List<String> list;

        for( var page : loader ) {
            page.execute();
        }
    }
}
