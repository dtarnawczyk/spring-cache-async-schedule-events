package org.spring.async.task4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
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

    @Autowired
    private TaskScheduler scheduler;

    public void schedule() {
        scheduler.schedule(() -> {
            try {
                String now = readHtml(new URL("http://onet.pl"));
                if (now.equals(lastResult)) {
                    LOG.info("not changed");
                } else {
                    lastResult = now;
                    LOG.info("changed");
                }
            } catch (IOException e) {
                LOG.error("error occurs", e);
            }
        }, new CronTrigger("0/10 * * * * ?"));
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
