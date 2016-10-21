package lab13;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
	public static void write(String fileName, StringBuffer text) {
		// ���������� ����
		File file = new File(fileName);

		try {
			// ���� ����� ���, �� ������� ���
			if (!file.exists()) {
				file.createNewFile();
			}

			// ���������� ���������� out ���� PrintWriter, � ������� ���������
			// �������
			PrintWriter out = new PrintWriter(file.getAbsoluteFile());

			try {
				// � ������� ������ print ���������� ������ text � ����
				out.print(text.toString());
			} finally {
				// ��������� ����, ���� �� �������, �� ������ �� ���������
				out.close();
			}
		} catch (IOException ewr) {

			System.out.println(ewr.getMessage());
		}
	}

	public static void main(String args[]) {
		try {
			Socket s = new Socket("localhost", 3456); // ���������� � ��������
														// localhost, ���� 3456
			InputStream is = s.getInputStream(); // ��������� ������ ��� ������
													// �� �������
			PrintWriter out1 = new PrintWriter(s.getOutputStream(), true);
			BufferedReader inu = new BufferedReader(new InputStreamReader(System.in));

			String password = "";
			try (Scanner input = new Scanner(System.in)) {

				System.out.print("������� ������:");
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

			s.close(); // �������� ������
		} catch (UnknownHostException e) { // �� ��� ������ ���� ������ �� �����
											// ������
			e.printStackTrace(); // ������ ������ ����������
		} catch (IOException e) { // �� ��� ������ ���� ��������� ������ ���
									// ������ �� ����
			e.printStackTrace(); // ������ ������ ����������
		}
	}
}