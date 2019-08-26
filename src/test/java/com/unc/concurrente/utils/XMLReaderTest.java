package com.unc.concurrente.utils;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.unc.concurrente.mocks.PInvariante;
import com.unc.concurrente.model.ParametrosIniciales;
import com.unc.concurrente.utils.XMLReader;

public class XMLReaderTest {
	
	ParametrosIniciales params = new ParametrosIniciales();
	private static final String XML_CONFIGURAION_FILE_PATH = "src/test/resources/PetriNetConfigurationTest.xml";
	private static final int TRANSICIONES = 6;
	private static final int PLAZAS = 9;
	private static final int INF = 1000000000;
	private static final Integer[] ALFA = new Integer[]{0, 0, 0, 0, 0, 0};
	private static final Integer[] BETA = new Integer[]{INF, INF, INF, INF, INF, INF};
	private static final Integer[] M0 = new Integer[]{1, 1, 0, 0, 0, 0, 1, 0, 5};
	private static final Boolean[] GUARDAS = new Boolean[]{true, true, true, true, true, true};
	private static final String[] T_INVARIANTE = {"0", "1", "2", "3", "4", "5"};
	private static final List<PInvariante> P_INVARIANTES = mockPInvariantes();
	private static final Integer[][] INCIDENCIA = new Integer[][] {{-1,0,0,0,1,0},
                                                                   {0,-1,0,0,0,1},
                                                                   {1,0,0,-1,0,0},
                                                                   {0,1,-1,0,0,0},
                                                                   {0,0,0,1,-1,0},
                                                                   {0,0,1,0,0,-1},
                                                                   {-1,-1,1,1,0,0},
                                                                   {0,-1,0,1,0,0},
                                                                   {-1,0,1,0,0,0}};
    @Before
    public void setUp() {
    	params = XMLReader.getParametrosIniciales(XML_CONFIGURAION_FILE_PATH);
    }
    
    @Test
    public void test_leerXML_exito_en_cargar_transiciones() {
    	assertEquals(params.getTransiciones().intValue(), TRANSICIONES);
    }
    
    @Test
    public void test_leerXML_exito_en_cargar_plazas() {
    	assertEquals(params.getPlazas().intValue(), PLAZAS);
    }
    
    @Test
    public void test_leerXML_exito_en_cargar_marca_inicial() {
    	Integer[] marca = params.getM0();
    	
    	assertEquals(marca.length, PLAZAS);
    	
    	assertEquals(marca[0], M0[0]);
    	assertEquals(marca[1], M0[1]);
    	assertEquals(marca[2], M0[2]);
    	assertEquals(marca[3], M0[3]);
    	assertEquals(marca[4], M0[4]);
    	assertEquals(marca[5], M0[5]);
    	assertEquals(marca[6], M0[6]);
    	assertEquals(marca[7], M0[7]);
    	assertEquals(marca[8], M0[8]);
    }
    
    @Test
    public void test_leerXML_exito_en_cargar_matriz_de_incidencia() {
    	Integer[][] matriz = params.getIncidencia();
    	int elementos = 0;
    	
    	for(int i = 0; i < PLAZAS; i++) {
    		for(int j = 0; j < TRANSICIONES; j++) {
    			assertEquals("El valor de la coordenada " + i + "," + j + " no es el esperado.", matriz[i][j], INCIDENCIA[i][j]);
    			elementos++;
    		}
    	}
    	
    	assertEquals("La cantidad de elementos en la matriz no es la esperada", elementos, PLAZAS*TRANSICIONES);
    }
    
    @Test
    public void test_leerXML_exito_en_cargar_el_vector_de_guardas() {
    	Boolean[] guardas = params.getGuardas();
    	
    	assertEquals(guardas.length, TRANSICIONES);
    	
    	assertEquals(guardas[0], GUARDAS[0]);
    	assertEquals(guardas[1], GUARDAS[1]);
    	assertEquals(guardas[2], GUARDAS[2]);
    	assertEquals(guardas[3], GUARDAS[3]);
    	assertEquals(guardas[4], GUARDAS[4]);
    	assertEquals(guardas[5], GUARDAS[5]);
    }
    
    @Test
    public void test_leerXML_exito_en_cargar_el_vector_de_tiempo_alfa() {
    	Integer[] alfa = params.getTiempos().getAlfa();
    	
    	assertEquals(alfa.length, TRANSICIONES);
    	
    	assertEquals(alfa[0], ALFA[0]);
    	assertEquals(alfa[1], ALFA[1]);
    	assertEquals(alfa[2], ALFA[2]);
    	assertEquals(alfa[3], ALFA[3]);
    	assertEquals(alfa[4], ALFA[4]);
    	assertEquals(alfa[5], ALFA[5]);
    }
    
    @Test
    public void test_leerXML_exito_en_cargar_el_vector_de_tiempo_beta() {
        Integer[] beta = params.getTiempos().getBeta();
    	
        assertEquals(beta.length, TRANSICIONES);
    	
        assertEquals(beta[0], BETA[0]);
    	assertEquals(beta[1], BETA[1]);
    	assertEquals(beta[2], BETA[2]);
    	assertEquals(beta[3], BETA[3]);
    	assertEquals(beta[4], BETA[4]);
    	assertEquals(beta[5], BETA[5]);
    }
    
    @Test
    public void test_leerXML_exito_en_cargar_el_vector_de_t_invariante() {
    	List<String[]> invariantes = XMLReader.leerTInvatiantes(XML_CONFIGURAION_FILE_PATH);
    	String[] tInvariante = invariantes.get(0);
    	
    	assertEquals("La cantidad de t-invariantes cargados no es la esperada", invariantes.size(), 1);
    	assertEquals("El numero de datos del T-invariante uno no es el esperado", tInvariante.length, T_INVARIANTE.length);
    	
    	assertEquals(tInvariante[0], T_INVARIANTE[0]);
    	assertEquals(tInvariante[1], T_INVARIANTE[1]);
    	assertEquals(tInvariante[2], T_INVARIANTE[2]);
    	assertEquals(tInvariante[3], T_INVARIANTE[3]);
    	assertEquals(tInvariante[4], T_INVARIANTE[4]);
    	assertEquals(tInvariante[5], T_INVARIANTE[5]);
    }
    
    @Test
    public void test_leerXML_exito_en_cargar_el_vector_de_p_invariantes() {
    	List<PInvariante> pInvariantes = XMLReader.leerPInvariantes(XML_CONFIGURAION_FILE_PATH);
    	PInvariante invariante;
    	
    	assertEquals("La cantidad de p-invariantes cargados no es la esperada", pInvariantes.size(), 4);
    	
    	invariante = pInvariantes.get(0);
    	assertEquals(invariante.getTotal(), P_INVARIANTES.get(0).getTotal());
    	assertEquals(invariante.getSecuencia().length, P_INVARIANTES.get(0).getSecuencia().length);
    	assertEquals(invariante.getSecuencia()[0], P_INVARIANTES.get(0).getSecuencia()[0]);
    	assertEquals(invariante.getSecuencia()[1], P_INVARIANTES.get(0).getSecuencia()[1]);
    	assertEquals(invariante.getSecuencia()[2], P_INVARIANTES.get(0).getSecuencia()[2]);
    	
    	invariante = pInvariantes.get(1);
    	assertEquals(invariante.getTotal(), P_INVARIANTES.get(1).getTotal());
    	assertEquals(invariante.getSecuencia().length, P_INVARIANTES.get(1).getSecuencia().length);
    	assertEquals(invariante.getSecuencia()[0], P_INVARIANTES.get(1).getSecuencia()[0]);
    	assertEquals(invariante.getSecuencia()[1], P_INVARIANTES.get(1).getSecuencia()[1]);
    	assertEquals(invariante.getSecuencia()[2], P_INVARIANTES.get(1).getSecuencia()[2]);
    	
    	invariante = pInvariantes.get(2);
    	assertEquals(invariante.getTotal(), P_INVARIANTES.get(2).getTotal());
    	assertEquals(invariante.getSecuencia().length, P_INVARIANTES.get(0).getSecuencia().length);
    	assertEquals(invariante.getSecuencia()[0], P_INVARIANTES.get(2).getSecuencia()[0]);
    	assertEquals(invariante.getSecuencia()[1], P_INVARIANTES.get(2).getSecuencia()[1]);
    	assertEquals(invariante.getSecuencia()[2], P_INVARIANTES.get(2).getSecuencia()[2]);
    	
    	invariante = pInvariantes.get(3);
    	assertEquals(invariante.getTotal(), P_INVARIANTES.get(3).getTotal());
    	assertEquals(invariante.getSecuencia().length, P_INVARIANTES.get(3).getSecuencia().length);
    	assertEquals(invariante.getSecuencia()[0], P_INVARIANTES.get(3).getSecuencia()[0]);
    	assertEquals(invariante.getSecuencia()[1], P_INVARIANTES.get(3).getSecuencia()[1]);
    	assertEquals(invariante.getSecuencia()[2], P_INVARIANTES.get(3).getSecuencia()[2]);
    	assertEquals(invariante.getSecuencia()[3], P_INVARIANTES.get(3).getSecuencia()[3]);
    	
    }
    
    private static List<PInvariante> mockPInvariantes() {
    	List<PInvariante> pInvariantes = new ArrayList<>();
    	PInvariante invariante;
    	
    	invariante = new PInvariante();
    	invariante.setTotal(1);
    	invariante.setSecuencia(new String[] {"1", "3", "5"});
    	pInvariantes.add(invariante);
    	
    	invariante = new PInvariante();
    	invariante.setTotal(1);
    	invariante.setSecuencia(new String[] {"0", "2", "4"});
    	pInvariantes.add(invariante);
    	
    	invariante = new PInvariante();
    	invariante.setTotal(1);
    	invariante.setSecuencia(new String[] {"2", "3", "6"});
    	pInvariantes.add(invariante);
    	
    	invariante = new PInvariante();
    	invariante.setTotal(5);
    	invariante.setSecuencia(new String[] {"2", "3", "7", "8"});
    	pInvariantes.add(invariante);
    	
		return pInvariantes;
    }
}
