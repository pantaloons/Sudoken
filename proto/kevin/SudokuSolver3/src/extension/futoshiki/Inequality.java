package extension.futoshiki;

import java.text.ParseException;
import java.util.InputMismatchException;

public enum Inequality {
    LESS_THAN(-1, "lt"), 
    GREATER_THAN(1, "gt");
    
    int comparisonResult;
    String stringForm;
    
    private Inequality(int comparisonResult, String stringForm) {
        this.comparisonResult = comparisonResult;
        this.stringForm = stringForm;
    }

    public boolean isTrue(Integer a, Integer b) {
        boolean isTrue = a.compareTo(b) == comparisonResult;
        return isTrue;
    }
    
    
    public String getStringForm() {
        return stringForm;
    }
    
    public static Inequality fromString(String stringForm) {
        Inequality parsedInequality = null;
        for(Inequality inequality : Inequality.values()) {
            if(inequality.getStringForm() == stringForm) {
                parsedInequality = inequality;
                break;
            }
        }
        if(parsedInequality == null) {
            throw new InputMismatchException("No inequalities match the string input.");
        }
        
        return parsedInequality;        
    }
}
