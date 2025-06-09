package src.UI.Views;

import model.DoctorsDetails;
import src.logic.Diagnosis;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class DoctorInfoView extends JPanel {
    private final Diagnosis diagnosis;

    public DoctorInfoView(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(255, 230, 230));
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.RED, 2), "Doctor/Hospital Info"));
    }

    public void showDoctorInfo(String disease) {
        removeAll();

        JLabel title = new JLabel("For " + disease + ", consider visiting:");
        title.setFont(new Font("Arial", Font.BOLD, 14));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(title);

        DoctorsDetails<String, String> doctorsDetail = diagnosis.getDoctorsDetail(disease);
        Set<String> seenHospitals = new HashSet<>();

        for (String doc : doctorsDetail.doctorNames) {
            JLabel docLabel = new JLabel(doc);
            docLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
            docLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            add(docLabel);
        }

        add(Box.createVerticalStrut(10));

        for (String hospital : doctorsDetail.hospitals) {
            if (seenHospitals.contains(hospital)) continue;
            seenHospitals.add(hospital);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.setBackground(getBackground());

            JLabel hospitalLabel = new JLabel(hospital);

            panel.add(hospitalLabel);
            panel.add(Box.createVerticalStrut(3));

            add(panel);
            add(Box.createVerticalStrut(5));
        }

        revalidate();
        repaint();
    }


}
