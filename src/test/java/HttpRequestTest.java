import org.example.index.http.HttpParser;
import org.example.index.http.HttpRequest;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.testng.AssertJUnit.assertEquals;

public class HttpRequestTest {

    @Test
    void parsesMethodPathAndHeaders() throws IOException {
        String raw =
                "GET /health HTTP/1.1\r\n" +
                        "Host: localhost:4000\r\n" +
                        "User-Agent: test\r\n" +
                        "\r\n";

        InputStream in = new ByteArrayInputStream(raw.getBytes(StandardCharsets.UTF_8));

        HttpRequest req = new HttpParser().parse(in);

        assertEquals("GET", req.getMethod());
        assertEquals("/health", req.getPath());
        assertEquals("localhost:4000", req.getHeaders().get("Host"));
    }


}
