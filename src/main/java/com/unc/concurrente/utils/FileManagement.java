package com.unc.concurrente.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManagement {
	private static final String T_FILE_PATH = "src/test/resources/com/unc/concurrente/logs/transicionLog.txt";
	private static final String P_FILE_PATH = "src/test/resources/com/unc/concurrente/logs/marcaLog.txt";
	private StringBuffer tBuffer;
	private StringBuffer pBuffer;
	private File tFile;
	private File pFile;

	public FileManagement() {
		tBuffer = new StringBuffer();
		pBuffer = new StringBuffer();
		tFile = new File(T_FILE_PATH);
		pFile = new File(P_FILE_PATH);
		
	}
	
	
	public void escribirTBuffer(int valor) {
		tBuffer.append(valor + "-");
	}
	
	public void escribirPBuffer(String valor) {
		pBuffer.append(valor + "\n");
	}
	
	public String getInfoTBuffer() {
		return tBuffer.toString();
	}
	
	public void escribirArchivo() {
		FileWriter tWriter;
		FileWriter pWriter;
		try {
			tWriter = new FileWriter(tFile);
			tWriter.write(tBuffer.toString());
			tWriter.close();
			
			pWriter = new FileWriter(pFile);
			pWriter.write(pBuffer.toString());
			pWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
