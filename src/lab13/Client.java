package lab13;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
	public static void write(String fileName, StringBuffer text) {
		// ќпредел€ем файл
		File file = new File(fileName);

		try {
			// если файла нет, то создаем его
			if (!file.exists()) {
				file.createNewFile();
			}

			// определ€ем переменную out типа PrintWriter, и создаем экземпл€р
			// объекта
			PrintWriter out = new PrintWriter(file.getAbsoluteFile());

			try {
				// с помощью метода print записываем строку text в файл
				out.print(text.toString());
			} finally {
				// закрываем файл, если не закрыть, то данные не запишутс€
				out.close();
			}
		} catch (IOException ewr) {

			System.out.println(ewr.getMessage());
		}
	}

	public static void main(String args[]) {
		try {
			Socket s = new Socket("localhost", 3456); // соединение с сервером
														// localhost, порт 3456
			InputStream is = s.getInputStream(); // получение потока дл€ чтени€
													// от сервера
			PrintWriter out1 = new PrintWriter(s.getOutputStream(), true);
			BufferedReader inu = new BufferedReader(new InputStreamReader(System.in));

			String password = "";
			try (Scanner input = new Scanner(System.in)) {

				System.out.print("¬ведите пароль:");
				password = input.nextLine().toString();
				System.out.println(password);
			}
			StringBuffer sendBuff = new StringBuffer();
			sendBuff.append(password);

			char[] mass = sendBuff.toString().toCharArray();
			ArrayList<Byte> byteArray = new ArrayList<>();
			for (char x : mass) {

				byteArray.add((byte) (x >> 8));
				byteArray.add((byte) x);
			}
			System.out.println(byteArray);
			for (int x : byteArray) {
				s.getOutputStream().write(x);
				 System.out.println(x);
			}

			ArrayList<Byte> arrayList = new ArrayList<>();

			Integer streamInt = 0;
			while (streamInt != -1) {
				streamInt = is.read();
				if (streamInt != -1) {

					arrayList.add(streamInt.byteValue());
				}
			}
			
			StringBuffer charReceive = new StringBuffer();
			int upByte;
			int downByte;
			int tempInt;
			for (int i = 0; i < (arrayList.size() - 2); i = i + 2) {
				upByte = arrayList.get(i);
				downByte = arrayList.get(i + 1);
				tempInt = downByte | (upByte << 8);
				charReceive.append((char) tempInt);

			}
			System.out.println(charReceive);

			Client.write("E:/2/out1.txt", charReceive);

			s.close(); // закрытие сокета
		} catch (UnknownHostException e) { // на тот случай если сервер не будет
											// найден
			e.printStackTrace(); // печать текста исключени€
		} catch (IOException e) { // на тот случай если возникнет ошибка при
									// обмене по сети
			e.printStackTrace(); // печать текста исключени€
		}
	}
}