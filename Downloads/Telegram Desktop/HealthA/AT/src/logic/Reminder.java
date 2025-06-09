package src.logic;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.*;

public class Reminder {
    public String name;
    public String date;
    public String time;
    public String description;

    private ScheduledFuture<?> scheduledTask;

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4);

    public Reminder(String name, String date, String time, String description) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.description = description;

        schedule();
    }

    public LocalDateTime getTriggerDateTime() {
        return LocalDateTime.of(
                LocalDate.parse(date),
                LocalTime.parse(time)
        );
    }

    public void schedule() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime triggerTime = getTriggerDateTime();
        Duration delay = Duration.between(now, triggerTime);

        long delayInMillis = delay.toMillis();

        scheduledTask = scheduler.schedule(() -> notifyUser(), delayInMillis, TimeUnit.MILLISECONDS);
    }


    private void notifyUser() {
        ReminderDialog.showReminder(name);
    }

    public void cancel() {
        if (scheduledTask != null && !scheduledTask.isDone()) {
            scheduledTask.cancel(false); 
        } 
    }


    @Override
    public String toString() {
        return name + " - " + time + " " + date;
    }
}
