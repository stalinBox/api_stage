package ec.gob.mag.stageRenagro.util;

public class ModifyArray {

	@SuppressWarnings("null")
	public static String[] copyArrayString(String[] array, int limitInitial, int LimitFinal) {
		String[] arrayReturn = null;
		for (; limitInitial <= LimitFinal; limitInitial++) {
			arrayReturn[limitInitial] = array[limitInitial];
		}
		return arrayReturn;
	}

}
