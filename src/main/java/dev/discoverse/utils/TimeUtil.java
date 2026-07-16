package dev.discoverse.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ------------------------------------------------------------
 * DiscoVerse Time Utility
 * ------------------------------------------------------------
 *
 * Utility class for formatting and parsing time.
 *
 * Supported formats:
 * 10s
 * 5m
 * 2h
 * 7d
 * 1w
 *
 * Combined:
 * 1h30m
 * 2d5h10m
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class TimeUtil {

    private static final Pattern TIME_PATTERN =
            Pattern.compile("(\\d+)([smhdw])");

    /**
     * Prevent instantiation.
     */
    private TimeUtil() {
        throw new UnsupportedOperationException(
                "TimeUtil is a utility class."
        );
    }

    /**
     * Returns current system time.
     *
     * @return Current time in milliseconds.
     */
    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * Formats milliseconds into a readable duration.
     *
     * Example:
     * 1d 5h 20m 10s
     *
     * @param millis Milliseconds.
     * @return Formatted duration.
     */
    public static String formatDuration(final long millis) {

        Duration duration = Duration.ofMillis(millis);

        long days = duration.toDays();
        duration = duration.minusDays(days);

        long hours = duration.toHours();
        duration = duration.minusHours(hours);

        long minutes = duration.toMinutes();
        duration = duration.minusMinutes(minutes);

        long seconds = duration.getSeconds();

        StringBuilder builder = new StringBuilder();

        if (days > 0)
            builder.append(days).append("d ");

        if (hours > 0)
            builder.append(hours).append("h ");

        if (minutes > 0)
            builder.append(minutes).append("m ");

        if (seconds > 0 || builder.isEmpty())
            builder.append(seconds).append("s");

        return builder.toString().trim();
    }

    /**
     * Parses a time string.
     *
     * Examples:
     * 5m
     * 2h30m
     * 7d
     *
     * @param input Time string.
     * @return Milliseconds.
     */
    public static long parseTime(final String input) {

        if (input == null || input.isBlank()) {
            return 0L;
        }

        Matcher matcher = TIME_PATTERN.matcher(input.toLowerCase());

        long total = 0L;

        while (matcher.find()) {

            long value = Long.parseLong(matcher.group(1));
            String unit = matcher.group(2);

            switch (unit) {

                case "s" -> total += value * 1000L;

                case "m" -> total += value * 60_000L;

                case "h" -> total += value * 3_600_000L;

                case "d" -> total += value * 86_400_000L;

                case "w" -> total += value * 604_800_000L;

                default -> {
                }

            }

        }

        return total;
    }

    /**
     * Checks whether a timestamp has expired.
     *
     * @param timestamp Timestamp.
     * @return true if expired.
     */
    public static boolean hasExpired(final long timestamp) {
        return currentTimeMillis() >= timestamp;
    }

    /**
     * Returns remaining milliseconds.
     *
     * @param futureTimestamp Future timestamp.
     * @return Remaining milliseconds.
     */
    public static long remaining(final long futureTimestamp) {
        return Math.max(0L, futureTimestamp - currentTimeMillis());
    }

    /**
     * Returns current Instant.
     *
     * @return Current Instant.
     */
    public static Instant now() {
        return Instant.now();
    }

}