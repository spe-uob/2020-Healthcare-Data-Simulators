package com.healthcare.team.scheduler;

import com.healthcare.team.BashProcess.Compute;
import com.healthcare.team.BashProcess.GenerateCsvAndSendDataJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


import static com.healthcare.team.commons.Constants.*;

public class JobScheduler {

    private static Scheduler scheduler;

    public static Scheduler init(Compute computer, String region, int interval, String action) {
        try {
            JobDetail job = JobBuilder.newJob(GenerateCsvAndSendDataJob.class)
                    .withIdentity(JOB_NAME)
                    .build();

            // specify the running period of the job
            Trigger trigger = buildJobTrigger(interval, LocalDateTime.now().plusSeconds(interval));

            //schedule the job
            SchedulerFactory schFactory = new StdSchedulerFactory();
            Scheduler sch = schFactory.getScheduler();
            if (ACTION_JOB_START.equals(action) || ACTION_RESUME_JOB.equals(action)) {
                SchedulerContext context = sch.getContext();
                context.put(COMPUTE_AS_CTX_PARAMETER_NAME, computer);
                context.put(REGION_CTX_PARAM_NAME, region);
                sch.start();
                sch.scheduleJob(job, trigger);
            }
            scheduler = sch;
            return sch;
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    public static Scheduler pause() {
        try {
            if (scheduler.isStarted()) {
                scheduler.standby();
            }
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
        return scheduler;
    }

    public static Scheduler resume(int interval) {
        try {
            if (scheduler.isStarted() || scheduler.isInStandbyMode()) {
                JobKey jobKey = new JobKey(JOB_NAME);
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                Trigger oldTrigger = triggers.get(0);
                Trigger newTrigger = buildJobTrigger(interval, LocalDateTime.now());
                scheduler.rescheduleJob(oldTrigger.getKey(), newTrigger);

                scheduler.start();
            }
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
        return scheduler;
    }

    public static Scheduler stop() {
        try {
            if (scheduler.isStarted() || scheduler.isInStandbyMode()) {
                scheduler.shutdown(Boolean.TRUE);
            }
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
        return scheduler;
    }

    private static Trigger buildJobTrigger(int interval, LocalDateTime now) {
        return TriggerBuilder.newTrigger()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(interval)
                        .repeatForever())
                .startAt(Timestamp.valueOf(now))
                .build();
    }
}
