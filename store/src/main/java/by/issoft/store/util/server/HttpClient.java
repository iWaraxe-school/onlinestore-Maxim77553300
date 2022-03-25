package by.issoft.store.util.server;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import static by.issoft.store.util.server.StoreServer.PASSWORD;
import static by.issoft.store.util.server.StoreServer.USER_NAME;
import static java.lang.String.format;

public class HttpClient {

    private static final String FORMAT = "%s:%s";

    public List<Category> getAll() {
        HttpURLConnection httpURLConnection = new HttpClient().getConnection("/categories", Method.GET);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(httpURLConnection.getInputStream(), new TypeReference<List<Category>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            httpURLConnection.disconnect();
        }
    }

    public void addProductToCart(Product product) {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpURLConnection httpURLConnection = new HttpClient().getConnection("cart", Method.POST);
        httpURLConnection.setDoOutput(true);
        try {
            OutputStream outputStream = httpURLConnection.getOutputStream();
            byte[] data = objectMapper.writeValueAsBytes(product);
            outputStream.write(data);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = bufferedReader.readLine()) != null) {
                response.append(responseLine.trim());
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            httpURLConnection.disconnect();
        }
    }

    private HttpURLConnection getConnection(String endPoint, Method method) {
        try {
            URL address = new URL("http", "localhost", 8080, endPoint);
            String credentials = Base64.getEncoder()
                    .encodeToString((format(FORMAT, USER_NAME, PASSWORD))
                            .getBytes(StandardCharsets.UTF_8));
            HttpURLConnection httpURLConnection = (HttpURLConnection) address.openConnection();
            httpURLConnection.setRequestMethod(method.name());
            httpURLConnection.setRequestProperty("Authorization", "Basic" + credentials);
            return httpURLConnection;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
