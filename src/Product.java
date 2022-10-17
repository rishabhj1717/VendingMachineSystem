public class Product {
    /*
     * This class shall have all the details for a particular product => Object will
     * be created in inventory!
     */
    int productId;
    private int amount;
    String name;
    String description;
    int quantity;

    Product(int productId, int amount, String name, String description, int quantity) {
        this.productId = productId;
        this.amount = amount;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    int getAmount(){
        return this.amount;
    }

    void updateQuantity(int quantity){
        this.quantity = quantity;
    }

    int getQuantity(){
        return this.quantity;
    }
    
}
