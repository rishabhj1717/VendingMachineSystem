import java.util.Random;

class UniqueId{
    static Random random = new Random();
    
    static String getUniqueId(){
        return String.format("%04d", random.nextInt(10000));
    }
}