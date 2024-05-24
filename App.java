/**
 * FanOut/FanIn pattern is a concurrency pattern that refers to executing multiple instances of the
 * activity function concurrently. The "fan out" part is essentially splitting the data into
 * multiple chunks and then calling the activity function multiple times, passing the chunks.
 *
 * <p>When each chunk has been processed, the "fan in" takes place that aggregates results from each
 * instance of function and forms a single final result.
 *
 * <p>This pattern is only really useful if you can “chunk” the workload in a meaningful way for
 * splitting up to be processed in parallel.
 */
package com.iluwatar.fanout.fanin;

import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

  
@Slf4j
public class App {

  
  public static void main(String[] args) {
    final List<Long> numbers = Arrays.asList(1L, 3L, 4L, 7L, 8L);

    LOGGER.info("Numbers to be squared and get sum --> {}", numbers);

    final List<SquareNumberRequest> requests =
        numbers.stream().map(SquareNumberRequest::new).toList();

    var consumer = new Consumer(0L);

   
    final Long sumOfSquaredNumbers = FanOutFanIn.fanOutFanIn(requests, consumer);

    LOGGER.info("Sum of all squared numbers --> {}", sumOfSquaredNumbers);
  }
}
