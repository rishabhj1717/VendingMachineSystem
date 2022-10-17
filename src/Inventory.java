import java.util.HashMap;
import java.util.Map;

public class Inventory {
    Map<Integer, Product> productMap;

    Inventory() {
        this.productMap = new HashMap<>();
        this.fillInventory();
    }

    void fillInventory() {
        try {
            for (int i = 0; i < 5; i++) {
                int randomNumForAmount = (int) (Math.random() * (100) + 50);
                int prodId = i + 1;
                Product prod = new Product(prodId, 100 + randomNumForAmount, "Product " + (i + 1),
                        "This is product " + (i + 1), 5);
                this.productMap.put(Integer.valueOf(prodId), prod);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    Product getProductDetail(int productId){
        return this.productMap.get(Integer.valueOf(productId));
    }

    

}
