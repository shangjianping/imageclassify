package hog;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.HOGDescriptor;

public class feat {
	public  float[] hog(String filepath){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat img=Highgui.imread(filepath);
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
		return temp;
	}
	
	public void convert(String filePath,String fileName,StringBuffer buff){
		//StringBuffer buff = new StringBuffer();
		float[] hog= hog(filePath+File.separator+fileName);
		buff.append(Integer.valueOf(fileName.substring(0, fileName.indexOf("."))));
		for (int i = 0; i < hog.length; i++) {
			buff.append(" ");
			buff.append(i);
			buff.append(':');
			buff.append(hog[i]);
		}
		buff.append("\n");
	}
	
	public void savefeat(String path,String outFilePath) throws IOException{
		File fileDir = new File(path);
		String[] images = fileDir.list();
		StringBuffer buff = new StringBuffer();

		for (String image : images) {
			convert(path,image,buff);
		}
		FileUtils.writeStringToFile(new File(outFilePath), buff.toString());
	}

	
	public static void main(String[] args) throws IOException{
		feat f = new feat();
		f.savefeat("E:/aa/test", "E:/aa/feat");
	}
}
