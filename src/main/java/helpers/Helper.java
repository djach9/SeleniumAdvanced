package helpers;

import com.github.javafaker.Faker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Helper {
    private static Random random = new Random();
    private static Faker faker = new Faker();

    public static Random getRandom() {
        return random;
    }

    public static Faker getFaker() {
        return faker;
    }

    public static String getTodaysDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(new Date());
    }

    public static <T> T getRandomElement(List<T> elements) {
        return elements.get(random.nextInt(elements.size()));
    }

    public static <T> Collector<T, ?, T> toSingleton() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() != 1) {
                        throw new IllegalStateException();
                    }
                    return list.get(0);
                }
        );
    }
}

