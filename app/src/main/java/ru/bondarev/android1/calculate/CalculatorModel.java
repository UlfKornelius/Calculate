package ru.bondarev.android1.calculate;

public class CalculatorModel {

    private float firstArg;
    private float secondArg;
    private StringBuilder inputStr = new StringBuilder();



    private int actionSelected;

    private State state;

    private enum State {
        firstArgInput,
        operationSelected,
        secondArgInput,
        resultShow
    }

    public CalculatorModel() {
        state = State.firstArgInput;
    }

    private boolean adminKey = false;


    public void onAdminPressed(int adminId) {
        if (adminId == R.id.buttonC && adminKey == true) {
            inputStr.setLength(0);
            inputStr.append("0");
            state = State.firstArgInput;
        }

        else if (adminId == R.id.buttonOn) {
            adminKey = true;
            inputStr = new StringBuilder();
            inputStr.setLength(0);
            inputStr.append("0");
            state = State.firstArgInput;

        }

        else if (adminId == R.id.buttonOff) {
            adminKey = false;
            state = State.firstArgInput;
            inputStr.setLength(0);

        }

    }

    public void onNumPressed(int buttonId) {

            if (state == State.resultShow) {
                state = State.firstArgInput;
                inputStr.setLength(0);
            }

            if (state == State.operationSelected) {
                state = State.secondArgInput;
                inputStr.setLength(0);
            }

            if (inputStr.length() < 15 && adminKey == true) {

                switch (buttonId) {
                    case R.id.button0:
                        inputStr.append("0");
                        break;
                    case R.id.button1:
                        inputStr.append("1");
                        break;
                    case R.id.button2:
                        inputStr.append("2");
                        break;
                    case R.id.button3:
                        inputStr.append("3");
                        break;
                    case R.id.button4:
                        inputStr.append("4");
                        break;
                    case R.id.button5:
                        inputStr.append("5");
                        break;
                    case R.id.button6:
                        inputStr.append("6");
                        break;
                    case R.id.button7:
                        inputStr.append("7");
                        break;
                    case R.id.button8:
                        inputStr.append("8");
                        break;
                    case R.id.button9:
                        inputStr.append("9");
                        break;
                    case R.id.buttonPt:
                        inputStr.append(".");
                        break;
                }
            }


    }





    public void onActionPressed(int actionId) {
        if (actionId == R.id.buttonResult && state == State.secondArgInput && inputStr.length() > 0 ) {
            secondArg = Float.parseFloat(inputStr.toString());
            state = State.resultShow;
            inputStr.setLength(0);
            switch (actionSelected) {
                case R.id.buttonPlus:
                    inputStr.append(firstArg + secondArg);
                    break;
                case R.id.buttonMinus:
                    inputStr.append(firstArg - secondArg);
                    break;
                case R.id.buttonMultiplication:
                    inputStr.append(firstArg * secondArg);
                    break;
                case R.id.buttonDivision:
                    inputStr.append(firstArg / secondArg);
                    break;

            }

        } else if (inputStr.length() > 0 && state == State.firstArgInput) {
            firstArg = Float.parseFloat(inputStr.toString());
            state = State.operationSelected;
            actionSelected = actionId;
        }


    }

    public String getText() {
        StringBuilder str = new StringBuilder();
        switch (state) {

            case operationSelected:
                return str.append(firstArg).append(' ')
                        .append(getOperationChar())
                        .toString();
            case secondArgInput:
                return str.append(firstArg).append(' ')
                        .append(getOperationChar())
                        .append(' ')
                        .append(inputStr)
                        .toString();
            case resultShow:
                return str.append(firstArg).append(' ')
                        .append(getOperationChar())
                        .append(' ')
                        .append(secondArg)
                        .append(" = ")
                        .append(inputStr.toString())
                        .toString();
            default:
                return inputStr.toString();
        }
    }

    private char getOperationChar() {
        switch (actionSelected) {
            case R.id.buttonPlus:
                return '+';
            case R.id.buttonMinus:
                return '-';
            case R.id.buttonMultiplication:
                return '*';
            case R.id.buttonDivision:
            default:
                return '/';

        }
    }

    public void reset() {
        state = State.firstArgInput;
        inputStr.setLength(0);
    }
}
