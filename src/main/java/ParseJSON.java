import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.parser.StrictErrorHandler;
import org.hl7.fhir.r4.model.*;

import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParseJSON {

    public void parseJson(String accesstoken) throws IOException {
        // Create a FHIR context
        FhirContext ctx = FhirContext.forR4();


        // Instantiate a new parser
        IParser parser = ctx.newJsonParser();
        IParser parser2 = ctx.newJsonParser();
        // Parse it
        //Patient parsed = parser.parseResource(Patient.class, input);
        String outputPath = System.getProperty("user.dir").concat("/output/fhir/");
        File dir = new File( outputPath);
        System.out.println(dir.getName());
        File[] directoryListing = dir.listFiles();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for(File f: directoryListing) {
            System.out.println("Current File: "+ f.getName());
            if(!f.getName().contains("json")) continue;
            Bundle bundle = parser.parseResource(Bundle.class, new FileReader(outputPath.concat(f.getName())));
            for (Bundle.BundleEntryComponent p : bundle.getEntry()) {
                Resource rsc = p.getResource();
                String rawData = parser.encodeResourceToString(rsc);
                String url = p.getChildByName("request").getValues().get(0).getChildByName("url").getValues().get(0).toString();
                url = url.substring(8, url.length() - 1);
                executor.execute(new Send(GUI.DATA.SYNTHEA, null, url, rawData, accesstoken));
//                Send sender = new Send(GUI.DATA.SYNTHEA, null, url, rawData, accesstoken);
//                sender.run();
            }
      }
        executor.shutdown();
    }
}
