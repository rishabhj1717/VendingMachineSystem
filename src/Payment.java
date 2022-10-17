import java.util.ArrayList;

public class Payment {
    int amount;
    ArrayList<Product> orderList;

    Payment(ArrayList<Product> orderList){
        this.amount = 0;
        this.orderList = orderList;
    }

    int calculateTotal(){
        for(Product v: this.orderList){
            this.amount += v.getAmount();
        }
        return this.amount;
    }

    int getTotalAmount(){
        return 0;
    }
}
