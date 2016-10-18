package lab13;
import java.io.*;
    import java.net.*;
    public class Server {
      public static void main(String args[]) {
        try {
          ServerSocket ss = new ServerSocket(3456); //создаем серверный сокет
          System.out.println("Waiting...");
          Socket client=ss.accept(); //переходим в состояние ожидания соединений от клиента
          System.out.println("Connected"); // если мы дошли до этой строки, значит снами соединился клиент 
          client.getOutputStream().write(100); //отсылаем байт со значением 10
          client.close(); // закрываем соединение с клиентом
          ss.close(); // закрываем серверный сокет, после этого никто уже соединиться не сможет
        } catch (IOException e) { // на тот случай если возникнет ошибка при обмене по сети
          e.printStackTrace();  // печать текста исключения
        }
      }
    }