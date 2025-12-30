package tr.edu.istiklal;

public class HesapIslemleri {
    public double islemYap(double sayi1, double sayi2, String operator) {
        switch (operator) {
            case "+": return sayi1 + sayi2;
            case "-": return sayi1 - sayi2;
            case "*": return sayi1 * sayi2;
            case "/": return (sayi2 == 0) ? Double.NaN : sayi1 / sayi2;
            default: return 0;
        }
    }
}