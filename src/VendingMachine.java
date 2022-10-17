import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * Display all products in vending machine
 * Toggle quantity of products displayed in vending machine!
 * Interact with inventory to toggle quantity of products
 * 
 */

public class VendingMachine {
    Inventory inventoryData;
    ArrayList<Product> orderList; // list of orders which need to be billed
    // ArrayList<Product> cartList; // list of all orders added to cart.
    Map <Integer, Integer> productQuantityMap = new HashMap<>();

    VendingMachine(Inventory inventoryData) {
        this.inventoryData = inventoryData;
        orderList = new ArrayList<>();
        // cartList = new ArrayList<>();
    }

    int getProductQuantityRemaining(int productId){
        int productQtyAddedToCart = productQuantityMap.getOrDefault(productId, 0);
        int originalQuantity = inventoryData.getProductDetail(productId).getQuantity();
        return originalQuantity - productQtyAddedToCart;
    }

    void displayAllProductsToCustomer() {
        System.out.println("----------------------Vending machine Products-------------------------");
        System.out.println();
        this.inventoryData.productMap.forEach((k, v) -> {
            System.out.printf("Id: %d, Name: %s , Price: %d , quantity: %d , description: %s.%n", v.productId, v.name,
                    v.getAmount(), getProductQuantityRemaining(v.productId), v.description);
            System.out.println();
        });
    }

    private boolean checkIfOrderCanBePlaced(int productId){
        int productQtyAddedToCart = productQuantityMap.getOrDefault(productId, 0);
        int originalQuantity = inventoryData.getProductDetail(productId).getQuantity();
        return (productQtyAddedToCart < originalQuantity);
    }

    private boolean checkIfItemCanBeRemoved(int productId){
        int qty = productQuantityMap.getOrDefault(productId, 0);
        return qty != 0;
    }

    void addToCart(int productId)throws CartException{
        if(!this.checkIfOrderCanBePlaced(productId)){
            throw new CartException("You cannot add to cart as product is out of stock");
        }
        orderList.add(this.inventoryData.getProductDetail(productId));
        int qty = productQuantityMap.getOrDefault(productId, 0);
        productQuantityMap.put(productId, ++qty);
    }

    void removeFromCart(int productId)throws CartException{
        int qty = productQuantityMap.getOrDefault(productId, 0);
        if(!checkIfItemCanBeRemoved(productId)){
            throw new CartException("Cannot remove item from cart as it does not exist in the cart");
        }
        orderList.remove(this.inventoryData.getProductDetail(productId));
        productQuantityMap.put(productId, --qty);
    }

    void display(){
        System.out.println("--------------------------------------------------------");
        System.out.println();
        for(Product v: orderList){
            System.out.printf("Id: %d, Name: %s , Price: %d , quantity: %d , description: %s.%n%n", v.productId, v.name,
                    v.getAmount(), productQuantityMap.get(v.productId), v.description);
        }
    }

    void finaliseOrder(){
        for(Product v: orderList){
            v.updateQuantity(v.quantity - productQuantityMap.getOrDefault(v.productId, 0));
        }
    }

    ArrayList<Product> getOrderList(){
        return this.orderList;
    }

}
