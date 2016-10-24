package lab13;

import java.net.*;
import java.io.*;

public class Client {

	public static void writeFile(String fileName, String text) {
		// ���������� ����
		File file = new File(fileName);

		try {
			// ���� ����� ���, �� ������� ���
			if (!file.exists()) {
				file.createNewFile();
			}

			// ���������� ���������� out ���� PrintWriter, � ������� ��������
			// �������
			PrintWriter out = new PrintWriter(file.getAbsoluteFile());

			try {
				// � ������� ������ print ���������� ������ text � ����
				out.print(text);
			} finally {
				// ��������� ����, ���� �� �������, �� ������ �� ��������
				out.close();
			}
		} catch (IOException ewr) {

			System.out.println(ewr.getMessage());
		}
	}

	public static void main(String[] ar) {
		int serverPort = 1212; // ��������� ���� �� ������� ����� ��������� ��
								// �������
		String address = "127.0.0.1"; // ��� IP-����� ����������, ���
										// ����������� ��������� ���������.

		try {
			InetAddress ipAddress = InetAddress.getByName(address); // �������
																	// ������,
																	// �������
																	// ����������
																	// �������������
																	// IP-�����.
			System.out.println("������� ����� IP �����:" + address + " ����:" + serverPort);
			Socket socket = new Socket(ipAddress, serverPort); // ������� �����
																// ���������
																// IP-����� �
																// ���� �������.

			// ����� ������� � �������� ������ ������, ������ ����� �������� �
			// �������� ������ ��������.
			InputStream sin = socket.getInputStream();
			OutputStream sout = socket.getOutputStream();

			// ������������ ������ � ������ ���, ���� ����� ������������
			// ��������� ���������.
			DataInputStream in = new DataInputStream(sin);
			DataOutputStream out = new DataOutputStream(sout);

			// ������� ����� ��� ������ � ����������.
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
			String line = null;

			while (true) {
				System.out.println();
				System.out.print("��� ��������� ����� � ������� ������� ������ � ������� \"Enter\":");
				System.out.println();
				line = keyboard.readLine(); // ���� ���� ������������ ������
											// ������ � ������ ������ Enter.
				out.writeUTF(line); // �������� ��������� ������ ������ �������.
				out.flush(); // ���������� ����� ��������� �������� ������.

				line = in.readUTF(); // ���� ���� ������ �������, ���� ������
										// ������ ������� ����,
				// ���� ���, �� ������� ��� ������ �� ������, ���������� ��� ���
				if (line.equals("������ �� ������, ��������� �������")) {

					System.out.println(line);
				} else {
					String path = "E:/2/out1.txt";
					System.out.println("���� ������, �������� �� ���� �� ������:" + path + " ���������� �����:");
					System.out.println(line);
					Client.writeFile(path, line);
					socket.close();
					System.exit(0);
				}
			}
		} catch (UnknownHostException e) { // �� ��� ������ ���� ������ �� �����
											// ������
			e.printStackTrace(); // ������ ������ ����������
		} catch (IOException e) { // �� ��� ������ ���� ��������� ������ ���
									// ������ �� ����
			e.printStackTrace(); // ������ ������ ����������
		}
	}
}