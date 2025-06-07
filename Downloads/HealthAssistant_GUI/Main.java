import model.MedicalProvider;
import service.HealthService;

import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HealthService service = new HealthService();
        String[] symptomsList = service.getSymptoms();

        StringBuilder message = new StringBuilder("Select your symptoms (enter numbers separated by commas):\n");
        for (int i = 0; i < symptomsList.length; i++) {
            message.append((i + 1)).append(". ").append(symptomsList[i]).append("\n");
        }

        String input = JOptionPane.showInputDialog(null, message.toString(), "Ethiopian Health Assistant", JOptionPane.QUESTION_MESSAGE);
        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No symptoms selected. Exiting.");
            return;
        }

        String[] choices = input.split(",");
        List<String> selectedSymptoms = service.getSelectedSymptoms(choices);

        String diagnosis = service.getDiagnosis(selectedSymptoms);

        List<MedicalProvider> providers = service.getProviders(diagnosis);
        StringBuilder result = new StringBuilder("Diagnosis: " + diagnosis + "\n\nSuggested doctors and hospitals in Ethiopia:\n");
        for (MedicalProvider provider : providers) {
            result.append("- ").append(provider).append("\n");
        }

        JOptionPane.showMessageDialog(null, result.toString(), "Diagnosis Result", JOptionPane.INFORMATION_MESSAGE);
    }
}