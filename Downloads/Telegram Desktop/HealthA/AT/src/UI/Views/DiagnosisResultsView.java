package src.UI.Views;

import src.logic.Diagnosis;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class DiagnosisResultsView extends JPanel {
    private final Diagnosis diagnosis;
    private final DoctorInfoView doctorInfoView;

    public DiagnosisResultsView(Diagnosis diagnosis, DoctorInfoView doctorInfoView) {
        this.diagnosis = diagnosis;
        this.doctorInfoView = doctorInfoView;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void updateResults(List<String> selectedSymptoms) {
        this.removeAll();

        Map<String, Double> results = diagnosis.diagnose(selectedSymptoms);
        if (results.isEmpty()) {
            add(new JLabel("No potential diseases found."));
        } else {
            results.entrySet().stream()
                    .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                    .forEach(entry -> {
                        String disease = entry.getKey();
                        JLabel label = new JLabel(disease + " - " + String.format("%.0f%%", entry.getValue() * 100));
                        
                        label.setForeground(Color.BLUE.darker());
                        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        label.setFont(new Font("SansSerif", Font.PLAIN, 13));
                        label.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                doctorInfoView.showDoctorInfo(disease);
                            }
                        });
                        this.add(label);
                    });
        }

        revalidate();
        repaint();
    }
}
