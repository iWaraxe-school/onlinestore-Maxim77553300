package by.issoft.store.util.server.handler;

import by.issoft.domain.Category;
import by.issoft.store.dao.CategoryDao;
import by.issoft.store.populator.HttpPopulator;
import by.issoft.store.populator.Populator;
import by.issoft.store.service.CategoryServiceImpl;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class CategoryHandler implements HttpHandler {
    private final Populator populator = new HttpPopulator();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Headers headers = exchange.getRequestHeaders();
        StringBuffer response = new StringBuffer();
        for (Category category : populator.getAllCategories()) {
            response.append(category + ":");
        }
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.toString().getBytes());
        os.close();
    }
}
