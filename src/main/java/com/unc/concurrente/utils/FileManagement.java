package com.unc.concurrente.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManagement {
	private static final String FILE_PATH = "src/test/resources/com/unc/concurrente/logs/transicionLog.txt";
	private StringBuffer buffer;
	private File file;

	public FileManagement() {
		buffer = new StringBuffer();
		file = new File(FILE_PATH);
		
	}
	
	
	public void escribirBuffer(int valor) {
		buffer.append(valor + "-");
	}
	
	public String getInfoBuffer() {
		return buffer.toString();
	}
	
	public void escribirArchivo() {
		FileWriter writer;
		try {
			writer = new FileWriter(file);
			writer.write(buffer.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}