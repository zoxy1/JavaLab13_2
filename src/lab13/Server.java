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

			Socket client = null;

			
			System.out.println("Waiting...");
			client = ss.accept();
			// ���������� �� �������
			System.out.println("Connected"); // ���� �� ����� �� ���� ������,

			/*BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			ArrayList<Byte> arrayList = new ArrayList<>(); // ������ �����
															// ����������
			Integer streamInt = 0;
			while (streamInt != -1) {
				streamInt = in.read();

				if (streamInt != -1) {
					arrayList.add(streamInt.byteValue());
				}
			}
			StringBuffer charReceive = new StringBuffer();

			int upByte;
			int downByte;
			int tempInt;
			for (int i = 0; i < (arrayList.size() - 1); i = i + 2) {
				upByte = arrayList.get(i);
				downByte = arrayList.get(i + 1);
				tempInt = downByte | (upByte << 8);
				charReceive.append((char) tempInt);

			}*/
			//if (charReceive.toString().equals("12345")) {
			if (true) {	
				
				
				 
				
				   /*StringBuffer readBuff = new StringBuffer();
				  
				  readBuff.append(Server.read("E:/2/in1.txt"));
				  
				  char[] mass = readBuff.toString().toCharArray(); 
				  ArrayList<Byte>byteArray = new ArrayList<>(); for (char x : mass) {
				  
				  byteArray.add((byte) (x >> 8)); 
				  byteArray.add((byte) x); }
				  
				  System.out.println(byteArray);
				  
				  for (int x : byteArray) { 
					  client.getOutputStream().write(x); 
				  //System.out.println(x);
				  }*/
				
				  System.out.println("�������� ������ ������:" );
			
			
			} 
			else {
				System.out.println("�������� ������ �� ������:" );
			}
			// InputStream is = ss.getInputStream(); // ������

			/*
			 * for (int x : byteArray) { client.getOutputStream().write(x); //
			 * 10 }
			 */

			client.close(); // ��������� ���������� � ��������
			ss.close(); // ��������� ��������� �����, ����� ����� ����� ���
						// ����������� �� ������
		} catch (IOException e) { // �� ��� ������ ���� ��������� ������ ���
									// ������ �� ����
			e.printStackTrace(); // ������ ������ ����������
		}
	}
}