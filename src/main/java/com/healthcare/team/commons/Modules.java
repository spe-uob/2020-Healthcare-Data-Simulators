package com.healthcare.team.commons;

public enum Modules {


    Allergies("Allergies"),

    Appendicitis("Appendicitis"),

    Asthma("Asthma"),

    Atopy("Atopy"),

    AttentionDeficitDisorder("Attention-Deficit-Disorder"),

    Bronchitis("Bronchitis"),

    ColorectalCancer("Colorectal-Cancer"),

    ContraceptiveMaintenance("Contraceptive-Maintenance"),

    Contraceptives("Contraceptives"),

    Copd("Copd"),

    Dementia("Dementia"),

    Dermatitis("Dermatitis"),

    EarInfections("Ear-Infections"),

    Epilepsy("Epilepsy"),

    FemaleReproduction("Female-Reproduction"),

    Fibromyalgia("Fibromyalgia"),

    FoodAllergies("Food-Allergies"),

    Gout("Gout"),

    Homelessness("Homelessness"),

    Injuries("Injuries"),

    LungCancer("Lung-Cancer"),

    Lupus("Lupus"),

    MedRec("Med-Rec"),

    MetabolicSyndromeCare("Metabolic-Syndrome-Care"),

    MetabolicSyndromeDisease("Metabolic-Syndrome-Disease"),

    OpioidAddiction("Opioid-Addiction"),

    Osteoarthritis("Osteoarthritis"),

    Osteoporosis("Osteoporosis"),

    Pregnancy("Pregnancy"),

    RheumatoidArthritis("Rheumatoid-Arthritis"),

    Rhinitis("Allergic-Rhinitis"),

    SelfHarm("Self-Harm"),

    SexualActivity("Sexual-Activity"),

    Sinusitis("Sinusitis"),

    SoreThroat("Sore-Throat"),

    TotalJointReplacement("Total-Joint-Replacement"),

    UrinaryTractInfections("Urinary-Tract-Infections"),

    WellnessEncounters("Wellness-Encounters");

    private String description;

    Modules(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
