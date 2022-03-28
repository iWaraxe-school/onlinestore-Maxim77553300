package by.issoft.store.util.server.handler;

import by.issoft.domain.Product;
import by.issoft.store.populator.PopulatorBd;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class Handler implements HttpHandler {
     private final static PopulatorBd populator = new PopulatorBd();
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Headers headers = exchange.getRequestHeaders();
        StringBuilder response = new StringBuilder();
            for(Product product : populator.getAllGoods()){
                response.append(product + ":" );
            }
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.toString().getBytes());
        os.close();
    }
}

