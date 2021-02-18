package x.snowroller;


import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Request {
    private String requestedUrl = "";
    private String requestType = "";
    private Map<String, String> urlParams = new HashMap<>();
    private Map<String, String> headers = new HashMap<>();
    private byte[] body;

    public void parseUrlParams(String s) {
        var params = s.split("&");
        for (var param : params) {
            var key = URLDecoder.decode(param.split("=")[0], StandardCharsets.UTF_8);
            var value = URLDecoder.decode(param.split("=")[1], StandardCharsets.UTF_8);
            urlParams.put(key, value);
        }
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public String getHeader(String headerName) {
        return headers.getOrDefault(headerName, "");
    }

    public Map<String, String> getUrlParams() {
        return urlParams;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public int getContentLength() {
        return Integer.parseInt(headers.getOrDefault("Content-Length", "0"));
    }

    public void setRequestUrl(String requestUrl) {
        if (requestUrl.contains("?")) {
            requestedUrl = requestUrl.split("[?]")[0];
            parseUrlParams(requestUrl.split("[?]")[1]);
        }
        else
            requestedUrl = requestUrl;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public void setBody(byte[] bodyContent) {
        body = bodyContent;
    }

    public String getUrl() {
        return requestedUrl;
    }

    public String getType() {
        return requestType;
    }

    public byte[] getBody() {
        return body;
    }

    public boolean isGetRoute(String url){
        return "GET".equals(requestType) && requestedUrl.equals(url);
    }
    public boolean isPostRoute(String url){
        return "POST".equals(requestType) && requestedUrl.equals(url);
    }
}
