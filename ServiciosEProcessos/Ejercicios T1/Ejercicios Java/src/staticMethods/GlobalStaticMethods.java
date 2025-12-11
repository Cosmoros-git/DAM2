package staticMethods;

import java.util.*;

public class GlobalStaticMethods {

    public static final boolean DEBUG_MESSAGES = false;
    // No hera tan util como pensaba.

    public static int exerciseVariant(Scanner sc, int min, int max) {
        int type = 0;
        do {
            try {
                type = sc.nextInt();
                if (type < min || type > max) {
                    System.out.println("Variante invalido");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Variante invalido");
            }
        } while (type < min || type > max);

        return type;
    }

    public static List<Integer> arrayPrimesEratosthenes(int n) {
        List<Integer> primes = new ArrayList<>();
        if (n < 2) return primes;

        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true); // Apparently inherent java array fill feature.
        isPrime[0] = isPrime[1] = false; // Marks 0, 1 as non primes.

        int limit = (int) Math.sqrt(n); //Checks up to sqroot. Aka 9 = 3;
        for (int i = 2; i <= limit; i++) { // Starts from 2.
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    // i=2 j=4, j=<n, j=2+2, so it ends with marking 4,6,8 as non primes.
                    // i=3 j=9 finishes running. Instead of checking all it marks stuff as non primes.
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        return primes;
    }

    public static boolean isPrime(int n) {
        if (n <= 1) return false;

        for (int i = 2; i < n; i++)
            if (n % i == 0) return false;

        return true;
    }
}
