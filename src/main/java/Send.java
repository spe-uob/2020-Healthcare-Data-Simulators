

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
                if(fileName.contains("jpeg")) {
                    MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("application/json");
                    RequestBody requestBody = RequestBody.create(MEDIA_TYPE_MARKDOWN, "{\n  \"resourceType\": \"Binary\",\n  \"contentType\": \"image/jpeg\"\n}");
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
                    JSONBinaryResponse user = gson.fromJson(jsonData, JSONBinaryResponse.class);
                    System.out.println("THIS IS THE PRESIDGNED URL BOYYYYYYYYY:");
                    System.out.println(user.getPresignedPutUrl());
                    break;
                }
        }
    }
}
