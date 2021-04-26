package com.healthcare.team.scheduler;

import com.healthcare.team.Compute;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import org.mockito.runners.MockitoJUnitRunner;

import static com.healthcare.team.commons.Constants.COMPUTE_AS_CTX_PARAMETER_NAME;
import static com.healthcare.team.commons.Constants.REGION_CTX_PARAM_NAME;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JobSchedulerTest {

    @Mock
    private Scheduler scheduler;

    @Mock
    private SchedulerFactory factory;

    private JobScheduler jobScheduler;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        jobScheduler = new JobScheduler() {
            protected Scheduler getScheduler() {
                return scheduler;
            }
        };
        when(factory.getScheduler()).thenReturn(scheduler);
        //when(scheduler.getContext()).thenReturn(jobScheduler.getContext());
    }

    @Test
    public void testSchedule() throws Exception {
        final String jobName = "testjob";
        final Map<String, Object> jobMap = Collections.<String, Object>singletonMap("a", "b");
        final Date jobStartTime = new Date();
        final int repeatInterval = 60000;

        when(scheduler.scheduleJob(isA(JobDetail.class), isA(Trigger.class))).thenReturn(new Date());
       /* jobScheduler.scheduleJob(jobName, TestPluginJob.class, jobMap, jobStartTime, repeatInterval);
        verify(scheduler).scheduleJob(argThat(new JobDetailsMatcher(jobName)),
                argThat(new JobTriggerMatcher(jobStartTime, repeatInterval)));*/
        Compute comp = new Compute(
                "3",
                "10",
                "18",
                "female",
                "Allergic_Rhinitis",
                "Shropshire"
        );
        JobScheduler.init(comp, comp.getStateSynthea(), 30, "START");
        String s = (String) scheduler.getContext().get(REGION_CTX_PARAM_NAME);
        System.out.println(s);
    }
}
