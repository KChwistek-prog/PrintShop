package com.printshopmanagement.application.emaillabs;

import com.printshopmanagement.application.scheduler.MailBuilder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailLabs {

   //  public static void main(String[] args) {
    public void send(MailBuilder mailBuilder){
        try {
            // setup variables
            String appKey = "31e1b1d6d387c78efcca497e598b0501a6441b62";
            String secretKey = "49411c571a6bbf36377e3f04b5865c1d06a26822";

            String userpass = appKey + ":" + secretKey;
            String basicAuth = "Basic "
                    + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes(StandardCharsets.UTF_8));

            // set params
            HashMap<String, String> params = new HashMap<>();
            params.put("to[address]", "vescus@gmail.com");
            params.put("smtp_account", "1.vescus.smtp");
            params.put("subject",mailBuilder.prepareSubject());
            params.put("html", mailBuilder.prepareData());
            params.put("text", mailBuilder.prepareData());
            params.put("from", "vescus@gmail.com");

            // build query
            StringBuilder query = new StringBuilder();
            boolean first = true;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (first)
                    first = false;
                else
                    query.append("&");
                query.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
                query.append("=");
                query.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
            }

            // setup connection
            URL url = new URL("https://api.emaillabs.net.pl/api/new_sendmail");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", basicAuth);
            connection.setDoOutput(true);

            // send data
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(query.toString());
            out.close();

            // read output
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            System.out.print(in.readLine());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}