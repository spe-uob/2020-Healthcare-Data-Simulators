package com.healthcare.team;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import org.hl7.fhir.r4.model.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParseJSONConverted {

    public void parseJson(String accesstoken) throws IOException {
        // Create a FHIR context
        FhirContext ctx = FhirContext.forR4();

        // Instantiate a new parser
        IParser parser = ctx.newJsonParser();
        // Parse it
        //Patient parsed = parser.parseResource(Patient.class, input);
        String outputPath = System.getProperty("user.dir").concat("/ConvertedFile.json");
        System.out.println(outputPath);
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Bundle bundle = parser.parseResource(Bundle.class, new FileReader(outputPath));
        for (Bundle.BundleEntryComponent p : bundle.getEntry()) {
            Resource rsc = p.getResource();
            String url = rsc.fhirType();
            String rawData = parser.encodeResourceToString(rsc);
            executor.execute(Send.of(GUI.DATA.SYNTHEA, null, url, rawData, accesstoken));
        }
        executor.shutdown();
    }
}
