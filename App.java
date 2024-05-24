
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
