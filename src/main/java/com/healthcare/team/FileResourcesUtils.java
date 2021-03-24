package com.healthcare.team;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.*;
import static java.util.stream.Collectors.toList;


public class FileResourcesUtils {

    public static final String[] locations = {"src", "main", "resources", "lib"};
    private static final List<File> requiredFiles = new ArrayList<>();
    static {
        requiredFiles.add(new File("lib" + File.separator + "cognito_auth.py"));
        requiredFiles.add(new File("lib" + File.separator + "synthea-with-dependencies.jar"));
        requiredFiles.add(new File("lib" + File.separator + "convertor_hl7-with-dependencies.jar"));
        requiredFiles.add(new File("lib" + File.separator + "regions.txt"));
        requiredFiles.add(new File("lib" + File.separator + "modules.txt"));
    }

    public boolean extractResources() {

        long counter = requiredFiles.stream().filter(File::exists).count();

        if (counter == requiredFiles.size()) {
            System.out.println("files already extracted, skipping...");
            return true;
        }

        FileResourcesUtils app = new FileResourcesUtils();

        // Sample 3 - read all files from a resources folder (JAR version)
        try {

            // get paths from src/main/resources/
            List<Path> result = app.getPathsFromResourceJAR();

            if (result.isEmpty()) {
                String[] location = {"src", "main", "resources", "lib"};
                File file = new File(String.join(File.separator, location));
                result = Files.walk(file.toPath())
                        .filter(Files::isRegularFile)
                        .sorted()
                        .collect(toList());

                for (Path path : result) {
                    InputStream is = new FileInputStream(path.toFile());
                    String str = path.toString().substring(19);
                    copyFile(is, str);
                }
            } else {
                for (Path path : result) {

                    //System.out.println("Path : " + path);

                    String filePathInJAR = path.toString();
                    // Windows will returns /lib/file1.jar, cut the first /
                    // the correct path should be  lib/file1.jar
                    if (filePathInJAR.startsWith("/")) {
                        filePathInJAR = filePathInJAR.substring(1);
                    }

                    //System.out.println("filePathInJAR : " + filePathInJAR);

                    // read a file from resource folder
                    InputStream is = app.getFileFromResourceAsStream(filePathInJAR);
                    copyFile(is, filePathInJAR);
                }
            }

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("files extracted");
        return true;
    }

    // get a file from the resources folder
    // works everywhere, IDEA, unit test and JAR file.
    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }

    // Get all paths from a folder that inside the JAR file
    private List<Path> getPathsFromResourceJAR()
            throws URISyntaxException, IOException {

        // get path of the current running JAR
        String jarPath = getClass().getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .toURI()
                .getPath();
        // System.out.println("JAR Path :" + jarPath);

        // file walks JAR
        URI uri = URI.create("jar:file:" + jarPath);

        File file = new File(String.join(File.separator, locations));
        List<Path> result = new ArrayList<>();
        if (!file.exists()) {
            try (FileSystem fs = FileSystems.newFileSystem(uri, Collections.emptyMap())) {
                result = Files.walk(fs.getPath("lib"))
                        .filter(Files::isRegularFile)
                        .sorted()
                        .collect(toList());
            }
        }
        return result;
    }

    // copies files to current directory for execution
    private void copyFile(InputStream is, String filePath) {
        OutputStream os;
        File dir = new File("lib");
        boolean done = dir.mkdir();

        if (done || dir.exists()) {
            try {
                os = new FileOutputStream("lib" + File.separator + filePath.substring(filePath.lastIndexOf(File.separator) + 1));
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }

                is.close();
                os.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
