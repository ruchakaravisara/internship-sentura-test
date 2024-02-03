package lk.ijse.interview;

import okhttp3.*;

import java.io.IOException;

public class WeavyUserApiClient {

    private static final String WEAVY_SERVER = "https://1e53e60f0368487b9410658071d6b98b.weavy.io";
    private static final String API_KEY = "wys_R830thWWfHBkfXvmW17HXn7xOlH04K05hFfo";

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

    public void updateUser(int userId, String newName) throws IOException {
        String endpoint = WEAVY_SERVER + "/api/users/" + userId;
        String jsonBody = String.format("{ 'name': '%s' }", newName);

        Request request = new Request.Builder()
                .url(endpoint)
                .header("Authorization", "Bearer " + API_KEY)
                .header("Content-Type", "application/json")
                .patch(RequestBody.create(MediaType.parse("application/json"), jsonBody))
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("Update User Response: " + response.body().string());
        }
    }

    public void deleteUser(int userId) throws IOException {
        String endpoint = WEAVY_SERVER + "/api/users/" + userId;

        Request request = new Request.Builder()
                .url(endpoint)
                .header("Authorization", "Bearer " + API_KEY)
                .delete()
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("Delete User Response: " + response.body().string());
        }
    }

    public static void main(String[] args) {
        WeavyUserApiClient weavyUserApiClient = new WeavyUserApiClient();

        try {
            //  Create User
            weavyUserApiClient.createUser("user-1", "John", "acme");

            //  List Users
            String userListResponse = weavyUserApiClient.listUsers();
            System.out.println("List of users: " + userListResponse);

            //  Get User Details
            String userDetailsResponse = weavyUserApiClient.getUserDetails(1);
            System.out.println("User Details: " + userDetailsResponse);

            //  Update User
            weavyUserApiClient.updateUser(1, "saman");

            //  Delete User
            weavyUserApiClient.deleteUser(1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
