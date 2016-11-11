package osrsItemGet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class OSRSItemGetter {

	public static void main(String[] args) {
		URL url;
		URLConnection con;
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		BufferedReader brtxt = null;
		int itemID;
		String[] splitLine = null;
		
//		System.out.println("Enter item num:");
//		Scanner scanner = new Scanner(System.in);
//		itemID = scanner.nextInt();
//		scanner.close();
		
		try {
			brtxt = new BufferedReader(new FileReader("lazyInputItems.txt"));
			String nextLine;
			while ((nextLine = brtxt.readLine()) != null) {
				itemID = Integer.parseInt(nextLine);
				// read in even numbers, since id then item name
				nextLine = brtxt.readLine();
				System.out.println(nextLine);
				url = new URL("https://api.rsbuddy.com/grandExchange?a=guidePrice&i="
						+ itemID);
				// Get inputstream thru URL Connection
				con = url.openConnection();
				is = con.getInputStream();
				isr = new InputStreamReader(is);
				br = new BufferedReader(isr);

				String line = br.readLine();
				if (line != null)
					splitLine = line.split(",");
				
				for (int i = 0; i < splitLine.length; i++) {
					System.out.println(splitLine[i]);
					// Get rid of everything except the numbers
					//System.out.println(splitLine[i].replaceAll("\\D",  ""));
				}
				System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				} else if (isr != null) {
					isr.close();
				} else if (is != null) {
					is.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	} // main
} // class
