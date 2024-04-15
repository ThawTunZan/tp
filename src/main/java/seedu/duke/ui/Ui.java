package seedu.duke.ui;

import seedu.duke.enums.CEGModules;
import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Ui {
    private static final String COMMENT_LINE_FORMAT_REGEX = "#.*";
    private static final String COMMANDS_HELP_MESSAGE =
            "Available Commands:\n" +
            "NOTE: \"<WORD>\" represents a user-typed argument that is required for the command\n" +
            "1. set n/<NAME> curr/<CURR_SEM> - Set name & current semester\n" +
            "2. add c/<COURSE_CODE> w/<WHEN> - Add a module to your schedule\n" +
            "3. remove c/<COURSE_CODE> - Remove a module from your schedule\n" +
            "4. grade c/<COURSE_CODE> g/<GRADE> - Add or change a module grade\n" +
            "5. gpa - View your GPA\n" +
            "6. desiredgpa <GPA> - Calculates grades needed to achieve a desired GPA\n" +
            "7. view - View modules on your schedule\n" +
            "8. view c/<COURSE_CODE> - View selected module information\n" +
            "9. graduate - View remaining core modules and MCs left to graduate\n" +
            "10. help - View command syntax and list of commands available for FAP\n" +
            "11. bye - Exit the program\n\n" +
            "Argument format:\n" +
            "<NAME>: Alphabetic characters and optionally spaces between\n" +
            "<CURR_SEM>: Valid semester from 1-8\n" +
            "<COURSE_CODE>: Valid NUS course code from AY23-24\n" +
            "<WHEN>: Valid semester from 1-8\n" +
            "<GRADE>: Alphabetic grade (A+, A, A-, B+, B, B-, C+, C, D+, D, F, CS, S)\n" +
            "<GPA>: Number from 0 to 5";
    private final Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Prints the expected grades and GPA needed to achieve the desired GPA.
     *
     * @param moduleList            The ModuleList object containing the list of modules.
     * @param desiredGPA            The desired GPA.
     * @param acquiredGPA           The final GPA achieved by graduation with the expected grades.
     * @param upperBoundGradeNeeded The number of upper bound grades needed.
     * @param upperBound            The upper bound grade.
     * @param lowerBoundGradeNeeded The number of lower bound grades needed.
     * @param lowerBound            The lower bound grade.
     * @param moduleCreditsNotTaken The remaining module credits to take.
     */
    public static void printGradeExpectations(ModuleList moduleList, double desiredGPA, double acquiredGPA,
                                              int upperBoundGradeNeeded, double upperBound, int lowerBoundGradeNeeded,
                                              double lowerBound, float moduleCreditsNotTaken) {
        String formattedDesiredGPA = String.format("%.02f", desiredGPA);
        String formattedAcquiredGPA = String.format("%.02f", acquiredGPA);
        System.out.println("MCs left to take: " + moduleCreditsNotTaken);
        System.out.println("To obtain desired GPA of: " + formattedDesiredGPA);
        if (upperBoundGradeNeeded == 0) {
            System.out.println("You will need: " + lowerBoundGradeNeeded + " " + moduleList.numberToGrade(lowerBound));
        } else {
            System.out.println("You will need: " + upperBoundGradeNeeded + " " + moduleList.numberToGrade(upperBound) +
                    " and " + lowerBoundGradeNeeded + " " + moduleList.numberToGrade(lowerBound));
        }
        System.out.println("With the above grades, your end GPA will be: " + formattedAcquiredGPA);
    }

    public void close() {
        in.close();
    }

    /**
     * Retrieves user input from the console. Ignores input that are white spaces or comments
     *
     * @return The user input as a String.
     */
    public String getUserInput() {
        printHyphens();
        String currentLine = in.nextLine();

        while (shouldIgnore(currentLine)) {
            currentLine = in.nextLine();
        }
        printHyphens();
        return currentLine;
    }

    public static void printMessage(String str) {
        System.out.println(str);
    }

    /**
     * Determines whether the given line of input should be ignored.
     *
     * @param currentLine The line of input to be checked.
     * @return True if the line should be ignored, false otherwise.
     */
    private boolean shouldIgnore(String currentLine) {
        return currentLine.isBlank() || currentLine.trim().matches(COMMENT_LINE_FORMAT_REGEX);
    }

    public static void printGreeting() {
        printGreeting("");
    }

    public static void printGreeting(String name) {
        String greeting = name.equals("") ? "Hello!" : "Hello " + name + "!";

        printHyphens();
        System.out.println(greeting + " This is your CEG Future Academic Planner!");
        System.out.println("What would you like to do today?");
        printHyphens();
    }

    public static void printHelpInfoCommand() {
        System.out.println("Type \"help\" to view the list & syntax of available commands");
    }

    public static void printCommandGuide() {
        System.out.println(COMMANDS_HELP_MESSAGE);
    }

    public static void printUserInfo(String name, int startSem) {
        String greeting = String.format("Greetings %s! Your details are updated:", name);
        String updatedCurrentSemesterInfo = String.format("You are currently in Semester %d", startSem);

        System.out.println(greeting);
        System.out.println(updatedCurrentSemesterInfo);
    }

    public static void printScheduleHeader(String name) {
        String user = (name.isEmpty() || name == null) ? "Anonymous" : name;
        String title = String.format("CEG Study Plan for: %s", user);

        System.out.println(title);
    }

    public static void printScheduleDetails(int startSem, float mcsTaken, float mcslisted) {
        String currentSemesterInfo = String.format("- Current Study: Semester %d", startSem);
        String mcsTakenInfo = String.format("- Total MCs taken: %.2f / 160", mcsTaken);
        String mcsListedInfo = String.format("- Total MCs listed: %.2f / 160", mcslisted);

        System.out.println(currentSemesterInfo);
        System.out.println(mcsTakenInfo);
        System.out.println(mcsListedInfo);
    }

    // Helper function for printModulePlan()
    private static void printSemesterTableHeader(String... semesters) {
        System.out.print("|");
        for (String semester : semesters) {
            System.out.printf(" %-13s|", semester);
        }
        System.out.println();
    }

    // Helper function for printModulePlan()
    private static void printModulesForSemesters(
            Map<Integer, ArrayList<Module>> moduleBySemMap,
            int startSem,
            int endSem
    ) {
        // Get the max number of modules per sem between the start sem and end sem.
        int maxModules = 0;
        for (int i = startSem; i <= endSem; i++) {
            ArrayList<Module> moduleForSemester = moduleBySemMap.getOrDefault(i, new ArrayList<>());
            maxModules = Math.max(maxModules, moduleForSemester.size());
        }

        // Print the module codes and grades under each semester. Prints horizontally.
        for (int j = 0; j < maxModules; j++) {
            for (int i = startSem; i <= endSem; i++) {
                ArrayList<Module> modulesForSemester = moduleBySemMap.getOrDefault(i, new ArrayList<>());
                int moduleListSize = modulesForSemester.size();

                Module module = (moduleListSize > j) ? modulesForSemester.get(j) : null;
                String moduleCode = (module != null) ? module.getModuleCode() : "";
                String moduleGrade = (module != null && module.getModuleGrade() != null) ? module.getModuleGrade() : "";

                System.out.printf("|%-11s %-2s", moduleCode, moduleGrade);
            }
            System.out.println("|");
        }
    }

    public static void printModulePlan(Map<Integer, ArrayList<Module>> moduleBySemMap) {
        printHyphens();
        printSemesterTableHeader("Y1S1 [Sem 1]", "Y1S2 [Sem 2]", "Y2S1 [Sem 3]", "Y2S2 [Sem 4]");
        printModulesForSemesters(moduleBySemMap, 1, 4);
        printHyphens();
        printSemesterTableHeader("Y3S1 [Sem 5]", "Y3S2 [Sem 6]", "Y4S1 [Sem 7]", "Y4S2 [Sem 8]");
        printModulesForSemesters(moduleBySemMap, 5, 8);
        printHyphens();
    }

    public static void printModulesToComplete(ArrayList<String> modulesToComplete) {
        int courseCodeTableWidth = 25;
        int mcTableWidth = 10;
        int borderWidth = 5;

        System.out.println("+---------------------------+------------+");
        System.out.println("| Course Code               | MCs        |");
        System.out.println("+---------------------------+------------+");
        for (String moduleCode : modulesToComplete) {
            String paddedModuleCode = String.format("| %-" + courseCodeTableWidth + "s |", moduleCode);
            String paddedModuleMC = String.format(" %-" + mcTableWidth + "s |",
                    CEGModules.mapStringToEnum(moduleCode).getModuleMC());
            System.out.println(paddedModuleCode + paddedModuleMC);
        }
        System.out.println("+---------------------------+------------+");
        printWrappedText("Be sure to also complete 40MCs of Unrestricted Electives, GESS, GEC, and GEN modules.",
                courseCodeTableWidth + mcTableWidth + borderWidth, 0);
    }

    public static void printViewModule(String courseTitle, String courseMC, String courseDescription) {
        int minimumTableWidth = 50;
        int spaceBetween = 10;
        int titleLength = courseTitle.length() + " Title: ".length();
        int creditsLength = courseMC.length() + " Credits: ".length();
        int padding = 2;
        int border = 2;

        int contentWidth = titleLength + spaceBetween + creditsLength + padding;
        int separatorWidth = Math.max(minimumTableWidth, contentWidth);
        int calculatedSpaceBetween = Math.max(minimumTableWidth - contentWidth, 0);

        System.out.println("=".repeat(separatorWidth));
        System.out.println(String.format("| Title: %s%s          Credits: %s |",
                courseTitle,
                " ".repeat(calculatedSpaceBetween),
                courseMC));
        System.out.println("=".repeat(separatorWidth));
        printWrappedText("Description: " + courseDescription,
                separatorWidth - padding - border,
                separatorWidth - padding - border);
        System.out.println("=".repeat(separatorWidth));
    }

    public static void printWrappedText(String text, int width, int tableWidth) {
        int start = 0;
        int end = 0;

        while (end < text.length()) {
            end = start + width;
            if (end >= text.length()) {
                end = text.length();
            } else {
                while (end > start && !Character.isWhitespace(text.charAt(end))) {
                    end--;
                }
                if (end == start) {
                    end = start + width;
                }
            }
            if (tableWidth > 0) { // print with border
                System.out.println(String.format("| %-" + tableWidth + "s |", text.substring(start, end)));
            } else {
                System.out.println(text.substring(start, end));
            }

            start = end;
            while (start < text.length() && Character.isWhitespace(text.charAt(start))) {
                start++;
            }
        }
    }

    //@@author dextboy
    public static void printHyphens() {
        System.out.println("_____________________________________________________________");
    }

    public static void printExit() {
        System.out.println("Bye. Enjoy your studies!");
    }

}
