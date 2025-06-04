package patterns.factory.realisation.two;

public class Main {
    public static void main(String[] args) {
        Dialog dialog = getDialog("Windows");
        dialog.renderWindow();// клієнт не знає
        // який клас буде викликано і створено
    }

    public static Dialog getDialog(String os) {
        if ("windows".equalsIgnoreCase(os)) {
            return new WinOsDialog();
        } else {
            return new MacOsDialog();
        }
    }
}