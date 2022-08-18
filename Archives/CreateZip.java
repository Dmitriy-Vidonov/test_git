import java.util.zip.*;
import java.io.FileOutputStream;
import java.io.FileInputStream;

class CreateZip {
  public static void main(String[] args) {
    try {
      // test.zip - несуществующий архив
      // существующий архив будет перезаписан!
      ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream("test.zip"));

      // файл для добавления в создаваемый архив
      String file = "text1.txt"; // имя файла, который будем добавлять в архив
      ZipEntry zipEntry = new ZipEntry(file); // создание нового zipEntry с именем file

      // добавляем новую сущность (заголовок) в архив
      zipOutputStream.putNextEntry(zipEntry);

      // копируем (записываем) исходный файл в архив
      FileInputStream fileInputStream = new FileInputStream(file); // чтение потока сырых данных из файла
      int length; // создаем счетчик
      byte[] buffer = new byte[1024]; // создаем массив на 1кб

      // в fileInputStream - у нас поток сырых данных. Через read() мы пишем его в
      // buffer
      // и по сути length - содержит длину массива buffer
      while ((length = fileInputStream.read(buffer)) > 0) {
        zipOutputStream.write(buffer, 0, length);
      }
      // закрываем файлы и потоки
      fileInputStream.close(); // закрытия потока данных из файла
      zipOutputStream.closeEntry(); // закрытие zipEntry
      zipOutputStream.close(); // закрытие потока записи файлов в архив

    } catch (Exception ex) {
      System.out.println("Exception during creating new archive with file - " + ex);
    }
  }
}