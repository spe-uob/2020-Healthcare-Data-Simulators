package com.healthcare.team.scheduler;
import static com.healthcare.team.commons.Constants.COMPUTE_AS_CTX_PARAMETER_NAME;
import com.healthcare.team.BashProcess;
import com.healthcare.team.Compute;
import com.healthcare.team.ParseCSV;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

import java.util.List;

public class GenerateCsvAndSendDataJob extends BashProcess implements Job {

    Compute computer = null;

    @Override
    public void execute(JobExecutionContext jExeCtx) throws JobExecutionException {
        try {
            computer = (Compute) jExeCtx.getScheduler().getContext().get(COMPUTE_AS_CTX_PARAMETER_NAME);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
        executeCommand("Error on job running");
        new ParseCSV().sendPatientsToRabbit();
    }

    @Override
    protected void alertUser() {
    }

    @Override
    protected void informUser() {
    }

    @Override
    protected boolean showAlert(String output) {
        return false;
    }

    @Override
    protected List<String> processParameters(String region) {
        return computer.processParameters(region);
    }
}
