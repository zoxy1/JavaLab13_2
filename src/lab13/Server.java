package lab13;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import sun.security.util.Password;

public class Server {

	public static StringBuffer read(String fileName) {

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

		return stringBuffer;
	}

	public static void main(String args[]) {
		try {
			ServerSocket ss = new ServerSocket(3456); // ������� ��������� �����

			Socket client;
			String password = "";
			try (Scanner input = new Scanner(System.in)) {

				System.out.print("������� ������:");
				password = input.nextLine().toString();

			}
			StringBuffer sendBuff = new StringBuffer();
			sendBuff.append(password + "\n");
			sendBuff.append(Server.read("E:/2/in1.txt"));

			char[] mass = sendBuff.toString().toCharArray();
			ArrayList<Byte> byteArray = new ArrayList<>();
			for (char x : mass) {

				byteArray.add((byte) (x >> 8));
				byteArray.add((byte) x);
			}
			System.out.println(byteArray);

			System.out.println("Waiting...");
			client = ss.accept();
			// ���������� �� �������
			System.out.println("Connected"); // ���� �� ����� �� ���� ������,
												// ������ ����� ����������
												// ������

			for (int x : byteArray) {
				client.getOutputStream().write(x); // 10
			}

			client.close(); // ��������� ���������� � ��������
			ss.close(); // ��������� ��������� �����, ����� ����� ����� ���
						// ����������� �� ������
		} catch (IOException e) { // �� ��� ������ ���� ��������� ������ ���
									// ������ �� ����
			e.printStackTrace(); // ������ ������ ����������
		}
	}
}