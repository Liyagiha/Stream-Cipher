import java.util.Random;

public class MyRandom extends Random {
    private long state;
	private final static long m = (1L << 32); // 2^32
    private final static long a = 1664525L; // coprime
    private final static long c = 1013904223L; // coprime

    public MyRandom() {
        this(System.currentTimeMillis());
    }
	
    public MyRandom(long seed) {
        this.state = seed;
    }

    @Override
    protected int next(int bits) {
        state = (a * state + c) % m;
        return (int)(state >>> (32 - bits));
    }

    @Override
    public void setSeed(long seed) {
        this.state = seed;
    }
}