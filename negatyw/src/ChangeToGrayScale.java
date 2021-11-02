import java.awt.*;
import java.awt.image.BufferedImage;

public class ChangeToGrayScale extends Thread{
    public BufferedImage image;
    int widthStart;
    int widthEnd;

    public ChangeToGrayScale(BufferedImage b, int widthStart, int widthEnd) {
        this.image = b;
        this.widthStart = widthStart;
        this.widthEnd = widthEnd;
    }

    @Override
    public void run(){
        if (this.image != null) {
            for(int i=1; i<this.image.getHeight()-1; i++){
                for(int j=this.widthStart; j<this.widthEnd-1; j++){

                    //odczyt składowych koloru RGB
                    Color c = new Color(image.getRGB(j, i));
                    int red = (int)(c.getRed());
                    int green = (int)(c.getGreen());
                    int blue = (int)(c.getBlue());

                    int final_red, final_green, final_blue;

                    //negatyw
                    final_red = 255-red;
                    final_green = 255-green;
                    final_blue = 255-blue;
                    Color newColor = new Color(final_red, final_green, final_blue);
                    image.setRGB(j,i,newColor.getRGB());
                } //koniec dwóch pętli po kolumnach i wierszach obrazu
            }
        }
    }
}
