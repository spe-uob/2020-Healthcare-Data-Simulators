package com.healthcare.team.unit;

import com.healthcare.team.Compute;
import com.healthcare.team.InitialSetup;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ComputeTest {
    private Compute compute;

    /*
        this class ensures no JOptionPanes are created
         which require user input to continue, hence, Tests pass.
    */
   public static class ComputeWithNoJOptionPane extends Compute {
        public ComputeWithNoJOptionPane(String population, String minAge,
                                        String maxAge, String gender,
                                        String module, String state) {
            super(population, minAge, maxAge, gender, module, state);
        }

        @Override
        protected void alertUser() {}

        @Override
        protected void informUser() {}
    }

    @BeforeClass
    public static void setupTestsClass() {
        //extract files first before running any test
        InitialSetup initialSetup = new InitialSetup();
        initialSetup.setup();
    }

    @Test(expected = IllegalArgumentException.class, timeout = 15000)
    public void testEmptyPopulationShouldThrow() {
        compute = new Compute(
                "",
                "0",
                "1",
                "Female",
                "Allergic-Rhinitis",
                "Shropshire"
        );
        compute.generatePatient();
    }

    @Test(expected = IllegalArgumentException.class, timeout = 15000)
    public void testEmptyMinAgeShouldThrow() {
        compute = new Compute(
                "1",
                "",
                "1",
                "Female",
                "Allergic-Rhinitis",
                "Shropshire"
        );
        compute.generatePatient();
    }

    @Test(expected = IllegalArgumentException.class, timeout = 15000)
    public void testEmptyMaxAgeShouldThrow() {
        compute = new Compute(
                "1",
                "0",
                "",
                "Female",
                "Allergic-Rhinitis",
                "Shropshire"
        );
        compute.generatePatient();
    }

    @Test(expected = IllegalArgumentException.class, timeout = 15000)
    public void testEmptyGenderShouldThrow() {
        compute = new Compute(
                "1",
                "0",
                "1",
                "",
                "Allergic-Rhinitis",
                "Shropshire"
        );
        compute.generatePatient();
    }

    @Test(expected = IllegalArgumentException.class, timeout = 15000)
    public void testEmptyModuleShouldThrow() {
        compute = new Compute(
                "1",
                "0",
                "1",
                "Female",
                "",
                "Shropshire"
        );
        compute.generatePatient();
    }

    @Test(expected = IllegalArgumentException.class, timeout = 30000)
    public void testEmptyStateShouldThrow() {
        compute = new Compute(
                "1",
                "0",
                "1",
                "Female",
                "Allergic-Rhinitis",
                ""
        );
    }

    @Test(expected = NullPointerException.class, timeout = 15000)
    public void testNullPopulationShouldThrow() {
        compute = new Compute(
                null,
                "0",
                "1",
                "Female",
                "Allergic-Rhinitis",
                "Shropshire"
        );
    }

    @Test(expected = NullPointerException.class, timeout = 15000)
    public void testNullMinAgeShouldThrow() {
        compute = new Compute(
                "1",
                null,
                "1",
                "Female",
                "Allergic-Rhinitis",
                "Shropshire"
        );
    }

    @Test(expected = NullPointerException.class, timeout = 15000)
    public void testNullMaxAgeShouldThrow() {
        compute = new Compute(
                "1",
                "0",
                null,
                "Female",
                "Allergic-Rhinitis",
                "Shropshire"
        );
    }

    @Test(expected = NullPointerException.class, timeout = 15000)
    public void testNullGenderShouldThrow() {
        compute = new Compute(
                "1",
                "0",
                "1",
                null,
                "Allergic-Rhinitis",
                "Shropshire"
        );
    }

    @Test(expected = NullPointerException.class, timeout = 15000)
    public void testNullModuleShouldThrow() {
        compute = new Compute(
                "1",
                "0",
                "1",
                "Female",
                null,
                "Shropshire"
        );
    }

    @Test(expected = NullPointerException.class, timeout = 15000)
    public void testNullStateShouldThrow() {
        compute = new Compute(
                "1",
                "0",
                "1",
                "Female",
                "Allergic-Rhinitis",
                null
        );
    }


    @Test(expected = IllegalArgumentException.class, timeout = 15000)
    public void testImpossiblePopulationShouldThrow() {
        compute = new Compute(
                "happy",
                "0",
                "1",
                "Female",
                "Allergic-Rhinitis",
                "Shropshire"
        );
        compute.generatePatient();
        compute = new Compute(
                "-1",
                "0",
                "1",
                "Female",
                "Allergic-Rhinitis",
                "Shropshire"
        );
        compute.generatePatient();
        compute = new Compute(
                "10000",
                "0",
                "1",
                "Female",
                "Allergic-Rhinitis",
                "Shropshire"
        );
        compute.generatePatient();
    }

    @Test(expected = IllegalArgumentException.class, timeout = 15000)
    public void testImpossibleMinAgeShouldThrow() {
        compute = new Compute(
                "1",
                "-6",
                "1",
                "Female",
                "Allergic-Rhinitis",
                "Shropshire"
        );
        compute.generatePatient();
        compute = new Compute(
                "1",
                "happy",
                "1",
                "Female",
                "Allergic-Rhinitis",
                "Shropshire"
        );
        compute.generatePatient();
    }

    @Test(expected = IllegalArgumentException.class, timeout = 15000)
    public void testImpossibleMaxAgeShouldThrow() {
        compute = new Compute(
                "1",
                "0",
                "201",
                "Female",
                "Allergic-Rhinitis",
                "Shropshire"
        );
        compute = new Compute(
                "1",
                "0",
                "happy",
                "Female",
                "Allergic-Rhinitis",
                "Shropshire"
        );
        compute = new Compute(
                "1",
                "0",
                "-1",
                "Female",
                "Allergic-Rhinitis",
                "Shropshire"
        );
    }

    @Test(expected = IllegalArgumentException.class, timeout = 15000)
    public void testImpossibleGenderShouldThrow() {
        compute = new Compute(
                "1",
                "0",
                "1",
                "R",
                "Allergic-Rhinitis",
                "Shropshire"
        );
        compute.generatePatient();
    }

    @Test(expected = IllegalArgumentException.class, timeout = 15000)
    public void testImpossibleModuleShouldThrow() {
        compute = new Compute(
                "1",
                "0",
                "1",
                "Female",
                "coding",
                "Shropshire"
        );
        compute.generatePatient();
    }

    @Test(expected = IllegalArgumentException.class, timeout = 15000)
    public void testImpossibleStateShouldThrow() {
        compute = new Compute(
                "1",
                "0",
                "1",
                "Female",
                "Allergic-Rhinitis",
                "California"
        );
    }

    @Test(timeout = 100000)
    public void testAllCorrectValuesShouldPass() {
        ComputeWithNoJOptionPane computeWithNoJOptionPane = new ComputeWithNoJOptionPane(
                "1",
                "0",
                "1",
                "male",
                "Allergic_Rhinitis",
                "Somerset"
        );
        assertEquals(computeWithNoJOptionPane.getPopulation(), "1");
        assertEquals(computeWithNoJOptionPane.getMinAge(), "0");
        assertEquals(computeWithNoJOptionPane.getMaxAge(), "1");
        assertEquals(computeWithNoJOptionPane.getGender(), "male");
        assertEquals(computeWithNoJOptionPane.getModule(), "Allergic_Rhinitis");
        assertEquals(computeWithNoJOptionPane.getStateSynthea(), "Somerset");
    }

    @Test(timeout = 10000)
    public void checkProcessParameters(){
        ComputeWithNoJOptionPane computeWithNoJOptionPane = new ComputeWithNoJOptionPane(
                "1",
                "0",
                "1",
                "male",
                "Allergic_Rhinitis",
                "Somerset"
        );
        String region = computeWithNoJOptionPane.getStateSynthea();
        String syntheaParams = new StringBuilder().append(" -p ").append(computeWithNoJOptionPane.getPopulation()).append(" -g ")
                .append(computeWithNoJOptionPane.getGender().equals("male") ? "M" : "F").append(" -a ")
                .append(computeWithNoJOptionPane.getMinAge()).append("-").append(computeWithNoJOptionPane.getMaxAge()).append(" -m ")
                .append(computeWithNoJOptionPane.getModule()).append(" ").append(region).toString();
        String command = new StringBuilder("java -jar ./lib/synthea-with-dependencies.jar")
                .append(syntheaParams)
                .append(" --exporter.baseDirectory ./")
                .append(region)
                .append(" --exporter.csv.export true").toString();
        List<String> actual = computeWithNoJOptionPane.processParameters(region);
        assertThat(actual, hasItems("-c"));
        assertThat(actual, hasSize(3));
        assertThat(actual, contains("bash", "-c", command));
        assertThat(actual, not(IsEmptyCollection.empty()));
    }
}
