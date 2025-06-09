package src.UI.Views;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import logic.Reminder;

public class MedicationReminderView extends JPanel {
    private final DefaultListModel<Reminder> reminderModel = new DefaultListModel<>();
    private final JList<Reminder> reminderList = new JList<>(reminderModel);
    private final ReminderDescriptionView descriptionView = new ReminderDescriptionView();

    public MedicationReminderView() {
        setLayout(new BorderLayout());

        reminderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        reminderList.addListSelectionListener(e -> {
            Reminder selected = reminderList.getSelectedValue();
            descriptionView.setDescription(selected != null ? selected.description : "");
        });

        JScrollPane listScroll = new JScrollPane(reminderList);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScroll, descriptionView);
        splitPane.setResizeWeight(0.4);

        JPanel buttons = new JPanel(new FlowLayout());
        JButton add = new JButton("Add");
        JButton edit = new JButton("Edit");
        JButton remove = new JButton("Remove");

        buttons.add(add);
        buttons.add(edit);
        buttons.add(remove);


        add.addActionListener(e -> {
            Reminder reminder = showReminderDialog(null);
            if (reminder != null) reminderModel.addElement(reminder);
        });

        edit.addActionListener(e -> {
            int index = reminderList.getSelectedIndex();
            if (index != -1) {
                Reminder existing = reminderModel.get(index);
                Reminder updated = showReminderDialog(existing);
                descriptionView.setDescription(updated.description);
                if (updated != null) reminderModel.set(index, updated);
            }
        });

        remove.addActionListener(e -> {
            int index = reminderList.getSelectedIndex();
            if (index != -1) {
                Reminder selectedReminder = reminderModel.get(index);
                selectedReminder.cancel();

                reminderModel.remove(index);
                descriptionView.setDescription("");
            }
        });

        add(splitPane, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
    }

    private Reminder showReminderDialog(Reminder existing) {

    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    JTextField nameField = new JTextField(20);
    JTextField dateField = new JTextField(dateFormatter.format(now), 10);
    JTextField timeField = new JTextField(timeFormatter.format(now), 5);

    JTextArea descArea = new JTextArea(4, 20);
    descArea.setLineWrap(true);
    descArea.setWrapStyleWord(true);
    JScrollPane descScroll = new JScrollPane(descArea);

    if (existing != null) {
        nameField.setText(existing.name);
        dateField.setText(existing.date);
        timeField.setText(existing.time);
        descArea.setText(existing.description);
    }

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    panel.add(new JLabel("Name:"));
    panel.add(nameField);
    panel.add(Box.createVerticalStrut(10));

    panel.add(new JLabel("Date (YYYY-MM-DD):"));
    panel.add(dateField);
    panel.add(Box.createVerticalStrut(10));

    panel.add(new JLabel("Time (HH:MM):"));
    panel.add(timeField);
    panel.add(Box.createVerticalStrut(10));

    panel.add(new JLabel("Description:"));
    panel.add(descScroll);

    int result = JOptionPane.showConfirmDialog(this, panel,
            existing == null ? "Add Reminder" : "Edit Reminder",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (result == JOptionPane.OK_OPTION) {
        String name = nameField.getText().trim();
        String date = dateField.getText().trim();
        String time = timeField.getText().trim();
        String desc = descArea.getText().trim();

        if (name.isEmpty() || date.isEmpty() || time.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name, date, and time are required.");
            return null;
        }

        return new Reminder(name, date, time, desc);
    }

    return null;
}


}
