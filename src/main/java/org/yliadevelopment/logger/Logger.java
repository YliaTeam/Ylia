package org.yliadevelopment.logger;

public interface Logger {
    void log(LogLevel level, String fmt, Object... args);
}
