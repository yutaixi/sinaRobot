package com.sina.robot.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {

	public static File write(String file_name, InputStream is) {
//		FileInputStream is=null;
//		try {
//			is = new FileInputStream(content);
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		File file = new File(file_name);
		if(!file.exists())
		{
			file.mkdirs();
		}
		OutputStream os = null;
		try {
			os = new FileOutputStream(file);
			byte buffer[] = new byte[2048];
			while ((is.read(buffer)) != -1) {
				os.write(buffer);
			}
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try
			{
				is.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return file;
	}
	
	
	
//	public static void saveMyBitmap(String bitName) throws IOException {
//		File f = new File("/sdcard/Note/" + bitName + ".png");
//		f.createNewFile();
//		FileOutputStream fOut = null;
//		try {
//		fOut = new FileOutputStream(f);
//		} catch (FileNotFoundException e) {
//		e.printStackTrace();
//		}
//		mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
//		try {
//		fOut.flush();
//		} catch (IOException e) {
//		e.printStackTrace();
//		}
//		try {
//		fOut.close();
//		} catch (IOException e) {
//		e.printStackTrace();
//		}
//		}
	
	private static boolean makeDirsWhenNotExist(String filePath)
	{
		String separator=null;
    	if(filePath.indexOf("/")>-1)
    	{
    		separator="/";
    	}else if(filePath.indexOf("\\")>-1)
    	{
    		separator="\\";
    	}
    	int index=filePath.lastIndexOf(separator);
    	String cataloguePath=filePath.substring(0, index);
		File file=new File(cataloguePath);
		if(!file.exists())
		{
			file.mkdirs();
		}
	    return true;	
	}
	
	public static File writeBitmap(String file_name,InputStream is) {  
		if(is==null)
		{
			return null;
		}
        byte[] bs = getFileToByte(is);  
        FileOutputStream fos=null;
        File f=null;
        try {  
             f= new File(file_name);  
            if (f.exists() == false) { 
            	makeDirsWhenNotExist(file_name);
                f.createNewFile();  
            }  
            fos= new FileOutputStream(f);  
            fos.write(bs);  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException ie) {  
            ie.printStackTrace();  
        }finally
        {
        	try
        	{
        		fos.close();
        	}catch(Exception e)
        	{
        		e.printStackTrace();
        	}
        	
        }
        return f;
    }  
    public static byte[] getFileToByte(InputStream is) {  
//        byte[] by = new byte[(int) file.length()];  
    	byte[] by=null;
        try {  
//            InputStream is = new FileInputStream(file);  
            ByteArrayOutputStream bytestream = new ByteArrayOutputStream();  
            byte[] bb = new byte[2048];  
            int ch;  
            ch = is.read(bb);  
            while (ch != -1) {  
                bytestream.write(bb, 0, ch);  
                ch = is.read(bb);  
//                System.out.println("ch : " + ch);  
            }  
            by = bytestream.toByteArray();  
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
        return by;  
    }  
    
//    public static boolean createWhenNotExist(String path)
//    {
//    	String separator=null;
//    	String splitReg=null;
//    	File file=null;
//    	if(path.indexOf("/")>-1)
//    	{
//    		separator="/";
//    		splitReg="/";
//    	}else if(path.indexOf("\\")>-1)
//    	{
//    		separator="\\";
//    		splitReg="\\\\";
//    	}
//    	int index=path.lastIndexOf(separator);
//    	String cataloguePath=path.substring(0, index);
//    	String[] catalogues=cataloguePath.split(splitReg);
//    	for(String catalogue : catalogues)
//    	{
//    		file=new File(catalogue);
//    		if(file.exists())
//    		{
//    			continue;
//    		}else
//    		{
//    			file.mkdirs();
//    		}
//    	}
//    	return true;
//    }
    
    public static boolean isFileExist(String filePath)
    {
    	File file=new File(filePath);
    	return file.exists();
    	
    }
}
