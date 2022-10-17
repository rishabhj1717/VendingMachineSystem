public class Customer extends UniqueId {
    String customerId;
    int age;
    long mobileNumber;
    String name;
    String address;
    String gender;
    String cityOfResidence;
    String stateOfResidence;
    int pincode;

    Customer(String name, String gender, long mobileNumber, String cityOfResidence, String stateOfResidence,
            int pincode, int age) throws CustomerException {
        this.name = name;
        if (!checkGender(gender)) {
            throw new CustomerException("Incorrect gender");
        }
        if (!checkMobileNumber(mobileNumber)) {
            throw new CustomerException("Incorrect mobile number entered");
        }
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.cityOfResidence = cityOfResidence;
        this.stateOfResidence = stateOfResidence;
        this.pincode = pincode;
        this.age = age;
        this.setAddress(); // would set address by appending, city, state and pincode
        this.customerId = getUniqueId(); // a basic implementation to work like a UUID
    }

    private boolean checkGender(String gender) {
        return (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female")
                || gender.equalsIgnoreCase("others"));
    }

    private boolean checkMobileNumber(long mobileNum) {
        return String.valueOf(mobileNum).length() == 10;
    }

    private void setAddress() {
        this.address = this.cityOfResidence + ", " + this.stateOfResidence + ", " + "Pincode - " + this.pincode + ".";
    }

}
