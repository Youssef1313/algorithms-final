import org.openjdk.jmh.annotations.*;

public class Benchmarks {
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 1)
    public void init() {
        fibbonaci_loop(20);
    }

    double fibbonaci_loop(int n){
        double prev=0d, next=1d, result=0d;
        for (int i = 0; i < n; i++) {
            result=prev+next;
            prev=next;
            next=result;
        }
        return result;
    }
}
