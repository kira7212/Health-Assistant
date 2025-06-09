package src.logic;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ReminderDialog {
    private static Clip clip;

    public static void showReminder(String reminderName) {
        
        playAlarm();

        JDialog dialog = new JDialog((Frame) null, "Reminder", true);
        dialog.setLayout(new BorderLayout());
        dialog.add(new JLabel(reminderName), BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> {
            stopAlarm();
            dialog.dispose();
        });

        dialog.add(closeButton, BorderLayout.SOUTH);
        dialog.setSize(250, 120);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private static void playAlarm() {
        try {
            URL resource = ReminderDialog.class.getClassLoader().getResource("asset/audio/alarm.wav");
   
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(resource);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); 
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Failed to play audio: " + e.getMessage());
        }
    }

    private static void stopAlarm() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}
