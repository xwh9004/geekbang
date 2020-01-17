package com.example;

/**
 * <p><b>Description:</b>  TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 10:13 on 2020/1/15
 * @version V0.1
 * @classNmae Passenger
 */

// Run with: java -XX:CompileCommand='dontinline,*.passThroughImmigration' Passenger
public abstract class Passenger {
    abstract void passThroughImmigration();
    public static void main(String[] args) {
        Passenger a = new ChinesePassenger();
        Passenger b = new ForeignerPassenger();
        long current = System.currentTimeMillis();
        for (int i = 1; i <= 2_000_000_000; i++) {
            if (i % 100_000_000 == 0) {
                long temp = System.currentTimeMillis();
                System.out.println(temp - current);
                current = temp;
            }
            Passenger c = (i < 1_000_000_000) ? a : b;
            c.passThroughImmigration();
        }
    }
}
class ChinesePassenger extends Passenger {
    @Override void passThroughImmigration() {}
}
class ForeignerPassenger extends Passenger {
    @Override void passThroughImmigration() {}
}
