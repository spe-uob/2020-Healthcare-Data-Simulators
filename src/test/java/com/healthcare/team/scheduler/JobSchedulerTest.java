package com.healthcare.team.scheduler;

import com.healthcare.team.Compute;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.quartz.*;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import org.mockito.runners.MockitoJUnitRunner;

import static com.healthcare.team.commons.Constants.COMPUTE_AS_CTX_PARAMETER_NAME;
import static com.healthcare.team.commons.Constants.REGION_CTX_PARAM_NAME;
import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class JobSchedulerTest {

    @Test
    public void ifJobStarts() throws Exception {
        Compute comp = new Compute(
                "3",
                "10",
                "18",
                "female",
                "Allergic_Rhinitis",
                "Shropshire"
        );
        Scheduler sch = JobScheduler.init(comp, comp.getStateSynthea(), 30, "START");
        assertNotNull(sch.getContext().get(REGION_CTX_PARAM_NAME));
        assertNotNull(sch.getContext().get(COMPUTE_AS_CTX_PARAMETER_NAME));
        assertTrue(sch.isStarted());

        JobKey jobKey = new JobKey("generateCsvAndSendDataJob");
        JobDetail jd = sch.getJobDetail(jobKey);
        assertEquals(jd.getJobClass(), GenerateCsvAndSendDataJob.class);
        assertEquals(1, sch.getTriggersOfJob(jobKey).size());
    }

    @Test
    public void ifJobNotStartedContextParametersAreNull() throws Exception {

        Scheduler sch = JobScheduler.init(null, null, 30, "TEST");
        assertNull(sch.getContext().get(REGION_CTX_PARAM_NAME));
        assertNull(sch.getContext().get(COMPUTE_AS_CTX_PARAMETER_NAME));
        assertFalse(sch.isStarted());
        assertTrue(sch.isShutdown());
    }

    @Test(expected = RuntimeException.class)
    public void ifNegativeIntervalThrowError() throws Exception {
        Scheduler sch = JobScheduler.init(null, null, -1, "");
    }
}
