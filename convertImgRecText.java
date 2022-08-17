import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Color;

public class convertImgRecText {
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

                Graphics canvas = img.getGraphics(); //получаем холст для работы с графикой
                canvas.setColor(Color.YELLOW); //задали цвет канве
                //надо узнать длину строки в пикселях исходя из выбранного шрифта и размера букв
                Font font = new Font("Verdana", Font.PLAIN, 15); //задали шрифт 
                FontMetrics metrics = new FontMetrics(font){};

                canvas.setFont(font); //установили шрифт в канву 
              
                Rectangle2D bounds = metrics.getStringBounds("Hello \nWorld!", null);  
                int widthInPixels = (int) bounds.getWidth(); //ширина текстового поля
                int heightInPixels = (int) bounds.getHeight(); //высота текстового поля

                int fontS = font.getSize(); //функции получения размера шрифта вынесли в отдельную переменную
              
                int w = widthInPixels + fontS*2; //ширина квадрата
                int h = w; //высота квадрата, равная его же ширине

                //отрисовка прямоугольника
                canvas.drawRect((img.getWidth()-w)/2, (img.getHeight()-h)/2, w, h);
                //отрисовка текста
                canvas.drawString("Hello,", (img.getWidth()-w)/2 + fontS, (img.getHeight()-h)/2 + fontS*2);
                canvas.drawString("world!", (img.getWidth()-w)/2 + fontS, (img.getHeight()-h)/2 + fontS*2 + heightInPixels);
                canvas.dispose();

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