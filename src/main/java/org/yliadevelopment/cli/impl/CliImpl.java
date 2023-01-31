package org.yliadevelopment.cli.impl;

import java.io.IOException;

import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.yliadevelopment.cli.ICli;
import org.yliadevelopment.logger.MainLogger;

public class CliImpl implements ICli {

    private Terminal terminal;
    private LineReader lineReader;

    @Override
    public boolean startCli() {
        try {
            this.terminal = TerminalBuilder.builder()
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.lineReader = LineReaderBuilder.builder()
                .terminal(this.getTerminal())
                .completer(new StringsCompleter("--start"))
                .build();
        String line = null;
        while (true) {
            try {
                line = this.getLineReader().readLine(">");
            } catch (UserInterruptException | EndOfFileException e) {
                MainLogger.get().error("User killed the Cli");
                break;
            }
            MainLogger.get().info("Value: %s", line);
        }
        return true;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public LineReader getLineReader() {
        return lineReader;
    }

}
