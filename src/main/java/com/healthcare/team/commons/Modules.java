package com.healthcare.team.commons;

public enum Modules {


    Allergies("Allergies"),

    Appendicitis("Appendicitis"),

    Asthma("Asthma"),

    Atopy("Atopy"),

    AttentionDeficitDisorder("Attention_Deficit_Disorder"),

    Bronchitis("Bronchitis"),

    ColorectalCancer("Colorectal_Cancer"),

    ContraceptiveMaintenance("Contraceptive_Maintenance"),

    Contraceptives("Contraceptives"),

    Copd("Copd"),

    Dementia("Dementia"),

    Dermatitis("Dermatitis"),

    EarInfections("Ear_Infections"),

    Epilepsy("Epilepsy"),

    FemaleReproduction("Female_Reproduction"),

    Fibromyalgia("Fibromyalgia"),

    FoodAllergies("Food_Allergies"),

    Gout("Gout"),

    Homelessness("Homelessness"),

    Injuries("Injuries"),

    LungCancer("Lung_Cancer"),

    Lupus("Lupus"),

    MedRec("Med_Rec"),

    MetabolicSyndromeCare("Metabolic_Syndrome_Care"),

    MetabolicSyndromeDisease("Metabolic_Syndrome_Disease"),

    OpioidAddiction("Opioid_Addiction"),

    Osteoarthritis("Osteoarthritis"),

    Osteoporosis("Osteoporosis"),

    Pregnancy("Pregnancy"),

    RheumatoidArthritis("Rheumatoid_Arthritis"),

    Rhinitis("Allergic_Rhinitis"),

    SelfHarm("Self_Harm"),

    SexualActivity("Sexual_Activity"),

    Sinusitis("Sinusitis"),

    SoreThroat("Sore_Throat"),

    TotalJointReplacement("Total_Joint_Replacement"),

    UrinaryTractInfections("Urinary_Tract_Infections"),

    WellnessEncounters("Wellness_Encounters");

    private String description;

    Modules(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
