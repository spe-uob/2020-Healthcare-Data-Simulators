import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ParserCustomSettings {

    ArrayList<String> parse(String fileName) throws FileNotFoundException {
        ArrayList<String> strarr = new ArrayList<String>();

        try {

            File myFile = new File(fileName);
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (fileName == "modules.txt") strarr.add(data.toLowerCase());
                    else strarr.add(data);
            }

            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return strarr;
    }

}
