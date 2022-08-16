import java.util.zip.*;
import java.io.FileInputStream;

class ShowArchiveContent {
  public static void main(String[] args) {
    try {
      // открыть архив и считать содержимое архива
      ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream("test.zip"));
      ZipEntry zipEntry = zipInputStream.getNextEntry();

      while (zipEntry != null) {
        String strZipFileName = zipEntry.getName();
        System.out.println(strZipFileName);
        zipEntry = zipInputStream.getNextEntry();
      }
      zipInputStream.closeEntry();
      zipInputStream.close();

    } catch (Exception ex) {
      System.out.println("some exception - " + ex);
    }
  }
}