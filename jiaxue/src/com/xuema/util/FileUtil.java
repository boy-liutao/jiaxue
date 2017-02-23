package com.xuema.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;


public class FileUtil {
	public static void redirectOut(String fileName) throws FileNotFoundException{
		FileOutputStream fos = new FileOutputStream(fileName);
        PrintStream ps = new PrintStream(fos);
        System.setOut(ps);
	}
	
	public static void redirectErr(String fileName) throws FileNotFoundException{
		FileOutputStream fos = new FileOutputStream(fileName);
        PrintStream ps = new PrintStream(fos);
        System.setErr(ps);
	}
	
	public static String getFileContent(String fileName) throws IOException{
        StringBuffer sb = new StringBuffer();
        File f = new File(fileName);
        FileReader r = new FileReader(f);
        BufferedReader br = new BufferedReader(r);
        String line = br.readLine();
        while (line != null){
            sb.append(line).append("\n");
            line = br.readLine();
        }
        r.close();
        br.close();
        return sb.toString();
    }
	
	public static String getFileContent(String fileName, String lineDelimiter) throws IOException{
        StringBuffer sb = new StringBuffer();
        File f = new File(fileName);
        FileReader r = new FileReader(f);
        BufferedReader br = new BufferedReader(r);
        String line = br.readLine();
        while (line != null){
            sb.append(line).append(lineDelimiter);
            line = br.readLine();
        }
        r.close();
        br.close();
        return sb.toString();
    }
	
	public static void copyFile(String srcFile, String destFile){
		File dir = new File(destFile);
		new File(srcFile).renameTo(new File(dir, srcFile));
	}
}
