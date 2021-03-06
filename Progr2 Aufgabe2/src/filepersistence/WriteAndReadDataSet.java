package filepersistence;
import java.io.*;

public class WriteAndReadDataSet {

    public static void main(String[] args) {
 
        // three example data sets
        String sensorName = "MyGoodOldSensor"; // does not change
 
        long[] timeStamps = new long[3];
        timeStamps[0] = System.currentTimeMillis();
        timeStamps[1] = timeStamps[0] + 1; // milli sec later
        timeStamps[2] = timeStamps[1] + 1000; // second later

        float[][] values = new float[3][];
        // 1st measure .. just one value
        float[] valueSet = new float[1];
        values[0] = valueSet;
        valueSet[0] = (float) 1.5; // example value 1.5 degrees

        // 2nd measure .. just three values
        valueSet = new float[3];
        values[1] = valueSet;
        valueSet[0] = (float) 0.7;
        valueSet[1] = (float) 1.2;
        valueSet[2] = (float) 2.1;

        // 3rd measure .. two values
        valueSet = new float[2];
        values[2] = valueSet;
        valueSet[0] = (float) 0.7;
        valueSet[1] = (float) 1.2;

        // write three data set into a file
        // TODO: your job. use DataOutputStream / FileOutputStream

        OutputStream fileos;
        DataOutputStream filedos = null;
        
        try {
            fileos = new FileOutputStream("file.txt");
            filedos = new DataOutputStream(fileos);
        } catch (Exception e) {
            System.out.println("couldn�t open file - fatal");
     
        }

        for (float[] value : values) {
            try {
               
                for (int n = 0; n < value.length; n++) {
                	
                	filedos.writeUTF(sensorName);
                	filedos.writeLong(timeStamps[n]);
                	filedos.writeFloat(value[n]);

                }
             

            } catch (Exception e) {
                System.out.println("file writing error!");

            }
        }

        // read data from file and print to System.out
        // TODO: your job use DataInputStream / FileInputStream

        InputStream inputStream;
        DataInputStream dataInputStream = null;
        try {
            inputStream = new FileInputStream("file.txt");
            dataInputStream = new DataInputStream(inputStream);
        } catch (FileNotFoundException e) {
            System.out.println("file writing error!");

        }

        for (float[] value : values) {
            try {
     
                for (int n = 0; n < value.length; n++) {
                	
                	String strValue =   	dataInputStream.readUTF();
                	long longValue = 	dataInputStream.readLong();
                	float floatValue = 	dataInputStream.readFloat();
            
                	System.out.println("Sensor Name: " + strValue + " | Time: " + longValue + " | Temperatur: " + floatValue);
          
                }
           

            } catch (Exception e) {
                System.out.println("file reading error!");

            }
        }
    }
}