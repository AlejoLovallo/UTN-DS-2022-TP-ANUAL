package Domain.Cron;

import org.quartz.*;

public class LogSchedulerListenerImpl implements SchedulerListener {

    private final Scheduler scheduler;

    public LogSchedulerListenerImpl(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void jobScheduled(Trigger trigger) {
        Logger.getInstance().loggearCron("Job scheduled: " + trigger.getKey().getName());
    }

    @Override
    public void jobUnscheduled(TriggerKey triggerKey) {
        Logger.getInstance().loggearCron("Job Unscheduled: " + triggerKey.getName());
    }

    @Override
    public void triggerFinalized(Trigger trigger) {
        Logger.getInstance().loggearCron("Job trigger finalized: " + trigger.getKey().getName());
    }

    @Override
    public void triggerPaused(TriggerKey triggerKey) {
        Logger.getInstance().loggearCron("Job trigger paused: " + triggerKey.getName());
    }

    @Override
    public void triggersPaused(String triggerGroup) {
        Logger.getInstance().loggearCron("Job triggers paused for trigger group: "
                + triggerGroup);
    }

    @Override
    public void triggerResumed(TriggerKey triggerKey) {
        Logger.getInstance().loggearCron("Job triggers resumed for trigger: " + triggerKey);
    }

    @Override
    public void triggersResumed(String triggerGroup) {
        Logger.getInstance().loggearCron("Job triggers resumed for trigger group: " + triggerGroup);
    }

    @Override
    public void jobAdded(JobDetail jobDetail) {
        Logger.getInstance().loggearCron("Job added: " + jobDetail.getKey().getName());
    }

    @Override
    public void jobDeleted(JobKey jobKey) {
        Logger.getInstance().loggearCron("Job deleted: " + jobKey.getName());
    }

    @Override
    public void jobPaused(JobKey jobKey) {
        Logger.getInstance().loggearCron("Jobs paused for job: " + jobKey);
    }

    @Override
    public void jobsPaused(String jobGroup) {
        Logger.getInstance().loggearCron("Jobs paused for job group: " + jobGroup);
    }

    @Override
    public void jobResumed(JobKey jobKey) {
        Logger.getInstance().loggearCron("Job resumed: " + jobKey.getName());
    }

    @Override
    public void jobsResumed(String jobGroup) {
        Logger.getInstance().loggearCron("Jobs resumed for job group: " + jobGroup);
    }

    @Override
    public void schedulerError(String msg, SchedulerException cause) {
        Logger.getInstance().loggearCron("Scheduler Error: " + cause);
    }

    @Override
    public void schedulerInStandbyMode() {
        try {
            Logger.getInstance().loggearCron("Scheduler put in standby mode: " + scheduler.getSchedulerName());
        } catch (SchedulerException e) {
            Logger.getInstance().loggearCron("Error getting scheduler name" + e);
        }
    }

    @Override
    public void schedulerStarted() {
        try {
            Logger.getInstance().loggearCron("Scheduler started: " + scheduler.getSchedulerName());
        } catch (SchedulerException e) {
            Logger.getInstance().loggearCron("Error getting scheduler name" + e);
        }
    }

    @Override
    public void schedulerShutdown() {
        try {
            Logger.getInstance().loggearCron("Scheduler shutdown: " + scheduler.getSchedulerName());
        } catch (SchedulerException e) {
            Logger.getInstance().loggearCron("Error getting scheduler name" + e);
        }
    }

    @Override
    public void schedulerShuttingdown() {
        try {
            Logger.getInstance().loggearCron("Scheduler shutting down: " + scheduler.getSchedulerName());
        } catch (SchedulerException e) {
            Logger.getInstance().loggearCron("Error getting scheduler name" + e);
        }
    }

    @Override
    public void schedulingDataCleared() {
        try {
            Logger.getInstance().loggearCron("Scheduler data cleared: " + scheduler.getSchedulerName());
        } catch (SchedulerException e) {
            Logger.getInstance().loggearCron("Error getting scheduler name" + e);
        }
    }

    @Override
    public void schedulerStarting() {
        try {
            Logger.getInstance().loggearCron("Scheduler starting: " + scheduler.getSchedulerName());
        } catch (SchedulerException e) {
            Logger.getInstance().loggearCron("Error getting scheduler name" + e);
        }
    }

}