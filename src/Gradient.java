import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;
import java.util.Arrays;
import java.util.List;



public class Gradient{
    int coeffs=40;
    int idx =0;
    int temp = 0;
    int j = 0;

    public String filepath = "C:/Users/Kacper/Desktop/PROJEKT/ColorQRs/";   // directory path to read from
    public String filepathSave = "C:/Users/Kacper/Desktop/PROJEKT/finals/Gradient/";
    public String filepathColormaps ="C:/Users/Kacper/Desktop/PROJEKT/gradientmapsjpgg/";

    String filename;
    String filenamegradient;

    File[] files = new File(filepath).listFiles(); // listing all files in directory
    File[] gradientfiles = new File(filepathColormaps).listFiles();

    Mat[] img = new Mat[files.length];
    Mat[] imgproc = new Mat[files.length];
    //Mat[] gradientmap;
    List<Mat> src;
    MatOfInt matOfInt= new MatOfInt(imgproc[0]);
    public Gradient() {

            src = Arrays.asList(getGradientmap());

        for (File file : files) {
            filename = filepath + file.getName();
            img[idx] = Imgcodecs.imread(filename);
            imgproc[idx] = img[idx].clone();

        /////////// apply gradient part

            //Imgproc.applyColorMap(img[idx], gradientmap[idx], Imgproc.COLORMAP_HOT);

            Core.merge(src, imgproc[idx]);

           //  Core.add(imgproc[idx], gradientmap[idx], imgproc[idx]);
          //  System.out.println(imgproc[idx].channels());
            //Core.mixChannels(imgproc, imgproc, matOfInt);
            //Imgproc.cvtColor(imgproc[idx], imgproc[idx], Imgproc.COLOR_RGB2YUV);
            //////////
           // Imgcodecs.imwrite(filepathSave + "Gradient" + idx + ".png", imgproc[idx]);
            idx++;

       // }
    }
}


public Mat[] getGradientmap(){
        Mat[] gradientmap = new Mat[gradientfiles.length];
    for(File file: gradientfiles) {
        filenamegradient = filepathColormaps + file.getName();
       // System.out.println(filenamegradient);
        gradientmap[j++] = Imgcodecs.imread(filenamegradient);

    }
        return gradientmap;
    }

}

