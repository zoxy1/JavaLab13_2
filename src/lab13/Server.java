package lab13;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.io.*;

public class Server {
	public static String readFile(String fileName) {

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

		return stringBuffer.toString();
	}

	public static void main(String[] ar) {
		int port = 1212; // порт, который будет слушать серверная программа
		try {
			ServerSocket ss = new ServerSocket(port); // создаем серверный сокет
			System.out.println("Создаем серверный сокет IP адрес:" + InetAddress.getByName(null) + " порт:" + port);

			// и привязываем его к вышеуказанному порту
			System.out.println("Waiting..."); // переходим в состояние ожидания
												// соединений от клиента

			Socket client = ss.accept(); // заставляем сервер ждать подключений
											// и выводим сообщение когда кто-то
											// связался с сервером
			System.out.println("Connected"); // если мы дошли до этой строки,
												// значит снами соединился
												// клиент
			System.out.println();

			// Берем входной и выходной потоки сокета, теперь можем получать и
			// отсылать данные клиенту.
			InputStream sin = client.getInputStream();
			OutputStream sout = client.getOutputStream();

			// Конвертируем потоки в другой тип, чтоб легче обрабатывать
			// текстовые сообщения.
			DataInputStream in = new DataInputStream(sin);
			DataOutputStream out = new DataOutputStream(sout);

			String line = null;
			while (true) {
				line = in.readUTF(); // ожидаем пока клиент пришлет строку
										// текста.

				if (line.equals("12345")) {
					System.out.println("Принятый пароль:" + line + " верный");
					out.writeUTF(Server.readFile("E:/2/in1.txt"));
					client.close(); // закрываем соединение с клиентом
					ss.close(); // закрываем серверный сокет, после этого никто
								// уже соединиться не сможет
					System.exit(0); // выходим из программы
				} else {
					System.out.println("Принятый пароль:" + line + "  не верный");
					out.writeUTF("Пароль не верный, повторите попытку");
				}

			}
		} catch (Exception x) {
			x.printStackTrace();
		}
	}
}