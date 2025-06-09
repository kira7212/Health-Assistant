package src.UI.Views;

import src.logic.Diagnosis;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

public class SymptomSelectionView extends JPanel {
    private final Map<String, JCheckBox> symptomCheckboxes = new HashMap<>();

    public SymptomSelectionView(Diagnosis diagnosis) {
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Map<String, List<String>> categorizedSymptoms = diagnosis.getCategorizedSymptoms();

        for (Map.Entry<String, List<String>> entry : categorizedSymptoms.entrySet()) {
            String category = entry.getKey();
            List<String> symptoms = entry.getValue();

            JPanel categoryPanel = new JPanel();
            categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.Y_AXIS));

            TitledBorder border = BorderFactory.createTitledBorder(category);
            border.setTitleFont(new Font("Arial Bold", Font.BOLD, 14));
            Border margin = BorderFactory.createEmptyBorder(10, 0, 0, 0);
            categoryPanel.setBorder(BorderFactory.createCompoundBorder(margin, border));

            for (String symptom : symptoms) {
                JCheckBox box = new JCheckBox(symptom);
                symptomCheckboxes.put(symptom, box);
                categoryPanel.add(box);
            }

            this.add(categoryPanel);
        }
    }

    public List<String> getSelectedSymptoms() {
        List<String> selected = new ArrayList<>();
        for (Map.Entry<String, JCheckBox> entry : symptomCheckboxes.entrySet()) {
            if (entry.getValue().isSelected()) {
                selected.add(entry.getKey());
            }
        }
        return selected;
    }
}
