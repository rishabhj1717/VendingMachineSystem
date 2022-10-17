import java.util.ArrayList;

public class CashPayment extends Payment{
    String type;

    CashPayment(String type, ArrayList<Product> orderList){
        super(orderList);
        this.type = type;
    }

    @Override
    int getTotalAmount(){
        return this.calculateTotal();
    }
}
