import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class Rotation {
    int idx = 0;



    public String filepath = "C:/Users/Kacper/Desktop/PROJEKT/ColorQRs/";
    public String filepathSave = "C:/Users/Kacper/Desktop/PROJEKT/finals/Rotation"; // make sure that the final directory exists

    String filename;
    File[] files = new File(filepath).listFiles();
    Mat[] img = new Mat[files.length]; // loaded images
    Mat[] imgproc = new Mat[files.length];  // images to work on and to save



    public Rotation() {



        for (File file : files) {
            filename = filepath + file.getName();
            img[idx] = Imgcodecs.imread(filename);
            imgproc[idx] = img[idx].clone();
           // System.out.println(filename);

            //////////// rotation part

            Point pointsin[] = new Point[]{ new Point ((int)(Math.random()*(50-20)+1)+20,(int)(Math.random()*(50-20)+1)+20),
                    new Point((int)(Math.random()*(200-100)+1)+100,(int)(Math.random()*(50-20)+1)+20),
                    new Point((int)(Math.random()*(50-20)+1)+20,(int)(Math.random()*(300-250)+1)+250)};

            Point pointsout[] = new Point[]{ new Point ((int)(Math.random()*(50-20)+1)+10,(int)(Math.random()*(50-20)+1)+10),
                    new Point((int)(Math.random()*(200-100)+1)+50,(int)(Math.random()*(50-20)+1)+10),
                    new Point((int)(Math.random()*(50-20)+1)+10,(int)(Math.random()*(300-250)+1)+100)};

            MatOfPoint2f psrc = new MatOfPoint2f(pointsin);
            MatOfPoint2f pout = new MatOfPoint2f(pointsout);

            Mat m = Imgproc.getAffineTransform(psrc, pout);
            Imgproc.warpAffine(imgproc[idx], imgproc[idx], m, img[idx].size());
            ///////////

            Imgcodecs.imwrite(filepathSave + "/Rotation" + idx + ".png", imgproc[idx]);
            idx++;
        }
    }
}

