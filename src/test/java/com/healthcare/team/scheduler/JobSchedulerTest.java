package com.healthcare.team.scheduler;

import com.healthcare.team.BashProcess.Compute;

import com.healthcare.team.BashProcess.GenerateCsvAndSendDataJob;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.runners.MethodSorters;
import org.quartz.*;
import org.mockito.runners.MockitoJUnitRunner;

import static com.healthcare.team.commons.Constants.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.class)
public class JobSchedulerTest {

    /*
     @Test(expected = RuntimeException.class)
     public void _ifJobNotStartedAndPauseThrowError() {
         JobScheduler.pause();
     }

     @Test(expected = RuntimeException.class)
     public void _ifJobNotStartedAndStopThrowError() {
         JobScheduler.stop();
     }
    */

    @Test(expected = RuntimeException.class)
    public void _ifJobNotStartedAndResumeThrowError() {
        JobScheduler.resume(10);
    }

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

        JobKey jobKey = new JobKey(JOB_NAME);
        JobDetail jd = sch.getJobDetail(jobKey);
        assertEquals(jd.getJobClass(), GenerateCsvAndSendDataJob.class);
        assertEquals(1, sch.getTriggersOfJob(jobKey).size());
        JobScheduler.stop();
    }

    @Test
    public void ifJobNotStartedContextParametersAreNull() throws Exception {
        Compute comp = new Compute(
                "3",
                "10",
                "18",
                "female",
                "Allergic_Rhinitis",
                "Shropshire"
        );
        Scheduler sch = JobScheduler.init(comp, comp.getStateSynthea(), 30, "TEST");
        assertNull(sch.getContext().get(REGION_CTX_PARAM_NAME));
        assertNull(sch.getContext().get(COMPUTE_AS_CTX_PARAMETER_NAME));
        assertFalse(sch.isStarted());
    }

    @Test(expected = RuntimeException.class)
    public void ifNegativeIntervalThrowError() {
        Compute comp = new Compute(
                "3",
                "10",
                "18",
                "female",
                "Allergic_Rhinitis",
                "Shropshire"
        );
        Scheduler sch = JobScheduler.init(comp, comp.getStateSynthea(), -1, "START");
        JobScheduler.stop();
    }

    @Test
    public void ifJobStartedAndThenStandBy() throws Exception {
        Compute comp = new Compute(
                "3",
                "10",
                "18",
                "female",
                "Allergic_Rhinitis",
                "Shropshire"
        );
        Scheduler sch = JobScheduler.init(comp, comp.getStateSynthea(), 30, "START");
        assertTrue(sch.isStarted());
        JobScheduler.pause();
        assertTrue(sch.isInStandbyMode());
        assertNotNull(sch.getContext().get(REGION_CTX_PARAM_NAME));
        assertNotNull(sch.getContext().get(COMPUTE_AS_CTX_PARAMETER_NAME));
        JobScheduler.stop();
    }

    @Test
    public void ifJobIsInStandByThenResume() throws Exception {
        Compute comp = new Compute(
                "3",
                "10",
                "18",
                "female",
                "Allergic_Rhinitis",
                "Shropshire"
        );
        Scheduler sch = JobScheduler.init(comp, comp.getStateSynthea(), 30, "START");
        JobScheduler.pause();
        JobScheduler.resume(10);
        assertTrue(sch.isStarted());
        JobScheduler.stop();
    }

    @Test
    public void ifJobIsInResumeThenStandBy() throws Exception {
        Compute comp = new Compute(
                "3",
                "10",
                "18",
                "female",
                "Allergic_Rhinitis",
                "Shropshire"
        );
        Scheduler sch = JobScheduler.init(comp, comp.getStateSynthea(), 30, "START");
        JobScheduler.pause();
        assertTrue(sch.isInStandbyMode());
        JobScheduler.stop();
    }

    @Test
    public void ifJobIsInResumeThenStop() throws Exception {
        Compute comp = new Compute(
                "3",
                "10",
                "18",
                "female",
                "Allergic_Rhinitis",
                "Shropshire"
        );
        Scheduler sch = JobScheduler.init(comp, comp.getStateSynthea(), 30, "START");
        JobScheduler.stop();
        assertTrue(sch.isShutdown());
    }

    @Test
    public void ifJobIsInPauseThenStop() throws Exception {
        Compute comp = new Compute(
                "3",
                "10",
                "18",
                "female",
                "Allergic_Rhinitis",
                "Shropshire"
        );
        Scheduler sch = JobScheduler.init(comp, comp.getStateSynthea(), 30, "START");
        JobScheduler.pause();
        JobScheduler.stop();
        assertTrue(sch.isShutdown());
    }
}
