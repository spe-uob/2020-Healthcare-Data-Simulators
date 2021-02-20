package com.healthcare.team.unit;

import com.healthcare.team.Compute;
import org.junit.*;

public class ComputeTest {
    private Compute compute;

    @After
    public void clean() {

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

    @Test(expected = IllegalArgumentException.class, timeout = 15000)
    public void testEmptyStateShouldThrow() {
        compute = new Compute(
                "1",
                "0",
                "1",
                "Female",
                "Allergic-Rhinitis",
                ""
        );
        compute.generatePatient();
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
        compute.generatePatient();
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
        compute.generatePatient();
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
        compute.generatePatient();
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
        compute.generatePatient();
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
        compute.generatePatient();
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
        compute.generatePatient();
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
        compute.generatePatient();
        compute = new Compute(
                "1",
                "0",
                "happy",
                "Female",
                "Allergic-Rhinitis",
                "Shropshire"
        );
        compute.generatePatient();
        compute = new Compute(
                "1",
                "0",
                "-1",
                "Female",
                "Allergic-Rhinitis",
                "Shropshire"
        );
        compute.generatePatient();
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
        compute.generatePatient();
    }
}
