package com.unc.concurrente.mocks;

import com.unc.concurrente.rdp.RDP;
import com.unc.concurrente.utils.XMLReader;

public class RDPMock {
	
	private static final String XML_CONFIGURAION_FILE_PATH = "src/test/resources/PetriNetConfigurationTest.xml";
	
	public static RDP getRDP() {
		return new RDP(XMLReader.getParametrosIniciales(XML_CONFIGURAION_FILE_PATH));
	}
}