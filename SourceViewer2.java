package URLClass;

import java.net.*;
import java.io.*;

public class SourceViewer2 {
	public static void main(String[] args) {
		if (args.length > 0) {
			try {

				URL u = new URL("https://github.com/");
				URLConnection uc = u.openConnection();
				InputStream raw = uc.getInputStream();
				InputStream buffer = new BufferedInputStream(raw);

				Reader r = new InputStreamReader(buffer);
				int c;
				while ((c = r.read()) != -1) {
					System.out.print((char) c);
				}
			} catch (MalformedURLException ex) {
				System.err.println("https://github.com/" + " is not a parseable URL");
			} catch (IOException ex) {
				System.err.println(ex);
			}
		}
	}
}
