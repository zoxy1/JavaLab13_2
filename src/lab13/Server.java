package lab13;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {

	public static StringBuffer read(String fileName) {

		StringBuffer stringBuffer = new StringBuffer();
		// использование try-with-resources
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))) {
			String line;
			while ((line = reader.readLine()) != null) {

				stringBuffer.append(line + "\n");

			}

			reader.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return stringBuffer;
	}

	public static void main(String args[]) {
		try {
			ServerSocket ss = new ServerSocket(3456); // создаем серверный сокет

			Socket client;
			try (Scanner input = new Scanner(System.in)) {
				String password;
				begin: while (true) {

					System.out.print("Введите пароль:");
					password = input.nextLine();
					if (password.toString().equals("12345") == true) {
						break begin;
					} else {
						System.out.println("Введен не правильный пароль, попробуйте еще раз");
					}

					// Runtime.getRuntime().exec("cls");
				}
			}
			//

			System.out.println("Waiting...");
			client = ss.accept();
			// соединений от клиента
			System.out.println("Connected"); // если мы дошли до этой строки,
												// значит снами соединился
												// клиент
			System.out.println(Server.read("E:/2/in.txt"));
			client.getOutputStream().write(100); // отсылаем байт со значением
													// 10
			client.close(); // закрываем соединение с клиентом
			ss.close(); // закрываем серверный сокет, после этого никто уже
						// соединиться не сможет
		} catch (IOException e) { // на тот случай если возникнет ошибка при
									// обмене по сети
			e.printStackTrace(); // печать текста исключения
		}
	}
}