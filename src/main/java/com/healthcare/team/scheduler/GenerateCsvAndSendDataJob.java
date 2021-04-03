package com.healthcare.team.scheduler;

import static com.healthcare.team.commons.Constants.COMPUTE_AS_CTX_PARAMETER_NAME;
import static com.healthcare.team.commons.Constants.REGION_CTX_PARAM_NAME;

import com.healthcare.team.BashProcess;
import com.healthcare.team.Compute;
import com.healthcare.team.ParseCSV;
import org.quartz.*;

import java.util.List;

public class GenerateCsvAndSendDataJob extends BashProcess implements Job {

    Compute computer = null;

    @Override
    public void execute(JobExecutionContext jExeCtx) throws JobExecutionException {
        try {
            SchedulerContext context = jExeCtx.getScheduler().getContext();
            String region = (String) context.get(REGION_CTX_PARAM_NAME);
            computer = (Compute) context.get(COMPUTE_AS_CTX_PARAMETER_NAME);
            executeCommand("Error on job running");
            new ParseCSV().sendPatientsToRabbit(region);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
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
