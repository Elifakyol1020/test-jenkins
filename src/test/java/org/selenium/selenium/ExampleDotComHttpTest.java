// java
package org.selenium.selenium;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.net.http.*;
import java.net.URI;
import java.time.Duration;

class ExampleDotComHttpTest {

    @Test
    void exampleDotCom_accessible_varOrYok() throws Exception {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://example.com"))
                .GET()
                .timeout(Duration.ofSeconds(10))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        boolean accessible = response.statusCode() == 200 && response.body().contains("Example Domain");
        if (accessible) {
            System.out.println("var");
        } else {
            System.out.println("yok");
        }

        assertTrue(accessible, "https://example.com erişilebilir olmalı, status=" + response.statusCode());
    }
}
