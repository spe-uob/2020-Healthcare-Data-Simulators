

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.squareup.okhttp.*;

import java.io.*;
import java.nio.Buffer;
import java.util.concurrent.TimeUnit;

public class Send implements Runnable {
    GUI.DATA d;
    File file;
    String url;
    String rawData;
    String accessToken;
    public Send(GUI.DATA d, File file, String url, String rawData, String accessToken){
        this.d = d;
        this.file = file;
        this.url = url;
        this.rawData = rawData;
        this.accessToken = accessToken;
    }

    @Override
    public void run() {
        final OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(100000, TimeUnit.SECONDS);
        client.setReadTimeout(1000000, TimeUnit.SECONDS);
        client.setWriteTimeout(1000000, TimeUnit.SECONDS);
        switch (this.d) {
            case SYNTHEA:
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, this.rawData);
                Request request1 = new Request.Builder()
                        .url("https://e81uscwufb.execute-api.eu-west-2.amazonaws.com/dev/" + this.url)
                        .method("POST", body)
                        .addHeader("Content-Type", "application/json")
                        .addHeader("x-api-key", "pqkLkOczhV6DVXNWVasFJauRPoWsyNjf63MRacJj")
                        .addHeader("Authorization", "Bearer " + this.accessToken)
                        .build();
                Response response1 = null;
                try {
                    response1 = client.newCall(request1).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(response1);
                break;
            case BINARY:
                String fileName = this.file.toString();
                String contentType = "";
                contentType = "application/octet-stream";
//                if(fileName.contains("jpeg") || fileName.contains("jpg"))  contentType = "image/jpeg";
//                else if(fileName.contains("png")) contentType = "image/png";
//                    else if(fileName.contains("pdf")) contentType = "application/pdf";
//                        else if(fileName.contains("mp4")) contentType = "video/mp4";
//                            else if(fileName.contains("mpeg")) contentType = "audio/mpeg";
//                                else if(fileName.contains("csv")) contentType = "text/csv";
//                                    else if(fileName.contains("txt")) contentType = "text/plain"
                        ;
                    //POST REQUEST TO GET URL
                MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("application/json");
                RequestBody requestBody = RequestBody.create(MEDIA_TYPE_MARKDOWN, "{\n  \"resourceType\": \"Binary\",\n  \"contentType\": \""+ contentType + "\"\n}");
                Request request2 = new Request.Builder()
                        .url("https://e81uscwufb.execute-api.eu-west-2.amazonaws.com/dev/Binary")
                        .method("POST", requestBody)
                        .addHeader("Content-Type", "application/json")
                        .addHeader("x-api-key", "pqkLkOczhV6DVXNWVasFJauRPoWsyNjf63MRacJj")
                        .addHeader("Authorization", "Bearer " + this.accessToken)
                        .build();

                Response response2 = null;
                try {
                    response2 = client.newCall(request2).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!response2.isSuccessful())
                    try {
                        throw new IOException("Unexpected code " + response2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                String jsonData = null;
                try {
                    jsonData = response2.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(jsonData);

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JSONBinaryResponse jsonBinaryResponse = gson.fromJson(jsonData, JSONBinaryResponse.class);

                System.out.println("THIS IS THE PRESIDGNED URL BOYYYYYYYYY:");
                String presignedPutUrl = jsonBinaryResponse.getPresignedPutUrl();
                System.out.println(presignedPutUrl);

                    //2nd REQUEST - PUT
                MediaType mediaTypeImage = MediaType.parse("image/jpeg");
                InputStream inputStream = null;
                try {
                    inputStream = new FileInputStream(this.file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                RequestBody requestBodyImage = RequestBodyUtil.create(mediaTypeImage, inputStream);
                Request request3 = new Request.Builder()
                        .url(presignedPutUrl)
                        .method("PUT", requestBodyImage)
                        .addHeader("Content-Type", "image/jpeg")
                        //.addHeader("x-api-key", "pqkLkOczhV6DVXNWVasFJauRPoWsyNjf63MRacJj")
                        //.addHeader("Authorization", "Bearer " + accessToken)
                        .build();

                Response response3 = null;
                try {
                    response3 = client.newCall(request3).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(response3);
                break;


        }
    }


}
