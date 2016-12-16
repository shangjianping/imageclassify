package random;

import java.io.File;
import java.io.IOException;

import random.global.CBConfigCenter;
import random.svm.CBSvm;
import random.util.CBDataConvert;
import random.util.CBImageUtil;
import random.util.ICBImageUtil;


public class CBMain {
    private static ICBImageUtil imageUtil = new CBImageUtil();

    public static void main(String[] args) {
        try {
            System.out.println("开始处理训练集数据");

            //转化数据为libsvm标准输入格式
            CBDataConvert.convertAndSave(CBConfigCenter.TRAIN_DIR, CBConfigCenter.TRAIN_DATA_SET);


            System.out.println("训练集数据处理完毕,开始生成模型");

            //建立模型
            CBSvm svm = new CBSvm(CBConfigCenter.TRAIN_DATA_SET, CBConfigCenter.MODEL_PATH);

            System.out.println("模型生成完毕,开始对测试集进行测试");

            //对测试集进行预测
            File testDirectory = new File(CBConfigCenter.TEST_DIR);
            for (File image : testDirectory.listFiles()) {
                double[][] data = imageUtil.getFormatData(image);
                StringBuffer fileNewName = new StringBuffer();
                for (int i = 0; i < data.length; i++) {
                    int number = (int) svm.getClassification(data[i]);
                    fileNewName.append(number);
                }

                String newFilePath = CBConfigCenter.TEST_DIR + "/" + fileNewName.toString()+".jpg";
                File newFile = new File(newFilePath);

                //文件存在的话,名字添加时间戳
                if (newFile.exists()) {
                    File newFile2 = new File(newFilePath + "_"
                            + System.currentTimeMillis());
                    image.renameTo(newFile2);
                    System.out.println("File " + image.getName()
                            + " rename to " + newFile2.getName());
                } else {
                    image.renameTo(newFile);
                    System.out.println("File " + image.getName()
                            + " rename to " + fileNewName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
