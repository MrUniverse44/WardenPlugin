package me.blueslime.wardenplugin.logs;

public interface WardenLogs {

    default void error(String... messages) {
        String prefix = getPrefix(WardenLog.ERROR);

        for (String message : messages) {
            send(prefix + message);
        }
    }

    default void error(Exception exception) {
        printException(exception);
    }

    default void error(Exception exception, String... messages) {
        String prefix = getPrefix(WardenLog.ERROR);

        for (String message : messages) {
            send(prefix + message);
        }

        printException(exception);
    }

    default void warn(String... messages) {
        String prefix = getPrefix(WardenLog.WARN);

        for (String message : messages) {
            send(prefix + message);
        }
    }

    default void debug(String... messages) {
        String prefix = getPrefix(WardenLog.DEBUG);

        for (String message : messages) {
            send(prefix + message);
        }
    }

    default void info(String... messages) {
        String prefix = getPrefix(WardenLog.INFO);

        for (String message : messages) {
            send(prefix + message);
        }
    }

    void send(String... message);

    default void printException(Exception exception) {
        String prefix = getPrefix(WardenLog.ERROR);
        Class<?> current = exception.getClass();
        String location = current.getName();
        String error = current.getSimpleName();
        send(prefix + " -------------------------");
        send(prefix + "Location: " + location.replace("." + error,""));
        send(prefix + "Error: " + error);

        if (exception.getStackTrace() != null) {
            send(prefix + "StackTrace: ");

            for (StackTraceElement line : exception.getStackTrace()) {
                String convertedLine = (prefix + " (Line: "
                        + line.getLineNumber() + ") (Class: " + line.getFileName() + ") (Method: "
                        + line.getMethodName() + ")")
                        .replace(".java","");

                send(
                        convertedLine
                );
            }
        }
        send(prefix + " -------------------------");
    }

    WardenLogs setPrefix(WardenLog log, String prefix);

    String getPrefix(WardenLog prefix);

    void build();
}
