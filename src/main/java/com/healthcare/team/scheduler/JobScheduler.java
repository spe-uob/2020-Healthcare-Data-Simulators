package com.healthcare.team.scheduler;

import com.healthcare.team.Compute;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;


import static com.healthcare.team.commons.Constants.*;

public class JobScheduler {

    public static void init(Compute computer, String region, int interval, String action) {
        try {
            JobDetail job = JobBuilder.newJob(GenerateCsvAndSendDataJob.class)
                    .withIdentity("generateCsvAndSendDataJob")
                    .build();

            // specify the running period of the job
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(interval)
                            .repeatForever())
                    .startAt(Timestamp.valueOf(LocalDateTime.now().plusSeconds(interval)))
                    .build();

            //schedule the job
            SchedulerFactory schFactory = new StdSchedulerFactory();
            Scheduler sch = schFactory.getScheduler();
            if (ACTION_JOB_START.equals(action)) {
                SchedulerContext context = sch.getContext();
                context.put(COMPUTE_AS_CTX_PARAMETER_NAME, computer);
                context.put(REGION_CTX_PARAM_NAME, region);
                sch.start();
                sch.scheduleJob(job, trigger);
            } else {
                sch.shutdown(Boolean.TRUE);
            }
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
}
