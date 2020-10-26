package lab4;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerChat {
	private static String message = "";

	public static void main(String[] args) {
		try {
			final ServerSocket svr = new ServerSocket(9998);
			System.out.println("Chatroom server starting...");
			System.out.println("Hãy nói gì đó thân mật với client <3 ");
			final ArrayList<Socket> listSoc = new ArrayList<Socket>();
			while (true) {
				final Socket soc = svr.accept();
				listSoc.add(soc);
				System.out.println("number of client is " + listSoc.size());
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Scanner in = new Scanner(soc.getInputStream());
							while (in.hasNextLine()) {
								message = in.nextLine();
								for (Socket s : listSoc) {
									if (s == null)
										listSoc.remove(s);
									else if (!s.equals(soc)) {
										PrintWriter out = new PrintWriter(s.getOutputStream(), true);
										out.println(message);
									}
								}
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}