package x.snowroller;

public class ExternalPage implements Page{
    @Override
    public void execute() {
        System.out.println("This is External Page");
    }
}
