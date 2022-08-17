import javax.imageio.ImageIO;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.io.InputStream;
import java.awt.FontMetrics;
import java.awt.geom.Rectangle2D;

public class convertImg_TEST {
  
    public static void main(String[] args) {
        //задаем 2 типа расширений
        /*String ext1 = "jpg";
        String ext2 = "png";
        try {
            ArrayList<String> images = new ArrayList<>();
            images = (Files_Dirs_4_2.filesNdirs(".", ext1, false)).get(0);

            for(int i=0; i< images.size(); i++) {
                String name = images.get(i);
                BufferedImage img = ImageIO.read(new File(name));
                ImageIO.write(img, ext2, new File(name.substring(0, name.lastIndexOf('.')) + "." + ext2));
                
                File file = new File(name);
                if (file.exists()) file.delete();
            }
        } catch (Exception ex) {
            System.out.println("Exception during work with convertation - " + ex);
        } */
      try {
        BufferedImage imgSquare = ImageIO.read(new File("pic2.png")); //открыли картинку
        Graphics canvas = imgSquare.getGraphics(); //получаем холст для работы с графикой

        canvas.setColor(Color.YELLOW); //задали цвет канве
        //canvas.fillRect((imgSquare.getWidth()-w)/2, (imgSquare.getHeight()-h)/2, w, h);
        //надо узнать длину строки в пикселях исходя из выбранного шрифта и размера букв
        Font font = new Font("Verdana", Font.PLAIN, 10);  
        FontMetrics metrics = new FontMetrics(font) {  
        };  
        Rectangle2D bounds = metrics.getStringBounds("Hello World!", null);  
        int widthInPixels = (int) bounds.getWidth(); 
        int heightInPixels = (int) bounds.getHeight();
        //System.out.println("ширина текста - " + widthInPixels);
        int w = widthInPixels + 20; //ширина квадрата
        int h = w;   //высота квадрата, равная его же ширине

        //отрисовка прямоугольника
        canvas.drawRect((imgSquare.getWidth()-w)/2, (imgSquare.getHeight()-h)/2, w, h);
        //отрисовка текста
        canvas.drawString("Hello,", (imgSquare.getWidth()-w)/2 + 10, (imgSquare.getHeight()-h)/2 + 15);
        canvas.drawString("world!", (imgSquare.getWidth()-w)/2 + 10, (imgSquare.getHeight()-h)/2 + 15 + heightInPixels);
        canvas.dispose();

        ImageIO.write(imgSquare, "png", new File("pic2_rect.png"));
        
      } catch(Exception ex) {
        System.out.println("Exception during drawing text in square - " + ex);
      }
    }
}