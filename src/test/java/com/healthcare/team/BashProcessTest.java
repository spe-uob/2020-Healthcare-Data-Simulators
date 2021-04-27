package com.healthcare.team;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.healthcare.team.commons.Utils;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class BashProcessTest {

    @Test(expected = NullPointerException.class)
    public void ifNullCommandThrowError() {
        BashProcess bashProc = new BashProcess() {
            @Override
            protected void alertUser() {
            }

            @Override
            protected void informUser() {
            }

            @Override
            protected boolean showAlert(String output) {
                return Utils.isStringInvalid(output);
            }

            @Override
            protected List<String> processParameters(String region) {
                return null;
            }
        };
        bashProc.executeCommand("", "");
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void ifNoCommandThrowError() {
        BashProcess bashProc = new BashProcess() {
            @Override
            protected void alertUser() {
            }

            @Override
            protected void informUser() {
            }

            @Override
            protected boolean showAlert(String output) {
                return Utils.isStringInvalid(output);
            }

            @Override
            protected List<String> processParameters(String region) {
                return List.of();
            }
        };
        bashProc.executeCommand("", "");
    }

    @Test(expected = RuntimeException.class)
    public void ifEmptyCommandThrowError() {
        BashProcess bashProc = new BashProcess() {
            @Override
            protected void alertUser() {
            }

            @Override
            protected void informUser() {
            }

            @Override
            protected boolean showAlert(String output) {
                return Utils.isStringInvalid(output);
            }

            @Override
            protected List<String> processParameters(String region) {
                return List.of("");
            }
        };
        bashProc.executeCommand("", "");
    }

    @Test(expected = RuntimeException.class)
    public void ifBadCommandThrowError() {
        BashProcess bashProc = new BashProcess() {
            @Override
            protected void alertUser() {
            }

            @Override
            protected void informUser() {
            }

            @Override
            protected boolean showAlert(String output) {
                return Utils.isStringInvalid(output);
            }

            @Override
            protected List<String> processParameters(String region) {
                return List.of("bash", "-c");
            }
        };
        bashProc.executeCommand("", "");
    }

    @Test
    public void ifAllGoodGenerateToken() {
        BashProcess bashProc = new BashProcess() {
            @Override
            protected void alertUser() {
            }

            @Override
            protected void informUser() {
            }

            @Override
            protected boolean showAlert(String output) {
                return Utils.isStringInvalid(output);
            }

            @Override
            protected List<String> processParameters(String region) {

                return List.of("python3", "lib" + File.separator + "cognito_auth.py",
                        "7n4vr35t6o5153456ervok1vm9", "eu-west-2", "data-sim-team", "jOvK-dRCs-kCW3-ZgPx");

            }
        };
        bashProc.executeCommand("Shropshire", "No token generated!");
        assertNotEquals("", bashProc.getGeneratedToken());
        assertTrue(Utils.isValidString(bashProc.getGeneratedToken()));
    }
}
