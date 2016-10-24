package lab13;

import java.net.*;
import java.io.*;

public class Client {

	public static void writeFile(String fileName, String text) {
		// определяем файл
		File file = new File(fileName);

		try {
			// если файла нет, то создаем его
			if (!file.exists()) {
				file.createNewFile();
			}

			// определяем переменную out типа PrintWriter, и создаем экземпл¤р
			// объекта
			PrintWriter out = new PrintWriter(file.getAbsoluteFile());

			try {
				// с помощью метода print записываем строку text в файл
				out.print(text);
			} finally {
				// закрываем файл, если не закрыть, то данные не запишутс¤
				out.close();
			}
		} catch (IOException ewr) {

			System.out.println(ewr.getMessage());
		}
	}

	public static void main(String[] ar) {
		int serverPort = 1212; // указываем порт на котором висит программа на
								// сервере
		String address = "127.0.0.1"; // это IP-адрес компьютера, где
										// исполняется серверная программа.

		try {
			InetAddress ipAddress = InetAddress.getByName(address); // создаем
																	// объект,
																	// который
																	// отображает
																	// вышеописанный
																	// IP-адрес.
			System.out.println("Создаем сокет IP адрес:" + address + " порт:" + serverPort);
			Socket socket = new Socket(ipAddress, serverPort); // создаем сокет
																// используя
																// IP-адрес и
																// порт сервера.

			// Берем входной и выходной потоки сокета, теперь можем получать и
			// отсылать данные клиентом.
			InputStream sin = socket.getInputStream();
			OutputStream sout = socket.getOutputStream();

			// Конвертируем потоки в другой тип, чтоб легче обрабатывать
			// текстовые сообщения.
			DataInputStream in = new DataInputStream(sin);
			DataOutputStream out = new DataOutputStream(sout);

			// Создаем поток для чтения с клавиатуры.
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
			String line = null;

			while (true) {
				System.out.println();
				System.out.print("Для получения файла с сервера введите пароль и нажмите \"Enter\":");
				System.out.println();
				line = keyboard.readLine(); // ждем пока пользователь введет
											// пароль и нажмет кнопку Enter.
				out.writeUTF(line); // отсылаем введенную строку текста серверу.
				out.flush(); // заставляем поток закончить передачу данных.

				line = in.readUTF(); // ждем пока сервер ответит, если пароль
										// верный пришлет файл,
				// если нет, то ответит что пароль не верный, попробуйте еще раз
				if (line.equals("Пароль не верный, повторите попытку")) {

					System.out.println(line);
				} else {
					String path = "E:/2/out1.txt";
					System.out.println("Файл принят, сохранен на диск по адресу:" + path + " содержание файла:");
					System.out.println(line);
					Client.writeFile(path, line);
					socket.close();
					System.exit(0);
				}
			}
		} catch (UnknownHostException e) { // на тот случай если сервер не будет
											// найден
			e.printStackTrace(); // печать текста исключения
		} catch (IOException e) { // на тот случай если возникнет ошибка при
									// обмене по сети
			e.printStackTrace(); // печать текста исключения
		}
	}
}