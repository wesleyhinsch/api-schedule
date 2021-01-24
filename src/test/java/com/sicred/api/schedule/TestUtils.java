package com.sicred.api.schedule;

public class TestUtils {

    private static int random(int n) {
        int ranNum = (int) (Math.random() * n);
        return ranNum;
    }

    private static int mod(int mod1, int mod2) {
        return (int) Math.round(mod1 - (Math.floor(mod1 / mod2) * mod2));
    }

    public static  String build() {
        int n = 9;
        int n1 = random(n);
        int n2 = random(n);
        int n3 = random(n);
        int n4 = random(n);
        int n5 = random(n);
        int n6 = random(n);
        int n7 = random(n);
        int n8 = random(n);
        int n9 = random(n);
        int d1 = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10;

        d1 = 11 - (mod(d1, 11));

        if (d1 >= 10)
            d1 = 0;

        int d2 = d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;

        d2 = 11 - (mod(d2, 11));

        String cpf = null;

        if (d2 >= 10)
            d2 = 0;
        cpf = "";

        cpf = "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + d1 + d2;

        return cpf;
    }

    public static int buildClosure() {
        long dgt1 = (int) Math.random();
        long dgt2 = (int) Math.random();
        long dgt3 = (int) Math.random();
        String concatDigts = dgt1+""+dgt2+""+dgt3;
        return Integer.parseInt(concatDigts);
    }

}
