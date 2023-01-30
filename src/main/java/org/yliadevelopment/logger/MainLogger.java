package org.yliadevelopment.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

public class MainLogger implements Logger {

    private static final MainLogger instance;

    public static MainLogger get() {
        return instance;
    }

    static {
        AnsiConsole.systemInstall();

        instance = new MainLogger();
    }

    @Override
    public void log(LogLevel level, String fmt, Object... args) {
        Ansi ansi = new Ansi();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:MM:ss");
        String currentThreadName = Thread.currentThread().getName();

        ansi.fgBrightBlue().a("[").a(formatter.format(new Date())).a("] ").reset();
        ansi.fgBright(level.color).a(currentThreadName).a("/").a(level.prefix).a(": ");

        if (!level.everythingColored)
            ansi.reset();

        ansi.a(String.format(fmt, args)).reset().newline();

        AnsiConsole.out().print(ansi);
    }

    public void info(String fmt, Object... args) {
        this.log(LogLevel.INFO, fmt, args);
    }

    public void warn(String fmt, Object... args) {
        this.log(LogLevel.WARNING, fmt, args);
    }

    public void error(String fmt, Object... args) {
        this.log(LogLevel.ERROR, fmt, args);
    }

}