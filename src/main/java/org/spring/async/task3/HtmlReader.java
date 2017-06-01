package org.spring.async.task3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class HtmlReader {

    private static final Logger LOG = LoggerFactory.getLogger(HtmlReader.class);

    private String lastResult = "";

    @Scheduled(cron = "0/30 * * * * ?")
    public void getOnet() throws IOException {
        String result = readHtml(new URL("http://www.onet.pl"));

        if(result.equals(lastResult)){
            LOG.info("Onet not changed");
        } else {
            lastResult = result;
            LOG.info("Onet changed");
        }
    }

    public String readHtml(URL url) throws IOException {

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Http response: " + conn.getResponseCode());
        }

        String readContent = readContent(conn);
        conn.disconnect();

        return readContent;
    }

    private String readContent(HttpURLConnection conn) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())))) {
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = br.readLine()) != null) {
                result.append(line).append('\n');
            }

            return result.toString();
        }
    }


}
