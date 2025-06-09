package src;

import src.UI.HealthSystemUI;
import src.logic.Diagnosis;
import logic.ReminderDialog;

public class Main {
    public static void main(String[] args) throws Exception {
        Diagnosis diagnosis = new Diagnosis();
        new HealthSystemUI(diagnosis).setVisible(true);
    }
}
