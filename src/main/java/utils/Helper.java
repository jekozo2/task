package utils;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;


@Slf4j
public class Helper {

    /**
     * The method returns loaded properties located on a particular file path
     *
     * @param path from Central Root to the Properties file
     * @return the loaded properties
     */
    public static Properties loadProperties(String path) {
        Properties properties = new Properties();

        log.info("Loading properties for path {}", path);

        try {
            FileInputStream fis = new FileInputStream(path);

            properties.load(fis);

            log.info("Properties loaded!");

        } catch (IOException e) {
            throw new RuntimeException("Unable to load properties " + e);

        }

        return properties;
    }

    /**
     * The method returns a random number
     *
     * @param from is the bottom range number that can be returned, inclusive
     * @param to   the to range number that can be returned, inclusive
     * @return a random number
     */
    public static int getRandomNumber(int from, int to) {
        return (int) Math.floor(Math.random() * (to - from + 1) + from);
    }

    /**
     * The method returns a random boolean
     *
     * @return a random boolean
     */
    public static boolean getRandomBoolean() {
        return new Random().nextBoolean();
    }
}
