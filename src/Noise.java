import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.imgcodecs.Imgcodecs;
import java.io.File;

public class Noise  {

    int idx = 0;
    double scale;   // the lower scale the bigger noise (up to mean value and standard deviation of readed QR which itself makes code unreadable for detector - happens to read red band from time to time))
    // 2 is still fine and the noise is surprisingly big


    public String filepath = "C:/Users/Kacper/Desktop/PROJEKT/ColorQRs/";   // load files from this directory
    public String filepathSave = "C:/Users/Kacper/Desktop/PROJEKT/finals/Noise";  // make sure that the final directory exists

    String filename;
    File[] files = new File(filepath).listFiles();
    Mat[] img = new Mat[files.length]; // loaded images
    Mat[] imgproc = new Mat[files.length];  // images to work on and to save

    MatOfDouble mean = new MatOfDouble ();
    MatOfDouble dev = new MatOfDouble ();

    public Noise(){
        for (File file : files) {
            filename = filepath + file.getName();
            img[idx] = Imgcodecs.imread(filename);
            imgproc[idx] = img[idx].clone();
            //System.out.println(filename);
            scale = (Math.random()*(4-0.2))+0.2; // you are dividing mean and std. deviation of the QR code by this value - the bigger value the lower noise?
//////////// noise generation and addition part - not much to change, can get rid of 1st line and arbitrarily set values of mean value and standard deviation (but what for ;))
            Core.meanStdDev(img[idx],mean,dev);
            Mat noise = new Mat(img[idx].size(), img[idx].type());
            Core.randn(noise,(mean.get(0,0)[0])/scale, (dev.get(0,0)[0])/scale);
            Core.add(imgproc[idx], noise, imgproc[idx]);
///////////
            Imgcodecs.imwrite(filepathSave + "/Noise" + idx + ".png", imgproc[idx]);
            idx++;
        }
    }

}

