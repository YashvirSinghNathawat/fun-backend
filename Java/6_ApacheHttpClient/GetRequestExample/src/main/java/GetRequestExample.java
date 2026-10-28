import java.io.Closeable;
import java.io.IOException;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.util.Timeout;

public class GetRequestExample {

    public static void main(String[] args) throws IOException, ParseException {

        RequestConfig requestConfig = prepareRequestConfiguration();
        getRequest(requestConfig);
        postRequest(requestConfig);
    }

    private static RequestConfig prepareRequestConfiguration() {
        return RequestConfig.custom()
                .setConnectionRequestTimeout(Timeout.ofSeconds(30)) // 30 seconds
                .setResponseTimeout(Timeout.ofSeconds(30)) // 30 seconds
                .build();
    }

    private static void postRequest(RequestConfig requestConfig) throws IOException, ParseException {
        // The client is responsible for executing HTTP requests. It uses a connection
        // manager to manage the connections needed for communication.
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost postRequest = new HttpPost("https://httpbin.org/post");
        postRequest.setConfig(requestConfig);
        String payload = """
        {
          "productId": "P100",
          "name": "Laptop",
          "price": 75000
        }
        """;
        postRequest.setEntity(new StringEntity(payload, ContentType.APPLICATION_JSON));
        httpClient.execute(postRequest, response -> {
            int code = response.getCode();
            System.out.println("POST Response Code: " + code);
            if (code >= 200 && code < 300) {
                handleSuccessResponse(response);
            } else {
                handleErrorResponse(response);
            }
            return null;
        });
    }

    private static void getRequest(RequestConfig requestConfig) throws IOException, ParseException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = prepareRequest();
        request.setConfig(requestConfig);
        httpClient.execute(request, response -> {
            int code = response.getCode();
            System.out.println("GET Response Code: " + code);
            if (code >= 200 && code < 300) {
                handleSuccessResponse(response);
            } else {
                handleErrorResponse(response);
            }
            return null;
        });
    }
    private static HttpGet prepareRequest() {
        HttpGet request = new HttpGet("https://httpbin.org/get");
        // Accept: application/json tells the server that the client expects JSON, but the server may return another format or a 406 Not Acceptable response if JSON is unsupported.
        request.addHeader(HttpHeaders.ACCEPT, "application/json");
        return request;
    }

    private static void handleSuccessResponse(ClassicHttpResponse response) throws IOException, ParseException {
        HttpEntity entity = response.getEntity();
        if (entity != null && entity.getContentType() != null) {
            ContentType contentType = ContentType.parse(entity.getContentType());

            // Read Response media type
            System.out.println(
                    "Media Type: " + contentType.getMimeType());

            System.out.println(
                    "Charset: " + contentType.getCharset());
            // Read Response Header
            Header[] headers = response.getHeaders(HttpHeaders.CONTENT_TYPE);
            for (Header header : headers) {
                System.out.println("Header: " + header.getName() + " = " + header.getValue());
            }
            // Read Response body
            String responseBody = EntityUtils.toString(entity);
            System.out.println("Response Length: " + responseBody.length());
        }
    }

    private static void handleErrorResponse(ClassicHttpResponse response) throws IOException, ParseException {
        String responseBody = EntityUtils.toString(response.getEntity());
        System.err.println("Error: " + response.getCode() + " - " + responseBody);
    }
}
