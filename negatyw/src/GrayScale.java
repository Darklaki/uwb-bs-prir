import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
public class GrayScale {
    BufferedImage image;
    int width;
    int height;
    public GrayScale() {
        try {
            int NUM_OF_THREADS = 4;
//odczyt obrazu z pliku
            File input = new File("zwycieskie-zdjecie-wildlife-photographer-of-the-year-2019-autor-yongqing-bao.jpeg");
            image = ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();
            int widthPerThread = image.getWidth() / NUM_OF_THREADS;
            for (int i = 0; i < NUM_OF_THREADS; i++) {
                int start = i * widthPerThread;
                int end = (i + 1) * widthPerThread + 1;
                ChangeToGrayScale thread = new ChangeToGrayScale(image, start, end);
                thread.start();
                thread.join();
            }

            File ouptut = new File("grayscale.jpg");
            ImageIO.write(image, "jpg", ouptut);
        } catch (Exception e) {}
    }
    static public void main(String args[]) throws Exception
    {
        GrayScale obj = new GrayScale();
    }
}