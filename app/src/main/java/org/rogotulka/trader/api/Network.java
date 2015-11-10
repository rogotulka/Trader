package org.rogotulka.trader.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class Network {

    private static final int TIMEOUT = 20000;
    private static final String METHOD_GET = "GET";
    private HttpURLConnection connection;

    public InputStream getInputStream(String urlString) throws IOException {
        URL url = new URL(urlString);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent", "Chrome 41.0.2228.0");
        connection.setConnectTimeout(TIMEOUT);
        connection.setRequestMethod(METHOD_GET);
        connection.setDoInput(true);
        return connection.getInputStream();
    }

    public void close() {
        if (connection != null) {
            connection.disconnect();
        }
    }
}
