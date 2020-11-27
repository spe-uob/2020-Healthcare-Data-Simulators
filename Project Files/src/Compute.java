import java.util.Random;

public class Compute {

    public int randomInt() {
        Random rand = new Random();
        int rand_int = rand.nextInt(1000);

        return rand_int;
    }
}
