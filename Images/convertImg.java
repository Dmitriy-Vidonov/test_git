import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class convertImg {
    public static void convert(String ext1, String ext2) {
        try {
            //Создаем список для хранения файлов требуемого расширения
            ArrayList<String> images = new ArrayList<>();
            //Путем вызова функции поиска файлов и подкаталогов (filesNdirs) получаем список нужных файлов
            images = (Files_Dirs_4_2.filesNdirs(".", ext1, false)).get(0);
            //Проходим все элементы списка в цикле и для каждого элемента производим преобразования
            for(int i=0; i< images.size(); i++) {
                String name = images.get(i); //формируем имя исходного изображения
                BufferedImage img = ImageIO.read(new File(name)); //читаем нужный файл
                //Сохраняем файл в новом формате
                ImageIO.write(img, ext2, new File(name.substring(0, name.lastIndexOf('.')) + "." + ext2));
                //Удаляем старый файл
                File file = new File(name);
                if (file.exists()) file.delete();
            }
        } catch (Exception ex) {
            System.out.println("Exception during work with convertation - " + ex);
        }
    }
}