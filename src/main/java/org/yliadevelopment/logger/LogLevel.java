package org.yliadevelopment.logger;

import org.fusesource.jansi.Ansi;

public enum LogLevel {

    INFO    ("INFO" , Ansi.Color.DEFAULT, false),
    WARNING ("WARN" , Ansi.Color.YELLOW , false),
    ERROR   ("ERROR", Ansi.Color.RED    , true);

    public final String prefix;
    public final Ansi.Color color;
    public final boolean everythingColored;

    LogLevel(String prefix, Ansi.Color color, boolean everythingColored) {
        this.prefix = prefix;
        this.color = color;
        this.everythingColored = everythingColored;
    }

}

