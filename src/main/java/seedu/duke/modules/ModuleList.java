package seedu.duke.modules;

import seedu.duke.enums.CEGModules;
import seedu.duke.exceptions.GpaNullException;
import seedu.duke.exceptions.InvalidGpaException;
import seedu.duke.exceptions.ModuleException;
import seedu.duke.exceptions.ModuleNotFoundException;
import seedu.duke.ui.Ui;
import seedu.duke.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.logging.Level;

import static seedu.duke.FAP.LOGGER;

/**
 * Manages a list of modules for a student. Allows for addition, removal, and querying of modules.
 */
public class ModuleList {
    private static final float DEFAULT_MC_REQUIRED = 160;

    protected ArrayList<Module> moduleList;
    private float moduleCreditsByGraduation = DEFAULT_MC_REQUIRED;
    private double currentGPA;
    private float moduleCreditsCountedToGPA;

    /**
     * Constructs an empty list of modules.
     */
    public ModuleList() {
        this.moduleList = new ArrayList<Module>();
    }

    /**
     * Retrieves a module by its course code.
     *
     * @param courseCode The course code of the module to be retrieved.
     * @return The module with the specified course code.
     * @throws ModuleNotFoundException If the module is not found.
     */
    public Module getModule(String courseCode) throws ModuleNotFoundException {
        if (courseCode == null || courseCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Course code cannot be null or empty.");
        }
        courseCode = courseCode.toUpperCase(); // Convert once and reuse, improving efficiency

        for (Module module : moduleList) {
            if (module.getModuleCode().equals(courseCode)) {
                return module;
            }
        }
        throw new ModuleNotFoundException("Module " + courseCode + " not found!");
    }

    public ArrayList<Module> getTakenModuleList() {
        return moduleList;
    }

    /**
     * Adds a new module to the list after validation.
     *
     * @param module The module to be added.
     * @throws IllegalArgumentException If the module is null.
     */
    public void addModule(Module module) {
        if (module == null) {
            throw new IllegalArgumentException("Module cannot be null.");
        }
        moduleList.add(module);
        System.out.println("Added the new module: " + module.getModuleCode() + "\n" + module.getModuleDescription());
    }

    public void add(Module module) {
        if (module == null) {
            throw new IllegalArgumentException("Module cannot be null.");
        }
        moduleList.add(module);
    }

    public void removeModule(Module module) {
        assert module != null : "Module cannot be null";
        // The remove operation returns false if the item was not found
        boolean removed = moduleList.remove(module);
        if (!removed) {
            System.out.println("Module not found in either list.");
        }
    }

    /**
     * Changes the grade of a module.
     *
     * @param moduleCode The module code of the module.
     * @param grade      The new grade for the module.
     */
    public void changeModuleGrade(String moduleCode, String grade) {
        if (moduleCode == null || moduleCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Module code cannot be null or empty.");
        }
        try {
            Module toChange = getModule(moduleCode);
            toChange.setModuleGrade(grade);
            System.out.println("Grade for " + moduleCode + " updated to " + grade);
            assert toChange.getModuleGrade().equals(grade) : "Grade is not updated successfully";

        } catch (ModuleNotFoundException e) {
            System.out.println("Module not found in list");
        } catch (ModuleException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Calculates the current GPA based on the grades of taken modules.
     *
     * @throws GpaNullException If there are no countable grades taken.
     */
    public void tallyGPA() throws GpaNullException {
        float totalMC = 0;
        double sumOfGPA = 0;

        for (Module module : moduleList) {
            if (module.getModuleGrade() == null || module.getModuleGrade().equals("CS") ||
                    module.getModuleGrade().equals("S")) {
                continue;
            }
            totalMC += module.getModuleMC();
            sumOfGPA += module.getGradeNumber() * module.getModuleMC();
        }
        this.moduleCreditsCountedToGPA = totalMC;
        if (totalMC == 0) {
            LOGGER.log(Level.INFO, "No modules with grades available to tabulate GPA.");
            this.currentGPA = 0;
            throw new GpaNullException("No countable grades present to tally.");
        }
        this.currentGPA = sumOfGPA / (double) totalMC;
    }

    /**
     * Retrieves the current GPA.
     *
     * @return The current GPA.
     */
    public double getCurrentGPA() {
        return this.currentGPA;
    }

    public Map<Integer, ArrayList<Module>> groupModulesBySemester() {
        Map<Integer, ArrayList<Module>> moduleBySemMap = new HashMap<>();
        for (int i = 1; i <= User.MAX_NUM_SEMESTERS; i++) {
            moduleBySemMap.put(i, new ArrayList<>());
        }

        for (Module module : moduleList) {
            moduleBySemMap.get(module.getModuleDate()).add(module);
        }

        return moduleBySemMap;
    }

    /**
     * Checks if a module with the given module code exists in the list of CEG modules.
     * This method determines existence based on direct match or equivalence with any CEG module.
     *
     * @param moduleCode The module code to search for.
     * @return {@code true} if a module with the given code exists or equivalent CEGModules, {@code false} otherwise.
     */
    public boolean containsCEGModuleInList(String moduleCode) {
        for (Module takenModule : moduleList) {
            ArrayList<String> equivalentList;
            try {
                equivalentList = CEGModules.mapStringToEnum(takenModule.getModuleCode()).getEquivalent();
            } catch (IllegalArgumentException e) {
                // Not a CEG specific module
                continue;
            }
            boolean hasEquivalent = equivalentList != null && equivalentList.contains(moduleCode);
            if (hasEquivalent || moduleCode.equals(takenModule.getModuleCode())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves a list of CEG modules that are yet to be completed based on the modules already taken.
     * This method identifies the missing modules needed for completion.
     *
     * @return An {@code ArrayList} containing the names of CEG modules that are yet to be completed.
     */
    public ArrayList<String> getModulesToComplete() {
        ArrayList<String> modulesToComplete = new ArrayList<>();
        for (CEGModules cegModule : CEGModules.values()) {
            if (!containsCEGModuleInList(cegModule.name())) {
                modulesToComplete.add(cegModule.name());
            }
        }
        return modulesToComplete;
    }

    public float calculateTotalMCs() {
        return calculateMCs(module -> true);
    }

    public float calculateTakenMCs() {
        return calculateMCs(Module::getModuleStatus);
    }

    private float calculateMCs(Predicate<Module> predicate) {
        float totalMCs = 0;
        for (Module module : moduleList) {
            if (predicate.test(module)) {
                totalMCs += module.getModuleMC();
            }
        }
        return totalMCs;
    }

    public void clearModules() {
        moduleList.clear();
    }

    /**
     * Calculates the grades needed to achieve a desired GPA.
     *
     * @param desiredGPA The desired GPA.
     * @throws InvalidGpaException If the desired GPA is not achievable, or if user has inputted more than 160 MCs
     */
    public void calcGradesExpectations(double desiredGPA) throws InvalidGpaException {
        tallyGPAForCalcGradesExpectations();
        float moduleCreditsTaken = getValidCreditsTaken();
        float moduleCreditsNotTaken = moduleCreditsByGraduation - moduleCreditsTaken;
        float totalModuleCreditsCountedToGPA = moduleCreditsNotTaken + moduleCreditsCountedToGPA;
        double requiredFutureAverageGrade = (desiredGPA * totalModuleCreditsCountedToGPA -
                currentGPA * moduleCreditsCountedToGPA) /
                moduleCreditsNotTaken;
        validateFutureAverageGrade(requiredFutureAverageGrade);
        double upperBound = getFutureAverageGradeUpperBound(requiredFutureAverageGrade);
        double lowerBound = getFutureAverageGradeLowerBound(requiredFutureAverageGrade);
        int upperBoundGradeNeeded = 0;
        int lowerBoundGradeNeeded = 0;
        double mockGPA = lowerBound;
        for (float i = moduleCreditsNotTaken; i > 0; i -= 4) {
            if (mockGPA < requiredFutureAverageGrade) {
                upperBoundGradeNeeded += 1;
            } else {
                lowerBoundGradeNeeded += 1;
            }
            mockGPA = calculateMockGPA(upperBound, upperBoundGradeNeeded, lowerBound, lowerBoundGradeNeeded);
        }
        if (mockGPA < requiredFutureAverageGrade) {
            lowerBoundGradeNeeded -= 1;
            upperBoundGradeNeeded += 1;
            mockGPA = calculateMockGPA(upperBound, upperBoundGradeNeeded, lowerBound, lowerBoundGradeNeeded);
        }
        totalModuleCreditsCountedToGPA = moduleCreditsCountedToGPA +
                (lowerBoundGradeNeeded + upperBoundGradeNeeded) * 4;
        double acquiredGPA = (currentGPA * moduleCreditsCountedToGPA + mockGPA *
                (4 * (upperBoundGradeNeeded + lowerBoundGradeNeeded))) / totalModuleCreditsCountedToGPA;
        Ui.printGradeExpectations(this, desiredGPA, acquiredGPA, upperBoundGradeNeeded,
                upperBound, lowerBoundGradeNeeded, lowerBound, moduleCreditsNotTaken);
    }

    /**
     * Checks if the sum of module credits taken are more than 160. Throws an exception if yes.
     *
     * @return The current sum of module credits taken.
     * @throws InvalidGpaException If the sum exceeds 160.
     */
    private float getValidCreditsTaken() throws InvalidGpaException {
        if (getModuleCreditsTaken() >= 160) {
            throw new InvalidGpaException("You have already taken 160 MCs or more");
        }
        return getModuleCreditsTaken();
    }

    /**
     * Tally GPA for calculating grade expectations and handles exceptions if no countable grades are present.
     */
    private void tallyGPAForCalcGradesExpectations() {
        try {
            tallyGPA();
        } catch (GpaNullException e) {
            this.currentGPA = 0;
            this.moduleCreditsCountedToGPA = 0;
        }
    }

    /**
     * Calculates the mock GPA, which represents the GPA only from future modules
     *
     * @param upperBound            The upper bound of the future average grade.
     * @param upperBoundGradeNeeded The number of upper bound grades needed.
     * @param lowerBound            The lower bound of the future average grade.
     * @param lowerBoundGradeNeeded The number of lower bound grades needed.
     * @return The calculated mock GPA.
     */
    private static double calculateMockGPA(double upperBound, int upperBoundGradeNeeded,
                                           double lowerBound, int lowerBoundGradeNeeded) {
        return (upperBound * upperBoundGradeNeeded + lowerBound * lowerBoundGradeNeeded) /
                (upperBoundGradeNeeded + lowerBoundGradeNeeded);
    }

    /**
     * Verify if the future average grade required is between 0 and 5, which means desired grade is attainable.
     * Else, throw an exception.
     *
     * @param requiredFutureAverageGrade The required future average grade.
     * @throws InvalidGpaException If the required future average grade is out of range.
     */
    private static void validateFutureAverageGrade(double requiredFutureAverageGrade) throws InvalidGpaException {
        if (requiredFutureAverageGrade > 5) {
            throw new InvalidGpaException("Your current GPA is too low to achieve desired GPA :(");
        }
        if (requiredFutureAverageGrade < 0) {
            throw new InvalidGpaException("Your current GPA is too high to achieve desired GPA");
        }
    }

    /**
     * Find the upper bound of the grade based on the required future average grade.
     *
     * @param requiredFutureAverageGrade The required future average grade to achieve desired GPA.
     * @return The upper bound of the future average grade.
     */
    public double getFutureAverageGradeUpperBound(double requiredFutureAverageGrade) {
        if (requiredFutureAverageGrade > 4.5) {
            return 5.0;
        } else if (requiredFutureAverageGrade > 4.0) {
            return 4.5;
        } else if (requiredFutureAverageGrade > 3.5) {
            return 4.0;
        } else if (requiredFutureAverageGrade > 3.0) {
            return 3.5;
        } else if (requiredFutureAverageGrade > 2.5) {
            return 3.0;
        } else if (requiredFutureAverageGrade > 2.0) {
            return 2.5;
        } else if (requiredFutureAverageGrade > 1.5) {
            return 2.0;
        } else if (requiredFutureAverageGrade > 1.0) {
            return 1.5;
        } else if (requiredFutureAverageGrade > 0) {
            return 1.0;
        }
        return 0;
    }

    /**
     * Find the lower bound of the grade based on the required future average grade.
     *
     * @param requiredFutureAverageGrade The required future average grade to achieve desired GPA.
     * @return The lower bound of the future average grade.
     */
    public double getFutureAverageGradeLowerBound(double requiredFutureAverageGrade) {
        if (requiredFutureAverageGrade < 1.0) {
            return 0;
        } else if (requiredFutureAverageGrade < 1.5) {
            return 1.0;
        } else if (requiredFutureAverageGrade < 2.0) {
            return 1.5;
        } else if (requiredFutureAverageGrade < 2.5) {
            return 2.0;
        } else if (requiredFutureAverageGrade < 3.0) {
            return 2.5;
        } else if (requiredFutureAverageGrade < 3.5) {
            return 3.0;
        } else if (requiredFutureAverageGrade < 4.0) {
            return 3.5;
        } else if (requiredFutureAverageGrade < 4.5) {
            return 4.0;
        } else if (requiredFutureAverageGrade < 5) {
            return 4.5;
        }
        return 5;
    }

    /**
     * Converts a numeric grade to its corresponding letter grade.
     *
     * @param grade The numeric grade to convert.
     * @return The corresponding letter grade.
     */
    public String numberToGrade(double grade) {
        if (grade == 5.0) {
            return "A";
        } else if (grade == 4.5) {
            return "A-";
        } else if (grade == 4.0) {
            return "B+";
        } else if (grade == 3.5) {
            return "B";
        } else if (grade == 3.0) {
            return "B-";
        } else if (grade == 2.5) {
            return "C+";
        } else if (grade == 2.0) {
            return "C";
        } else if (grade == 1.5) {
            return "D+";
        } else if (grade == 1.0) {
            return "D";
        } else if (grade == 0) {
            return "F";
        }
        return "non-vaLid number";
    }

    /**
     * Calculates the total credits taken. Only account for modules that are taken and is assigned a grade.
     *
     * @return The total credits taken.
     */
    private float getModuleCreditsTaken() {
        float moduleCreditsTaken = 0;
        for (Module module : moduleList) {
            if (module.getModuleStatus() && !module.gradeIsNull()) {
                moduleCreditsTaken += module.getModuleMC();
            }
        }
        return moduleCreditsTaken;
    }
}
