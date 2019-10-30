import java.util.Timer;

public class SortCompare {
    public static double time(String alg, Double[] a) {
        Timer timer = new Timer();
        if      (alg.equals("Insertion"))       Insertion.sort(a);
        else if (alg.equals("Selection"))       Selection.sort(a);
        else throw new IllegalArgumentException("Invalid algorithm: " + alg);

    }
}
