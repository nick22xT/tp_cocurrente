package com.unc.concurrente.utils;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.unc.concurrente.model.ParametrosIniciales;
import com.unc.concurrente.model.Tiempos;

public class XMLReader {
	
	private static final String DELIMITADOR = ",";
	private static final String PLAZAS = "plazas";
	private static final String TRANSICIONES = "transiciones";
	private static final String MARCA_INICIAL = "marca-inicial";
	private static final String GUARDAS = "guardas";
	private static final String INCIDENCIA = "incidencia";
	private static final String FILA = "fila";
	private static final String TIEMPO = "t-tiempo";
	private static final String ALFA = "alfa";
	private static final String BETA = "beta";
	private static final String INFINITO = "INF";
	private static final int INF_NUMERO = 1000000000;
	
	/**
	 * Carga los parametros iniciales de la red de petri desde un archivo XML
	 * @param path
	 * @return
	 */
	public static ParametrosIniciales getParametrosIniciales(String path) {
		Document document = getArchivo(path);;
		ParametrosIniciales params = new ParametrosIniciales();
        
        NodeList nodeList = document.getElementsByTagName(PLAZAS);
        params.setPlazas(Integer.parseInt(nodeList.item(0).getTextContent()));
        
        nodeList = document.getElementsByTagName(TRANSICIONES);
        params.setTransiciones(Integer.parseInt(nodeList.item(0).getTextContent()));
        
        nodeList = document.getElementsByTagName(MARCA_INICIAL);
        params.setM0(parsearVector(nodeList.item(0).getTextContent()));
        
        nodeList = document.getElementsByTagName(GUARDAS);
        params.setGuardas(parsearGuardas(nodeList.item(0).getTextContent()));
        
        nodeList = document.getElementsByTagName(INCIDENCIA);
        params.setIncidencia(parsearMatriz(nodeList.item(0), params.getPlazas(), params.getTransiciones()));
        
        nodeList = document.getElementsByTagName(TIEMPO);
        params.setTiempos(parsearTiempos(nodeList.item(0)));
        
		return params;
	}
	
	
	private static Document getArchivo(String path) {
		File xmlFile = new File(path);
		DocumentBuilderFactory dbf = null;
        DocumentBuilder documentBuilder = null;
		Document document = null;
		
        try {
        	dbf = DocumentBuilderFactory.newInstance();
            documentBuilder = dbf.newDocumentBuilder();
			document = documentBuilder.parse(xmlFile);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return document;
	}
	
	private static Integer[][] parsearMatriz(Node node, Integer plazas, Integer transiciones) {
		NodeList nodeList = node.getChildNodes();
		Integer [][] intMatriz = new Integer[plazas][transiciones];
		int indice = 0;
		
		for(int i = 0; i < nodeList.getLength(); i++) {
			if(FILA.equalsIgnoreCase(nodeList.item(i).getNodeName())) {
				Integer[] fila = parsearVector(nodeList.item(i).getTextContent());
				for(int j = 0; j < transiciones; j++) {
					intMatriz[indice][j] = fila[j];
				}
				indice++;
			}
			
		}
		
		return intMatriz;
	}
	
	private static Boolean[] parsearGuardas(String caracteres) {
		String[] charSecuence = caracteres.split(DELIMITADOR);
		Boolean[] arrayBoolean = new Boolean[charSecuence.length];
		
		for(int i = 0; i < arrayBoolean.length; i++) {
			arrayBoolean[i] = Boolean.parseBoolean(charSecuence[i]);
		}
		
		return arrayBoolean;
	}
	
	private static Integer[] parsearVector(String caracteres) {
		String[] charSecuence = caracteres.split(DELIMITADOR);
		Integer[] intSecuence = new Integer[charSecuence.length];
		
		for(int i = 0; i < charSecuence.length; i++) {
			if(INFINITO.equalsIgnoreCase(charSecuence[i])) {
				intSecuence[i] = INF_NUMERO;
			} else {
				intSecuence[i] = Integer.parseInt(charSecuence[i]);
			}
		}
		
		return intSecuence;
	}
	
	private static Tiempos parsearTiempos(Node node) {
		NodeList nodeList = node.getChildNodes();
		Tiempos tiempos = new Tiempos();
		
		for(int i = 0; i < nodeList.getLength(); i++) {
			if(ALFA.equalsIgnoreCase(nodeList.item(i).getNodeName())) {
				tiempos.setAlfa(parsearVector(nodeList.item(i).getTextContent()));
			} else if(BETA.equalsIgnoreCase(nodeList.item(i).getNodeName())) {
				tiempos.setBeta(parsearVector(nodeList.item(i).getTextContent()));
			}
		}
		
		return tiempos;
	}
}
