package Math;

public class math {

    public static long getfirstDigit(long n) {
        do {
            n /= 10;
        } while (n > 10);
        return n;
    }
}
