package src.UI.Views;

import javax.swing.*;
import java.awt.*;

public class ReminderDescriptionView extends JScrollPane {
    private final JLabel descriptionLabel;

    public ReminderDescriptionView() {
        
        descriptionLabel = new JLabel();
        descriptionLabel.setVerticalAlignment(SwingConstants.TOP);
        descriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);
        descriptionLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        descriptionLabel.setOpaque(true);
        descriptionLabel.setBackground(Color.WHITE);

        
        setDescription("");

      
        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.add(descriptionLabel, BorderLayout.NORTH);
        jPanel.setBackground(new Color(255, 230, 230));

        setViewportView(jPanel);
        getVerticalScrollBar().setUnitIncrement(16);
    }

    public void setDescription(String text) {
        if (text == null || text.isEmpty()) {
            descriptionLabel.setText("No description selected.");
        } else {
     
            descriptionLabel.setText(text);
        }
    }
}
