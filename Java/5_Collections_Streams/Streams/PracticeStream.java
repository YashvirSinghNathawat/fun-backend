import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class PracticeStream {
    private static void waysCreateStream() {
        // From Collections
        List<Integer> list = new ArrayList<>();
        list.add(1);  list.add(2); list.add(3);
        Stream<Integer> listStream = list.stream();

        // From Array
        Integer[] arr = {1,2,3,4};
        Stream<Integer> arrStream = Arrays.stream(arr);

        // From Static Method
        Stream<Integer> streamStaticMEthod = Stream.of(1, 2, 3);

        // From Stream Builder
        Stream.Builder<Integer> streamBuilder = Stream.builder();
        streamBuilder.add(123).add(234).add(345);
        Stream<Integer> streamFromBuilder = streamBuilder.build();

        // From Stream Iterate
        Stream<Integer> streamFromIterate = Stream.iterate(1000, a -> a + 100);
        
    }

    private static void nonTerminalMethods() {
        List<List<Integer>> integer2DList = Arrays.asList(
            Arrays.asList(4,1,8),
            Arrays.asList(7,2,9),
            Arrays.asList(5,6,3)
        );

        Stream<Integer> streamInteger = integer2DList.stream().flatMap(integerList -> integerList.stream());
        Stream<Integer> filteredStream = streamInteger.filter(a -> a>=4);
        Stream<String> mappedStream = filteredStream.map(a -> a.toString());
        Stream<String> sortedStream = mappedStream.sorted();
        LongStream longStream = sortedStream.mapToLong(Long::parseLong);
        Stream<Integer> integerStream= longStream.boxed().map(Long::intValue);
        Stream<Integer> limitedStream = integerStream.skip(1).limit(5);
        Stream<Integer> peekStream = limitedStream.peek((Integer a) -> System.out.print(a + " "));
        
        List<Integer> listString = peekStream.collect(Collectors.toList());
        System.out.println();
    }

    private static void terminalMethods() {
        List<Integer> list = Arrays.asList(5,88,3,65,2,34);

        System.out.println("********************************");
        list.stream().forEach(System.out::print);
        System.out.println();
        List<Integer> list_2 = list.stream().collect(Collectors.toList());

        Optional<Integer> value = list.stream().reduce((Integer a, Integer b) -> a+b);
        System.out.println(value.get());
        System.out.println("********************************");
        Integer[] intarr = list.stream().toArray((int size)->new Integer[size]);
        System.out.println("********************************");
        System.out.println("Max Value : "+ list.stream().max((a, b) -> a - b).get());
        System.out.println("********************************");
        System.out.println("Min Value : " + list.stream().max((a, b) -> b - a).get());
        System.out.println("********************************");
        System.out.println("Count Value : " + list.stream().count());
        System.out.println("********************************");
        System.out.println("AnyMatch Value : " + list.stream().anyMatch(a -> a==65));
        System.out.println("********************************");
        System.out.println("AllMatch Value : " + list.stream().allMatch(a -> a == 65));
        System.out.println("********************************");
        System.out.println("NoneMatch Value : " + list.stream().noneMatch(a -> a == 64));
        System.out.println("********************************");
        System.out.println("FindFirst Value : " + list.stream().findFirst().get());
        System.out.println("********************************");
        System.out.println("FindAny Value : " + list.stream().findAny().get());
        System.out.println("********************************");

    }
    
    private static long expensiveCalculation(int a) {
        long value = a;

        for (int i = 0; i < 100; i++) {
            value = value * 31 + i;
        }

        return value;
    }

    private static void parallelStream() {
        List<Integer> list = IntStream.rangeClosed(1, 10_000_000)
                .boxed()
                .collect(Collectors.toList());
        // Normal Stream
        long sequentialStartTime = System.currentTimeMillis();
        list.stream()
            .mapToLong(PracticeStream::expensiveCalculation)
            .sum();
        System.out.println("Sequential Time: " + (System.currentTimeMillis() - sequentialStartTime) + " ms");

        long parallelStartTime = System.currentTimeMillis();
        list.parallelStream()
                .mapToLong(PracticeStream::expensiveCalculation)
                .sum();
        System.out.println("Parallel Time: " + (System.currentTimeMillis() - parallelStartTime) + " ms");
    }
    public static void main(String[] args) {
        waysCreateStream();
        nonTerminalMethods();
        terminalMethods();
        parallelStream();
    }
}
