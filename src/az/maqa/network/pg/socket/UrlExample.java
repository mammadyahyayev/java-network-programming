package az.maqa.network.pg.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlExample {

	public static void main(String[] args) {

		try {
			URL url = new URL("https://finance.yahoo.com/quote/ORCL?ltr=1");
			URLConnection urlConnection = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String inputLine = "";
			String pattern = "<span class=\"Trsdu(0.3s) Fw(b) Fz(36px) Mb(-4px) D(ib)\" data-reactid=\"32\">(.+?)</span>";
			Pattern r = Pattern.compile(pattern);
			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
				if (inputLine.contains("Trsdu(0.3s) Fw(b) Fz(36px) Mb(-4px) D(ib)")) {
					Matcher m = r.matcher(inputLine);
					if (m.find()) {
						System.out.println("==================>Price:" + m.group(1));
					}
				}
			}
			in.close();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
