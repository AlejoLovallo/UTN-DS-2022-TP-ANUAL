package Domain.Cron;

import org.quartz.*;

public class LogSchedulerListenerImpl implements SchedulerListener {

    private final Scheduler scheduler;

    public LogSchedulerListenerImpl(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void jobScheduled(Trigger trigger) {
        System.out.println("Job scheduled: " + trigger.getKey().getName());
    }

    @Override
    public void jobUnscheduled(TriggerKey triggerKey) {
        System.out.println("Job Unscheduled: " + triggerKey.getName());
    }

    @Override
    public void triggerFinalized(Trigger trigger) {
        System.out.println("Job trigger finalized: " + trigger.getKey().getName());
    }

    @Override
    public void triggerPaused(TriggerKey triggerKey) {
        System.out.println("Job trigger paused: " + triggerKey.getName());
    }

    @Override
    public void triggersPaused(String triggerGroup) {
        System.out.println("Job triggers paused for trigger group: "
                + triggerGroup);
    }

    @Override
    public void triggerResumed(TriggerKey triggerKey) {
        System.out.println("Job triggers resumed for trigger: " + triggerKey);
    }

    @Override
    public void triggersResumed(String triggerGroup) {
        System.out.println("Job triggers resumed for trigger group: " + triggerGroup);
    }

    @Override
    public void jobAdded(JobDetail jobDetail) {
        System.out.println("Job added: " + jobDetail.getKey().getName());
    }

    @Override
    public void jobDeleted(JobKey jobKey) {
        System.out.println("Job deleted: " + jobKey.getName());
    }

    @Override
    public void jobPaused(JobKey jobKey) {
        System.out.println("Jobs paused for job: " + jobKey);
    }

    @Override
    public void jobsPaused(String jobGroup) {
        System.out.println("Jobs paused for job group: " + jobGroup);
    }

    @Override
    public void jobResumed(JobKey jobKey) {
        System.out.println("Job resumed: " + jobKey.getName());
    }

    @Override
    public void jobsResumed(String jobGroup) {
        System.out.println("Jobs resumed for job group: " + jobGroup);
    }

    @Override
    public void schedulerError(String msg, SchedulerException cause) {
        System.out.println("Scheduler Error: " + cause);
    }

    @Override
    public void schedulerInStandbyMode() {
        try {
            System.out.println("Scheduler put in standby mode: " + scheduler.getSchedulerName());
        } catch (SchedulerException e) {
            System.out.println("Error getting scheduler name" + e);
        }
    }

    @Override
    public void schedulerStarted() {
        try {
            System.out.println("Scheduler started: " + scheduler.getSchedulerName());
        } catch (SchedulerException e) {
            System.out.println("Error getting scheduler name" + e);
        }
    }

    @Override
    public void schedulerShutdown() {
        try {
            System.out.println("Scheduler shutdown: " + scheduler.getSchedulerName());
        } catch (SchedulerException e) {
            System.out.println("Error getting scheduler name" + e);
        }
    }

    @Override
    public void schedulerShuttingdown() {
        try {
            System.out.println("Scheduler shutting down: " + scheduler.getSchedulerName());
        } catch (SchedulerException e) {
            System.out.println("Error getting scheduler name" + e);
        }
    }

    @Override
    public void schedulingDataCleared() {
        try {
            System.out.println("Scheduler data cleared: " + scheduler.getSchedulerName());
        } catch (SchedulerException e) {
            System.out.println("Error getting scheduler name" + e);
        }
    }

    @Override
    public void schedulerStarting() {
        try {
            System.out.println("Scheduler starting: " + scheduler.getSchedulerName());
        } catch (SchedulerException e) {
            System.out.println("Error getting scheduler name" + e);
        }
    }

}