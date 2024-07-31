# Consumer Benchmark Project

This project implements and benchmarks different `Consumer` classes that accept integers and calculate the mean of the numbers consumed in the last 5 minutes. The project includes two implementations: `AverageCalculatingConsumer` and `SumCalculatingConsumer`.

## Implementations

### AverageCalculatingConsumer
This implementation calculates the average in the `mean` method using the Stream API without explicitly calculating the sum.

### SumCalculatingConsumer
This implementation maintains a running sum of the numbers and calculates the mean by dividing the sum by the number of elements.

## Benchmark Results

The benchmark results for both implementations are as follows:

```
AverageCalculatingConsumer - Accept Time: 33472 ms
AverageCalculatingConsumer - Mean Time: 127 ms
AverageCalculatingConsumer - Mean: 9.3903674E7

SumCalculatingConsumer - Accept Time: 35695 ms
SumCalculatingConsumer - Mean Time: 0 ms
SumCalculatingConsumer - Mean: 9.23078165E7
```

## Benchmark Method

The benchmark method measures the time taken to perform a series of `accept` and `mean` operations for each implementation.

```java
private static void benchmarkConsumer(Consumer consumer, String consumerName) {
    int numberOfOperations = 100000000;
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
```

## Usage

To run the benchmarks, create instances of the `AverageCalculatingConsumer` and `SumCalculatingConsumer` classes and call the `benchmarkConsumer` method for each instance.

## Conclusion

This project demonstrates the implementation and benchmarking of different `Consumer` classes. The benchmark results show the performance of each implementation in terms of accept and mean times.
