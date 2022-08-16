import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class Tests {
  // определение буфера для хранения временных данных
  private static final byte[] buffer = new byte[4096 * 1024]; // буфер на 4 мб

  // создание метода copy() для копирования входного потока в выходной
  public static void copy(InputStream input, OutputStream output) throws Exception {
    int length;
    while ((length = input.read(buffer)) != -1) {
      output.write(buffer, 0, length);
    }
  }

  public static void archivator(String archName, String[] filesList) {
    try {
      // переменная ZipFile для чтения исходного архива
      ZipFile primaryArchive = new ZipFile(archName);
      // создаем выходной поток для записи файлов в .zip формат
      ZipOutputStream temporaryArchive = new ZipOutputStream(new FileOutputStream("temp.zip"));
      // создаем коллекцию элементов типа ZipEntry для хранения списка элементов
      // исходного архива
      Enumeration<? extends ZipEntry> primaryArchiveEntries = primaryArchive.entries();
      // проход по всем элементам коллекции
      while (primaryArchiveEntries.hasMoreElements()) {
        // в очередной zipEntry помещаем элемент из архива
        ZipEntry zipEntry = primaryArchiveEntries.nextElement();
        // создаем новые элементы во временном архиве
        temporaryArchive.putNextEntry(zipEntry);
        // в тех случаях, если текущий элемент архива не является директорией:
        if (!zipEntry.isDirectory()) {
          // копируем содержимое элемента исходного архива во временный
          copy(primaryArchive.getInputStream(zipEntry), temporaryArchive);
        }
        // освобождаем ресурсы путем закрытия вхождения
        temporaryArchive.closeEntry();
      }

      /* Добавляем дополнительные файлы в temporaryArchive */
      // проходим по всем файлам в списке на добавление
      for (int loopCounter = 0; loopCounter < filesList.length; loopCounter++) {
        // для каждого файла создаем свой объект ZipEntry (элемент архива)
        ZipEntry e = new ZipEntry(filesList[loopCounter]);
        // добавляем элементы по временный архив
        temporaryArchive.putNextEntry(e);
        // чтение потока сырых данных из очередного файла на добавление
        FileInputStream fileInputStream = new FileInputStream(filesList[loopCounter]);

        int length; // создаем счетчик для подсчета длины массива
        byte[] buffer = new byte[1024]; // создаем массив на 1кб

        // до тех пор, пока не дойдем до конца буферного массива, читаем из него данные
        // и переносим в файл временного архива
        while ((length = fileInputStream.read(buffer)) > 0) {
          temporaryArchive.write(buffer, 0, length);
        }
        // закрываем файлы и потоки
        fileInputStream.close(); // закрытия потока данных из файла
        temporaryArchive.closeEntry(); // закрытие zipEntry
      }

      primaryArchive.close(); // закрываем архив, из которого происходило чтение данных
      temporaryArchive.close(); // закрываем временный архив

      // удаляем первичный архив
      File archForDel = new File(archName);
      if (archForDel.exists())
        archForDel.delete();

      // переименовываем временный архив именем первичного архива
      File archForRename = new File("temp.zip");
      if (archForRename.exists()) {
        archForRename.renameTo(archForDel);
      }

    } catch (Exception ex) {
      System.out.println("Exception while working with archives - " + ex);
    }
  }
}