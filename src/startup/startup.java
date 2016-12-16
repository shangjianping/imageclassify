package startup;

import hog.feat;

import java.io.IOException;

import random.svm.CBSvm;

public class startup {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		feat f = new feat();
		f.savefeat("E:/aa/test", "E:/aa/feat");

		 System.out.println("训练集数据处理完毕,开始生成模型");

         //建立模型
         CBSvm svm = new CBSvm("E:/aa/feat", "E:/aa/model");

         System.out.println("模型生成完毕,开始对测试集进行测试");
         float[] data =f.hog("E:/aa/test/0.jpg");
         int number = (int) svm.getClassification(data);
         System.out.println(number);
	}

}
