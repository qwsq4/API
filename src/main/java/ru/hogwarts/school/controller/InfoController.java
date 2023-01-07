package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

@RestController
public class InfoController {

    public final int port;

    public InfoController(@Value("${server.port}") int port) {
        this.port = port;
    }

    @GetMapping("/getPort")
    public int getPort() {
        return port;
    }

    @GetMapping("getIntSum")
    public String intSum() {
        Instant start = Instant.now();
        int sum = Stream
                .iterate(1, a -> a +1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b);
        Instant finish = Instant.now();
        long elapsed = Duration.between(start, finish).toMillis();
        return "result: " + sum + ", time elapsed: " + elapsed;
    }
}
