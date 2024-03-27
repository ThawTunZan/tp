package seedu.duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FAPTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpStreams() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    public void restoreStreams() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    public void testInit() {
        String simulatedUserInput = "init n/bob\n";
        provideInput(simulatedUserInput);

        FAP.main(new String[]{});

        String output = testOut.toString();

        String expectedOutput =
                "Hello! This is your CEG Future Academic Planner!\n" +
                        "What can I do for you?\n" +
                        "__________________________________________________\n" +
                        "__________________________________________________\n" +
                        "Hello bob!\n" +
                        "What would you like to do today?\n" +
                        "__________________________________________________\n" +
                        "An error occurred: No line found\n";
        assertTrue(output.contains(expectedOutput));
    }

    @Test
    public void invalidInitTest() {

        String simulatedUserInput = "init n/";
        provideInput(simulatedUserInput);
        FAP.main(new String[]{});
        String output = testOut.toString();

        String expectedOutput =
                "__________________________________________________\n" +
                        "Hello! This is your CEG Future Academic Planner!\n" +
                        "What can I do for you?\n" +
                        "__________________________________________________\n" +
                        "__________________________________________________\n" +
                        "INVALID COMMAND\n" +
                        "__________________________________________________\n" +
                        "An error occurred: No line found\n";

        assertTrue(output.contains(expectedOutput));
    }
}
