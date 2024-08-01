import consumer.AverageCalculatingConsumer;
import consumer.Consumer;
import consumer.SumCalculatingConsumer;

public class Main {
    public static void main(String[] args) {
        Consumer averageCalculatingConsumer = new AverageCalculatingConsumer(),
                sumCalculatingConsumer = new SumCalculatingConsumer();

        benchmarkConsumer(averageCalculatingConsumer, "AverageCalculatingConsumer");
        benchmarkConsumer(sumCalculatingConsumer, "SumCalculatingConsumer");
    }

    private static void benchmarkConsumer(Consumer consumer, String consumerName) {
        int numberOfOperations = 50_000_000;
        long startTime, endTime;

        // Benchmark accept method
        startTime = System.currentTimeMillis();
        for (int i = 0; i < numberOfOperations; i++) {
            consumer.accept(i);
        }
        endTime = System.currentTimeMillis();
        long acceptTime = endTime - startTime;

        // Benchmark mean method
        startTime = System.currentTimeMillis();
        double mean = consumer.mean();
        endTime = System.currentTimeMillis();
        long meanTime = endTime - startTime;

        System.out.println(consumerName + " - Accept Time: " + acceptTime + " ms");
        System.out.println(consumerName + " - Mean Time: " + meanTime + " ms");
        System.out.println(consumerName + " - Mean: " + mean + "\n");
    }
}
