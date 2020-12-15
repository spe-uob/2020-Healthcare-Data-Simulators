

import com.squareup.okhttp.*;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Send {

    public void SendResource(String url, String rawData, String accessToken) throws IOException {
        final OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(1000, TimeUnit.SECONDS);
        client.setReadTimeout(1000, TimeUnit.SECONDS);
        client.setWriteTimeout(1000, TimeUnit.SECONDS);
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, rawData);
        Request request = new Request.Builder()
                .url("https://e81uscwufb.execute-api.eu-west-2.amazonaws.com/dev/"+url)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("x-api-key", "pqkLkOczhV6DVXNWVasFJauRPoWsyNjf63MRacJj")
                .addHeader("Authorization", "Bearer "+accessToken)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response);
    }
}
