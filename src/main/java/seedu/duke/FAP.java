package seedu.duke;

import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.storage.Storage.loadDataFromFile;
import static seedu.duke.storage.Storage.saveModulesToFile;
import static seedu.duke.ui.Ui.printGreeting;
import static seedu.duke.ui.Ui.printCommandGuide;

import seedu.duke.user.User;
import seedu.duke.command.Command;
import seedu.duke.json.JsonManager;
import seedu.duke.modules.ModuleList;
import seedu.duke.parser.Parser;
import seedu.duke.ui.Ui;

/**
 * The main class for the FAP (Future Academic Planner) application.
 * It initializes the application, loads existing user and module data from file,
 * displays a greeting and command guide, and then enters the command processing loop.
 * The application continues to run until the user inputs the "bye" command.
 */
public class FAP {

    public static User user = new User();
    public static ModuleList moduleList = new ModuleList();
    public static JsonManager jsonManager = new JsonManager();
    public static String filePath = Paths.get(System.getProperty("user.dir"),
            "data", "CS2113_AY2324S2_FAP_Storage.txt").toString();
    public static final Logger LOGGER = Logger.getLogger(FAP.class.getName());
    private static final String BYE = "bye";

    /**
     * The main method that serves as the entry point for the application.
     * It sets the logging level, attempts to load data from file, and then
     * enters the main application loop until the user decides to exit.
     *
     * @param args The command line arguments passed to the application (not used).
     */
    public static void main(String[] args) {

        LOGGER.setLevel(Level.OFF);
        try {
            loadDataFromFile(filePath);
            printGreeting(user.getName());
            printCommandGuide();
            assert moduleList != null : "moduleList should not be null";
            runApplication();
        } catch (AssertionError e) {
            LOGGER.log(Level.SEVERE, "Assertion failed: " + e.getMessage(), e);
            System.err.println("Critical assertion failure: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An unexpected error occurred: " + e.getMessage(), e);
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * The main application loop that continuously prompts the user for input,
     * processes the input as commands, and performs the corresponding actions.
     * The loop terminates when the user inputs the "bye" command or when an error occurs.
     */
    private static void runApplication() {

        Ui ui = new Ui();
        boolean continueRunning = true;

        while (continueRunning) {
            try {
                String userInput = ui.getUserInput();
                LOGGER.log(Level.INFO, "User input: " + userInput);
                Command command = Parser.getCommand(userInput);
                command.execute(userInput);
                user.resetModuleStatuses();
                saveModulesToFile(filePath);
                if (userInput.equals(BYE)) {
                    continueRunning = false;
                    ui.close();
                }

            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "An error occurred: " + e.getMessage());
                System.err.println("An error occurred: " + e.getMessage());
                ui.close();
                continueRunning = false; // Exit loop on error
            }
        }
    }
}
