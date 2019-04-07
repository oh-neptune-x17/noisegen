import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;

public class Blur {
    int coeffs10 = 10; // more coeffs = more blur
    int coeffs20 = 20;
    int idx =0;
    public String filepath = "C:/Users/Kacper/Desktop/PROJEKT/ColorQRs/";   // directory path to read from
    public String filepathSave = "C:/Users/Kacper/Desktop/PROJEKT/finals/";  //// make sure that the final directory exists
    String filename;

    File[] files = new File(filepath).listFiles(); // listing all files in directory

    Mat[] img = new Mat[files.length];
    Mat[] imgproc = new Mat[files.length];
    Mat[] imgproc_ = new Mat[files.length];
    public Blur(){
        for (File file : files) {
                filename = filepath + file.getName();
                img[idx] = Imgcodecs.imread(filename);
                imgproc[idx] = img[idx].clone();
                imgproc_[idx] = img[idx].clone();
                /////////// gaussian blur part
                for (int j = 1; j < coeffs10; j += 2) {
                    Imgproc.GaussianBlur(img[idx], imgproc[idx], new Size(j, j), 0, 0);
                }
                 for (int j = 1; j < coeffs20; j += 2) {
                     Imgproc.GaussianBlur(img[idx], imgproc_[idx], new Size(j, j), 0, 0);
                 }
                //////////
                    Imgcodecs.imwrite(filepathSave + "/GaussianBlur10coeffs/GaussianBlur" + idx + ".png", imgproc[idx]);
                    Imgcodecs.imwrite(filepathSave + "/GaussianBlur/GaussianBlur" + idx + ".png", imgproc_[idx]);
                idx++;
        }
    }

 //   public void saveImages(Mat[] toSave){
 //       toSave = this.imgproc;
 //           for(int i = 0; i < toSave.length; i++){
 //               Imgcodecs.imwrite( filepath"+i+".png", toSave[i]);
 //           }
 //       }
    public static void main(String[] args) {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            Blur blur = new Blur();
            Noise noise = new Noise();
            Rotation rotation = new Rotation();
            //Gradient gradient = new Gradient();

    }

}
