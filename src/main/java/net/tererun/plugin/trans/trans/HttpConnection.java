package net.tererun.plugin.trans.trans;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection {

    private String result;

    public HttpConnection(String sendUrl) {
        this.result = "";

        HttpURLConnection con = null;
        StringBuffer result = new StringBuffer();
        try {
            URL url = new URL(sendUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            int status = con.getResponseCode();
            if (status == 200) {
                InputStream in = con.getInputStream();
                String encoding = "UTF-8";
                if (null == encoding)
                    encoding = "UTF-8";

                InputStreamReader inReader = new InputStreamReader(in, encoding);
                BufferedReader bufReader = new BufferedReader(inReader);

                String line = null;
                while ((line = bufReader.readLine()) != null)
                    result.append(line);

                bufReader.close();
                inReader.close();
                in.close();
            } else {
                System.out.println(status);
            }
        } catch (Exception err) {
            err.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        System.out.println(result.toString());
        this.result = result.toString();
    }

    public HttpConnection(String sendUrl, String json) {
        this.result = "";
        HttpURLConnection con = null;
        StringBuffer result = new StringBuffer();
        try {
            URL url = new URL(sendUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept-Language", "jp");
            con.setRequestProperty("Content-Type", "application/JSON; charset=utf-8");
            con.setRequestProperty("Content-Length", String.valueOf(json.length()));
            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
            out.write(json);
            out.flush();
            con.connect();

            int status = con.getResponseCode();
            if (status == 200) {
                InputStream in = con.getInputStream();
                String encoding = con.getContentEncoding();
                if (null == encoding)
                    encoding = "UTF-8";

                InputStreamReader inReader = new InputStreamReader(in, encoding);
                BufferedReader bufReader = new BufferedReader(inReader);

                String line = null;
                while ((line = bufReader.readLine()) != null)
                    result.append(line);

                bufReader.close();
                inReader.close();
                in.close();
            } else {
                System.out.println(status);
            }
        } catch (Exception err) {
            err.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        this.result = result.toString();
    }

    public String getResult() {
        return this.result;
    }
}