import java.util.ArrayList;

public class CardPayment extends Payment{
    String type;

    CardPayment(String type, ArrayList<Product> orderList){
        super(orderList);
        this.type = type;
    }

    @Override
    int getTotalAmount() {
        return this.calculateTotal() * 2;
    }
}
