package by.issoft.store.util.server.handler;


import by.issoft.domain.Product;
import by.issoft.store.populator.HttpPopulator;
import by.issoft.store.populator.Populator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CartHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        InputStream requestBody = exchange.getRequestBody();
        ObjectMapper objectMapper = new ObjectMapper();
        Product product = objectMapper.readValue(requestBody, Product.class);
        Populator populator = new HttpPopulator();
        populator.addToCart(product);
        String response = "Product is in the cart ";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.toString().getBytes());
        os.close();

    }
}
