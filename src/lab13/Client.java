package lab13;
import java.io.*;
 import java.net.*;
 public class Client {
   public static void main(String args[]) {
     try {
       Socket s = new Socket("localhost", 3456); //соединение с сервером localhost, порт 3456
       InputStream is = s.getInputStream(); //получение потока для чтения от сервера
       System.out.println("Read: "+is.read()); // получение байта от клиента
       s.close(); // закрытие сокета
     } catch (UnknownHostException e) { // на тот случай если сервер не будет найден
       e.printStackTrace(); // печать текста исключения
     } catch (IOException e) { // на тот случай если возникнет ошибка при обмене по сети
       e.printStackTrace(); // печать текста исключения
     }
   }
 }