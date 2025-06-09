package src.UI;

import src.UI.Views.*;
import src.logic.Diagnosis;
import logic.Reminder;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HealthSystemUI extends JFrame {
    private final Diagnosis diagnosis;


    private SymptomSelectionView symptomSelectionView;
    private DiagnosisResultsView diagnosisResultsView;
    private DoctorInfoView doctorInfoView;
    private MedicationReminderView medicationReminderView;

    public HealthSystemUI(Diagnosis engine) {
        this.diagnosis = engine;

        setTitle("Health Assistant");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buildUI();
    }

    private void buildUI() {
        JTabbedPane tPane = new JTabbedPane();

        JPanel symptomCheckerPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Health Assistant - Symptom Checker", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        symptomCheckerPanel.add(title, BorderLayout.NORTH);

    
        symptomSelectionView = new SymptomSelectionView(diagnosis);
        doctorInfoView = new DoctorInfoView(diagnosis);
        diagnosisResultsView = new DiagnosisResultsView(diagnosis, doctorInfoView);

        JScrollPane symptomScroll = new JScrollPane(symptomSelectionView);
        symptomScroll.setBorder(BorderFactory.createTitledBorder("Select Symptoms"));

        JScrollPane diagnosisScroll = new JScrollPane(diagnosisResultsView);
        diagnosisScroll.setBorder(BorderFactory.createTitledBorder("Possible Diagnoses"));

        JScrollPane infoScroll = new JScrollPane(doctorInfoView);

        JPanel stackedPanel = new JPanel(new GridLayout(2, 1));
        stackedPanel.add(diagnosisScroll);
        stackedPanel.add(infoScroll);

        JButton diagnoseBtn = new JButton("Diagnose");
        diagnoseBtn.setToolTipText("Analyze selected symptoms");
        diagnoseBtn.addActionListener(e -> runDiagnosis());

        JPanel rightPanel = new JPanel(new BorderLayout(5, 5));
        rightPanel.add(diagnoseBtn, BorderLayout.NORTH);
        rightPanel.add(stackedPanel, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, symptomScroll, rightPanel);
        splitPane.setResizeWeight(0.5);
        symptomCheckerPanel.add(splitPane, BorderLayout.CENTER);

        tPane.addTab("Symptom Checker", symptomCheckerPanel);

        //second tab
        medicationReminderView = new MedicationReminderView();
        tPane.addTab("Medication Reminder", medicationReminderView);

        add(tPane, BorderLayout.CENTER);
    }

    private void runDiagnosis() {
        List<String> selectedSymptoms = symptomSelectionView.getSelectedSymptoms();
        diagnosisResultsView.updateResults(selectedSymptoms);
    }
}
