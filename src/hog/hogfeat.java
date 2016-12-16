package hog;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.HOGDescriptor;


public class hogfeat {
	public static void main( String[] args){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat img=Highgui.imread("E:/aa/86574FS01201401170066_icon.jpg");
		//Size imgSize;
		Imgproc.resize(img,img,new Size(64, 128));
		HOGDescriptor d=new HOGDescriptor();
		MatOfFloat descriptorsValues=new MatOfFloat();
		MatOfPoint locations=new MatOfPoint();
		d.compute(img, descriptorsValues,new Size(0, 0), new Size(0, 0),locations);
		System.out.println("HOG descriptor size is " + d.getDescriptorSize());
		System.out.println("img dimensions:" + img.cols() + " width x " + img.rows() + "height");
		System.out.println("Found " + descriptorsValues.size() + " descriptor valuses");
		System.out.println("Nr of locations specified : " + locations.size());
		System.out.println("descriptormat width "+descriptorsValues.size().width);
		System.out.println("descriptormat height "+descriptorsValues.size().height);
		int size=(int) (descriptorsValues.total()*descriptorsValues.channels());
		System.out.println("size "+size);
		float[] temp=new float[size];
		descriptorsValues.get(0, 0,temp);
		for(int k=0;k<size;k++){
			System.out.println(temp[k]);
		}
	}
}
