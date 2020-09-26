/**
 * @FileName: Timer.java
 * @Author: zzc
 * @Date: 2020年09月21日 22:16:23
 * @Version V1.0.0
 */

package utils;

public class Timer {
    private long startTime;

    public Timer() {
        startTime = System.nanoTime();
    }

    public double getTime() {
        return (System.nanoTime() - startTime) / 1000000000.0;
    }

    public void reset() {
        startTime = System.nanoTime();
    }
}
