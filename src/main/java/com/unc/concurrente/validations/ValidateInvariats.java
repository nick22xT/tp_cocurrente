package com.unc.concurrente.validations;

public class ValidateInvariats {
	
	public static void ValidateInvariants(Integer[] m) {
		assert m[10].intValue() + m[11].intValue() + m[14].intValue() + m[8].intValue() == 30;
		assert m[12].intValue() + m[13].intValue() + m[15].intValue() + m[9].intValue() == 30;
		assert m[14].intValue() + m[16].intValue() + m[8].intValue() == 1;
		assert m[15].intValue() + m[17].intValue() + m[9].intValue() == 1;
		assert m[20].intValue() + m[21].intValue() + m[22].intValue() == 1;
		assert m[23].intValue() + m[24].intValue() == 1;
		assert m[25].intValue() + m[26].intValue() == 1;
		assert m[14].intValue() + m[15].intValue() + m[19].intValue() + m[27].intValue() == 5;
		assert m[0].intValue() + m[4].intValue() == 1;
		assert m[1].intValue() + m[5].intValue() == 1;
		assert m[2].intValue() + m[6].intValue() == 1;
		assert m[3].intValue() + m[7].intValue() == 3;
	}
}
