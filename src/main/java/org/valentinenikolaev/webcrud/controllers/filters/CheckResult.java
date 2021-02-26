package org.valentinenikolaev.webcrud.controllers.filters;

public class CheckResult {
    private boolean isAllConditionsValid;
    private String errorMessage;

    public CheckResult() {
    }

    public CheckResult(boolean isAllConditionsValid) {
        this.isAllConditionsValid = isAllConditionsValid;
        errorMessage = "";
    }

    public CheckResult(String errorMessage) {
        this.errorMessage = errorMessage;
        isAllConditionsValid = false;
    }

    public CheckResult(boolean isAllConditionsValid, String errorMessage) {
        this.isAllConditionsValid = isAllConditionsValid;
        this.errorMessage = errorMessage;
    }

    public boolean isAllConditionsValid() {
        return isAllConditionsValid;
    }

    public void setAllConditionsValid(boolean allConditionsValid) {
        isAllConditionsValid = allConditionsValid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
