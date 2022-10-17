import java.util.Scanner;

public class App {
    /* This is our driver class */

    static Customer registerCustomer(Scanner sc) throws CustomerException {
        String name, gender, city, state;
        long mobileNumber;
        int age, pincode;
        boolean customerSuccess = true;
        Customer custObj = null;
        do {
            try {
                customerSuccess = true;
                System.out.println("Please enter customer details: ");
                System.out.println();
                System.out.println("Please enter your name");
                name = sc.nextLine();
                System.out.println("Please enter your gender:");
                gender = sc.nextLine();
                System.out.println("Please enter your mobile number(10 digits): ");
                mobileNumber = Long.parseLong(sc.nextLine());
                System.out.println("Please enter you age: ");
                age = Integer.parseInt(sc.nextLine());
                System.out.println("Which city do you reside in? ");
                city = sc.nextLine();
                System.out.println("Which state do you reside in? ");
                state = sc.nextLine();
                System.out.println("Pincode of your area? ");
                pincode = Integer.parseInt(sc.nextLine());
                custObj = new Customer(name, gender, mobileNumber, city, state, pincode, age);

            } catch (CustomerException e) {
                customerSuccess = false;
                System.out.println(e.getMessage());
            } catch (Exception e) {
                customerSuccess = false;
                System.out.println("Please enter valid data");
            }
        } while (!customerSuccess);

        return custObj;
    }

    static void displayAllOps() {
        System.out.println(" 1. Add to cart \n 2. Remove from cart \n 3. Proceed to checkout");
    }

    static void inventoryAndVending(Scanner sc, Inventory inventoryObj, VendingMachine vm) throws CartException {
        String choice = "";
        int opChoice = 0;

        do {
            
            System.out.println("All products: ");
            vm.displayAllProductsToCustomer();
            System.out.println();
            displayAllOps();
            opChoice = Integer.parseInt(sc.nextLine());
            if (opChoice == 1) {
                // add to cart functionality
                System.out.println("Which item do you want to add to cart?");
                int prodId = Integer.parseInt(sc.nextLine());
                vm.addToCart(prodId);
                System.out.println("------------------Your cart------------------");
                vm.display();
            } else if (opChoice == 2) {
                // remove from cart functionality
                System.out.println("Which item do you wish to remove from cart?");
                int prodId = Integer.parseInt(sc.nextLine());
                vm.removeFromCart(prodId);
                System.out.println("------------------Your cart------------------");
                vm.display();
            } else if (opChoice == 3) {
                System.out.println("-------------------Final order-----------------");
                vm.finaliseOrder();
                vm.display();
                break;
            } else {
                System.out.println("Invalid Operation Performed!");
            }
            System.out.println("Do you wish to continue or proceed to pay amount?(y/n)");
            choice = sc.nextLine();

        } while (choice.charAt(0) == 'y');

    }

    public static void main(String[] args) throws Exception {
        Customer custObj;
        Payment paymentObj;
        Scanner sc = new Scanner(System.in);
        int retryAttempts = 0;
        boolean customerSuccess = true;
        System.out.println("Hello, Welcome to our vending machine!");
        custObj = registerCustomer(sc);

        // Create inventory
        Inventory inventoryObj = new Inventory();

        // Create Vending machine
        VendingMachine vm = new VendingMachine(inventoryObj);
        do {
            try {

                inventoryAndVending(sc, inventoryObj, vm);

                // Payment functionality
                System.out.printf("How would you like to perform your payment?%n%n");
                System.out.println("1. Card (Total amount would double owing to some constraints) 2. Cash");
                int paymentChoice = Integer.parseInt(sc.nextLine());
                if (paymentChoice == 1) {
                    paymentObj = new CardPayment("card", vm.getOrderList());
                } else {
                    paymentObj = new CashPayment("cash", vm.getOrderList());
                }
                System.out.println("Total amount to be paid: " + paymentObj.getTotalAmount());

                System.out.println("Thank you for ordering, please do visit again");
                System.exit(0);
            } catch (CartException e) {
                System.out.println(e.getMessage());
                retryAttempts++;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);// because there is a problem with code.
            }
        } while (retryAttempts < 3);
    }
}
