package Lab01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class ReadXML {
    public static void readXML(String urlString) throws IOException {
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("ISO-8859-2")));

        String inputLine = "";
        StringBuffer stringBuffer = new StringBuffer();
        while ((inputLine = bufferedReader.readLine()) != null){
            stringBuffer.append(inputLine);
        }

        bufferedReader.close();

        ParseXML.parseXML(stringBuffer);
    }
}
