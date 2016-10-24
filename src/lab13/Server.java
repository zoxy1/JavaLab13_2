package lab13;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.io.*;

public class Server {
	public static String readFile(String fileName) {

		StringBuffer stringBuffer = new StringBuffer();
		// ������������� try-with-resources
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
		int port = 1212; // ����, ������� ����� ������� ��������� ���������
		try {
			ServerSocket ss = new ServerSocket(port); // ������� ��������� �����
			System.out.println("������� ��������� ����� IP �����:" + InetAddress.getByName(null) + " ����:" + port);

			// � ����������� ��� � �������������� �����
			System.out.println("Waiting..."); // ��������� � ��������� ��������
												// ���������� �� �������

			Socket client = ss.accept(); // ���������� ������ ����� �����������
											// � ������� ��������� ����� ���-��
											// �������� � ��������
			System.out.println("Connected"); // ���� �� ����� �� ���� ������,
												// ������ ����� ����������
												// ������
			System.out.println();

			// ����� ������� � �������� ������ ������, ������ ����� �������� �
			// �������� ������ �������.
			InputStream sin = client.getInputStream();
			OutputStream sout = client.getOutputStream();

			// ������������ ������ � ������ ���, ���� ����� ������������
			// ��������� ���������.
			DataInputStream in = new DataInputStream(sin);
			DataOutputStream out = new DataOutputStream(sout);

			String line = null;
			while (true) {
				line = in.readUTF(); // ������� ���� ������ ������� ������
										// ������.

				if (line.equals("12345")) {
					System.out.println("�������� ������:" + line + " ������");
					out.writeUTF(Server.readFile("E:/2/in1.txt"));
					client.close(); // ��������� ���������� � ��������
					ss.close(); // ��������� ��������� �����, ����� ����� �����
								// ��� ����������� �� ������
					System.exit(0); // ������� �� ���������
				} else {
					System.out.println("�������� ������:" + line + "  �� ������");
					out.writeUTF("������ �� ������, ��������� �������");
				}

			}
		} catch (Exception x) {
			x.printStackTrace();
		}
	}
}