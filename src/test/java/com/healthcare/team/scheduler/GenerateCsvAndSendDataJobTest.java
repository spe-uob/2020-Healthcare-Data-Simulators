package com.healthcare.team.scheduler;


import com.google.common.collect.ImmutableMap;
import com.healthcare.team.scheduler.JobScheduler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.quartz.*;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GenerateCsvAndSendDataJobTest {
/*
    @Mock
    private Scheduler scheduler;

    private JobScheduler jobScheduler;

    @Before
    public void setUp() throws Exception {
        jobScheduler = new JobScheduler() {

            protected Scheduler getScheduler() {
                return scheduler;
            }
        };
    }

    @Test
    public void testSchedule() throws Exception {
        final String jobName = "testjob";
        final Map<String, Object> jobMap = Collections.<String, Object>singletonMap("a", "b");
        final Date jobStartTime = new Date();
        final int repeatInterval = 60000;

        when(scheduler.scheduleJob(isA(JobDetail.class), isA(Trigger.class))).thenReturn(new Date());
        jobScheduler.scheduleJob(jobName, TestPluginJob.class, jobMap, jobStartTime, repeatInterval);
        verify(scheduler).scheduleJob(argThat(new JobDetailsMatcher(jobName)),
                argThat(new JobTriggerMatcher(jobStartTime, repeatInterval)));
    }

   /*@Test
    public void testUnschedule() throws Exception {
        when(scheduler.getJobNames(anyString())).thenReturn(new String[]{"test"});
        when(scheduler.deleteJob(anyString(), anyString())).thenReturn(true);

        pluginScheduler.unscheduleJob("test");
        verify(scheduler).deleteJob(eq("test"), anyString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnscheduleInexisting() throws Exception {
        when(scheduler.getJobNames(anyString())).thenReturn(new String[0]);
        pluginScheduler.unscheduleJob("test");
    }

    @Test
    public void testPluginJob() throws Exception {
        final JobExecutionContext context = mock(JobExecutionContext.class);
        JobDetail jobDetail = mock(JobDetail.class);
        when(context.getJobDetail()).thenReturn(jobDetail);
        when(jobDetail.getJobDataMap()).thenReturn(getJobDataMap());

        new QuartzPluginScheduler.QuartzPluginJob().execute(context);
        assertEquals(1, QuartzJob.n.intValue());
    }

    private JobDataMap getJobDataMap() {
        return new JobDataMap(ImmutableMap.<String, Object>builder()
                .put("pluginJobClass", QuartzJob.class)
                .build());
    }
*/

    /* utils */
/*
    public static class QuartzJob implements PluginJob {
        private static AtomicInteger n = new AtomicInteger(0);

        @Override
        public void execute(Map<String, Object> jobDataMap) {
            n.incrementAndGet();
        }
    }

    private abstract static class TestPluginJob implements PluginJob {
    }

    private static class JobDetailsMatcher extends ArgumentMatcher<JobDetail> {
        private final String name;

        private JobDetailsMatcher(String name) {
            this.name = name;
        }

        @Override
        public boolean matches(Object o) {
            JobDetail details = (JobDetail) o;
            return name.equals(details.getName());
        }
    }

    private static class JobTriggerMatcher extends ArgumentMatcher<Trigger> {
        private final Date startTime;
        private final int repeatInterval;

        private JobTriggerMatcher(Date jobStartTime, int repeatInterval) {
            startTime = jobStartTime;
            this.repeatInterval = repeatInterval;
        }

        @Override
        public boolean matches(Object o) {
            SimpleTrigger trigger = (SimpleTrigger) o;
            return startTime.equals(trigger.getStartTime()) && repeatInterval == trigger.getRepeatInterval();
        }
    }
*/
}