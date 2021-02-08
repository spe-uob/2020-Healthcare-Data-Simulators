

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.squareup.okhttp.*;

import java.io.*;
import java.nio.Buffer;
import java.util.concurrent.TimeUnit;

public class Send {


    public void SendResource(GUI.DATA d, File file, String url, String rawData, String accessToken) throws IOException {
        final OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(10000, TimeUnit.SECONDS);
        client.setReadTimeout(1000, TimeUnit.SECONDS);
        client.setWriteTimeout(1000, TimeUnit.SECONDS);
        switch (d) {
            case SYNTHEA:
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, rawData);
                Request request1 = new Request.Builder()
                        .url("https://e81uscwufb.execute-api.eu-west-2.amazonaws.com/dev/" + url)
                        .method("POST", body)
                        .addHeader("Content-Type", "application/json")
                        .addHeader("x-api-key", "pqkLkOczhV6DVXNWVasFJauRPoWsyNjf63MRacJj")
                        .addHeader("Authorization", "Bearer " + accessToken)
                        .build();
                Response response1 = client.newCall(request1).execute();
                System.out.println(response1);
                break;
            case BINARY:
                String fileName = file.toString();
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
                        .addHeader("Authorization", "Bearer " + accessToken)
                        .build();

                Response response2 = client.newCall(request2).execute();
                if (!response2.isSuccessful())
                    throw new IOException("Unexpected code " + response2);

                String jsonData = response2.body().string();
                System.out.println(jsonData);

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JSONBinaryResponse jsonBinaryResponse = gson.fromJson(jsonData, JSONBinaryResponse.class);

                System.out.println("THIS IS THE PRESIDGNED URL BOYYYYYYYYY:");
                String presignedPutUrl = jsonBinaryResponse.getPresignedPutUrl();
                System.out.println(presignedPutUrl);

                    //2nd REQUEST - PUT
                MediaType mediaTypeImage = MediaType.parse("image/jpeg");
                InputStream inputStream =  new FileInputStream(file);
                RequestBody requestBodyImage = RequestBodyUtil.create(mediaTypeImage, inputStream);
                Request request3 = new Request.Builder()
                        .url(presignedPutUrl)
                        .method("PUT", requestBodyImage)
                        .addHeader("Content-Type", "image/jpeg")
                        //.addHeader("x-api-key", "pqkLkOczhV6DVXNWVasFJauRPoWsyNjf63MRacJj")
                        //.addHeader("Authorization", "Bearer " + accessToken)
                        .build();

                Response response3 = client.newCall(request3).execute();
                System.out.println(response3);
                break;


        }
    }
}
