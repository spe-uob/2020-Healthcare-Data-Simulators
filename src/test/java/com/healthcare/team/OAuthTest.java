package com.healthcare.team;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.*;

import java.io.File;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OAuthTest {
    private OauthWithNoJOptionPane oAuth;

    /*
        this class ensures no JOptionPanes are created
         which require user input to continue, hence, Tests pass.
    */
    public static class OauthWithNoJOptionPane extends OAuth {
        public OauthWithNoJOptionPane(String client_id, String region, String username, String password) {
            super(client_id, region, username, password);
        }

        @Override
        protected void alertUser() {}
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyClientIdShouldThrow() {
        oAuth = new OauthWithNoJOptionPane(
                "",
                "eu-west-2",
                "data-sim-team",
                "jOvK-dRCs-kCW3-ZgPx"
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyRegionShouldThrow() {
        oAuth = new OauthWithNoJOptionPane(
                "7n4vr35t6o5153456ervok1vm9",
                "",
                "data-sim-team",
                "jOvK-dRCs-kCW3-ZgPx"
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyUserNameShouldThrow() {
        oAuth = new OauthWithNoJOptionPane(
                "7n4vr35t6o5153456ervok1vm9",
                "eu-west-2",
                "",
                "jOvK-dRCs-kCW3-ZgPx"
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyPasswordShouldThrow() {
        oAuth = new OauthWithNoJOptionPane(
                "7n4vr35t6o5153456ervok1vm9",
                "eu-west-2",
                "data-sim-team",
                ""
        );
    }

   @Test(timeout = 10000)
    public void checkProcessParameters() {
        OAuth auth = new OAuth("1234", "Gloucestershire", "J Doe", "8***");
        List<String> expected = List.of("python3", "lib" + File.separator + "lib/cognito_auth.py",
                auth.getClientId(), auth.getAwsRegion(), auth.getUsername(), auth.getPassword());
        List<String> actual = auth.processParameters(auth.getAwsRegion());
        assertThat(actual, hasItems("python3"));
        assertThat(actual, hasSize(6));
        assertThat(actual, not(IsEmptyCollection.empty()));
    }

    @Test
    public void checkShowAlertDialogInSpecificCondition() {
        OAuth auth = new OAuth("1234", "Gloucestershire", "J Doe", "8***");
        assertTrue(auth.showAlert("   "));
        assertTrue(auth.showAlert(null));
        assertFalse(auth.showAlert("some string here"));
    }
}