package seedu.duke.enums;

import java.util.ArrayList;
import java.util.List;

public enum CEGModules {
    ES2631(4, null),
    CS1010(4, null),
    GEA1000(4, null),
    DTK1234(4, null),
    EG1311(4, null),
    IE2141(4, null),
    EE2211(4, null),
    CDE2501(4, null),
    CDE2000(4, null),
    PF1101(4, null),
    CG4002(8, null),
    MA1511(2, null),
    MA1512(2, null),
    MA1508E(4, null),
    EG2401A(2, null),
    EG3611A(10, new ArrayList<>(List.of("CP3880"))),
    CP3880(12, new ArrayList<>(List.of("EG3611A"))),
    CG1111A(4, null),
    CG2111A(4, null),
    CS1231(4, null),
    CG2023(4, null),
    CG2027(2, null),
    CG2028(2, null),
    CG2271(4, null),
    CS2040C(4, null),
    CS2113(4, null),
    EE2026(4, null),
    EE4204(4, null);

    private final int moduleMC;
    private final ArrayList<String> equivalent;

    CEGModules(int moduleMC, ArrayList<String> equivalent) {
        this.moduleMC = moduleMC;
        this.equivalent = equivalent;
    }

    public int getModuleMC() {
        return moduleMC;
    }

    public ArrayList<String> getEquivalent() {
        return equivalent;
    }

    /**
     * Maps a module code string to its corresponding CEG module enumeration.
     *
     * @param moduleCode The module code string to map.
     * @return The CEG module enumeration corresponding to the provided module code.
     * @throws IllegalArgumentException if no matching module code is found in the CEGModules enumeration.
     */
    public static CEGModules mapStringToEnum(String moduleCode) {
        for (CEGModules cegModule : CEGModules.values()) {
            if (cegModule.name().equalsIgnoreCase(moduleCode)) {
                return cegModule;
            }
        }
        throw new IllegalArgumentException("No module code " + moduleCode + " found in CEGModules");
    }
}
