package savethebunniesclient.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class CreateFile {
	public static void createFile(String text, int id) {
		File file = null;
		try {
	        file = new File(Resources.TEMPFILES + id + ".txt");
	        if (!file.exists()) {
	            file.createNewFile();
	        }
	        FileWriter fw = new FileWriter(file);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(text);
	        bw.close();
	        //System.out.println(id+".txt was created in " + Resources.TEMPFILES.toString());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public static void createFileMyLevels(String text, int id) {
		File file = null;
		try {
	        file = new File(Resources.MYLEVELS + id + ".txt");
	        if (!file.exists()) {
	            file.createNewFile();
	        }
	        FileWriter fw = new FileWriter(file);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(text);
	        bw.close();
	        //System.out.println(id+".txt was created in " + Resources.MYLEVELS.toString());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static void deleteFolderContentMyLevels() {
		File folder = new File(Resources.MYLEVELS);
		File[] files = folder.listFiles(); 
		if(files!=null) { 
			for(File f: files) {
				f.delete();  
			}
		}	
		//System.out.println("CONTENIDO DE MYLEVELS ELIMINADO");
	}
	
	public static void deleteFolderContentsOnlineLevels() {
		File folder = new File(Resources.TEMPFILES);
		File[] files = folder.listFiles(); 
		if(files!=null) { 
			for(File f: files) {
				f.delete();  
			}
		}
		//System.out.println("CONTENIDO DE ONLINELEVELS (TEMP) ELIMINADO");
	}
}
