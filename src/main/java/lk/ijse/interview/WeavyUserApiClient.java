package lk.ijse.interview;

import okhttp3.*;

import java.io.IOException;

public class WeavyUserApiClient {

    private static final String WEAVY_SERVER = "https://{WEAVY-SERVER}";
    private static final String API_KEY = "{wys_R830thWWfHBkfXvmW17HXn7xOlH04K05hFfo}";

    private final OkHttpClient client;

    public WeavyUserApiClient() {
        this.client = new OkHttpClient();
    }

    public void createUser(String uid, String name, String directory) throws IOException {
        String endpoint = WEAVY_SERVER + "/api/users";
        String jsonBody = String.format("{ 'uid': '%s', 'name': '%s', 'directory': '%s' }", uid, name, directory);

        Request request = new Request.Builder()
                .url(endpoint)
                .header("Authorization", "Bearer " + API_KEY)
                .header("Content-Type", "application/json")
                .post(RequestBody.create(MediaType.parse("application/json"), jsonBody))
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("Create User Response: " + response.body().string());
        }
    }

    public String listUsers() throws IOException {
        String endpoint = WEAVY_SERVER + "/api/users?top=20";

        Request request = new Request.Builder()
                .url(endpoint)
                .header("Authorization", "Bearer " + API_KEY)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String getUserDetails(int userId) throws IOException {
        String endpoint = WEAVY_SERVER + "/api/users/" + userId;

        Request request = new Request.Builder()
                .url(endpoint)
                .header("Authorization", "Bearer " + API_KEY)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }




    public static void main(String[] args) {
        WeavyUserApiClient weavyUserApiClient = new WeavyUserApiClient();


    }
}
