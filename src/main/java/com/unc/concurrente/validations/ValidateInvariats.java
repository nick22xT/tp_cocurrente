package com.unc.concurrente.validations;

import static org.junit.Assert.assertEquals;

public class ValidateInvariats {
	
	public static void ValidateInvariants(Integer[] m) {
		assertEquals(m[10] + m[11] + m[14] + m[8], 30);
		assertEquals(m[12] + m[13] + m[15] + m[9], 30);
		assertEquals(m[14] + m[16] + m[8], 1);
		assertEquals(m[15] + m[17] + m[9], 1);
		assertEquals(m[20] + m[21] + m[22], 1);
		assertEquals(m[23] + m[24], 1);
		assertEquals(m[25] + m[26], 1);
		assertEquals(m[14] + m[15] + m[19] + m[27], 5);
		assertEquals(m[0] + m[4], 1);
		assertEquals(m[1] + m[5], 1);
		assertEquals(m[2] + m[6], 1);
		assertEquals(m[3] + m[7], 3);
	}
}
