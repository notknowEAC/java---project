
import java.util.*;
public class Drink extends Menu{
    public String size ;
    public String sweet ;

    public Drink(String name,int price,String size,String sweet){
        super(name,price);
        this.name=name;
        this.price=price;
        this.size=size;
        this.sweet=sweet;

    }
}
