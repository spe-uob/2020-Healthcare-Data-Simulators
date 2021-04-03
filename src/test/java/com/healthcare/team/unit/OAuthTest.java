package com.healthcare.team.unit;

import com.healthcare.team.OAuth;
import org.junit.*;

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
        protected void alertUserAuth() {}
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
}