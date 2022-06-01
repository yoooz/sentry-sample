package org.example.client;

import com.github.javafaker.Faker;
import io.sentry.Sentry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    static {
        Sentry.init(options -> {
            options.setDsn("http://2f12121fdfd6423390048efcf4b57606@localhost:9000/1");
            options.setBeforeSend((event, hint) -> {
                event.setFingerprints(Arrays.asList("{{ logger }}"));
                return event;
            });
        });
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Logger logger = LoggerFactory.getLogger(getFaker("logger"));
            logger.error(getFaker("error"));
            sleep(1000);
        }
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {}
    }

    private static String getFaker(String prefix) {
        List<String> list = Arrays.asList("one", "two", "three", "four", "five");
        return String.format("%s-%s", prefix, list.get((new Random()).nextInt(list.size())));
    }
}
