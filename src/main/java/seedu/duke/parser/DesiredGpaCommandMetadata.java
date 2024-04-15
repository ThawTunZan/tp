package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.DesiredGpaCommand;
import seedu.duke.exceptions.InvalidGpaException;

import java.util.Map;

public class DesiredGpaCommandMetadata extends CommandMetadata{
    private static final String DESIREDGPA_KEYWORD = "desiredgpa";
    private static final String[] DESIREDGPA_ARGUMENTS = {"dgpa"};

    public DesiredGpaCommandMetadata() {
        super(DESIREDGPA_KEYWORD, DESIREDGPA_ARGUMENTS);
    }

    public static void validateGPA(double gpa) throws InvalidGpaException {
        if (gpa > 5 || gpa <0) {
            throw new InvalidGpaException("GPA must be within 0 to 5.");
        }
    }

    @Override
    protected Command createCommandInstance(Map<String, String> args) {
        float dgpa = Float.parseFloat(args.get("dgpa"));

        return new DesiredGpaCommand(dgpa);
    }
}
