import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) {

        BufferedImage img = null;
        File f = null;

        // Wczytanie obrazu
        try {
            f = new File("img/all_black.png");
            img = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println(e);
        }

        // Pobranie szerokoci i wysokości obrazu
        int width = img.getWidth();
        int height = img.getHeight();

        // Pobieranie środkowego pikselu
        int p = img.getRGB(width / 2, height / 2);

        // Odczytanie wartosci kanalow przesuwajac o odpowiednia liczbe bitow w prawo, tak aby
        // kanal znalazł się na bitach 7-0, następnie wyzerowanie pozostałych bitów z użyciem bitowego AND z maską 0xFF.
        int a = (p >> 24) & 0xff;
        int r = (p >> 16) & 0xff;
        int g = (p >> 8) & 0xff;
        int b = p & 0xff;


        // Ustawianie wartosci poszczegolnych kanalow na przykładowe liczby
        a = 255;
        r = 100;
        g = 150;
        b = 200;

        a = a << 24;
        r = r << 16;
        g = g << 8;
        b = b;

        p = a | r | g | b;

        img.setRGB(width / 2, height / 2, p);

        // Zapisanie obrazu
        try {
            f = new File("img/ex2.png");
            ImageIO.write(img, "png", f);
        } catch (IOException e) {
            System.out.println(e);
        }

        drawLine(134, 701, 200, 598);

        allWhite(img);

        imgNegative();
    }

    public static void allWhite(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();

        for (int i=0; i<width; i++) {
            for (int j=0; j<height; j++) {
                int p = img.getRGB(i, j);

                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                a = 255 << 24;
                r = 255 << 16;
                g = 255 << 8;
                b = 255;

                p = a | r | g | b;

                img.setRGB(i, j, p);
            }
        }

        try {
            File file = new File("img/ex3.png");
            ImageIO.write(img, "png", file);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void imgNegative() {
        File file = null;
        BufferedImage img = null;

        try {
            file = new File("img/windows.png");
            img = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println(e);
        }

        int width = img.getWidth();
        int height = img.getHeight();

        for (int i=0; i<width; i++) {
            for (int j=0; j<height; j++) {

                int p = img.getRGB(i, j);

                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                r = 255 - r;
                g = 255 - g;
                b = 255 - b;

                p = a << 24 | r << 16 | g << 8 | b;

                img.setRGB(i, j, p);
            }
        }

        try {
            file = new File("img/ex4.png");
            ImageIO.write(img, "png", file);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void drawLine(int x0, int x1, int y0, int y1) {

        BufferedImage img = null;
        File f = null;

        try {
            f = new File("img/all_black.png");
            img = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println(e);
        }

        if ((x1-x0)!=0) {

            double par = (y1 * 1.0 - y0 * 1.0) / (x1 * 1.0 - x0 * 1.0);
            double bPar = y0 - par * x0;

            System.out.println("y = " + par + "x + " + bPar);

            for (int i = 0; i < img.getWidth(); i++) {
                for (int j = 0; j < img.getHeight(); j++) {

                    if (x0 > x1) {
                        int temp = x1;
                        x1 = x0;
                        x0 = temp;
                    }

                    if ((i >= x0) && (i <= x1) && (j >= y0) && (j <= y1)) {

                        int round = (int) Math.round(par * i + bPar);
                        double weight = Math.ceil(Math.abs(par));
                        if (round >= j - weight && round <= j + weight) {
                            int a = 255 << 24;
                            int r = 255 << 16;
                            int g = 255 << 8;
                            int bCol = 255;

                            int p = a | r | g | bCol;

                            img.setRGB(i, j, p);
                        }
                    }
                }
            }
        } else {
            for (int i=Math.min(y0,y1);i<Math.max(y0,y1);i++) {
                int a = 255 << 24;
                int r = 255 << 16;
                int g = 255 << 8;
                int bCol = 255;

                int p = a | r | g | bCol;

                img.setRGB(x0, i, p);
            }
        }

        try {
            File file = new File("img/ex5.png");
            ImageIO.write(img, "png", file);
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}