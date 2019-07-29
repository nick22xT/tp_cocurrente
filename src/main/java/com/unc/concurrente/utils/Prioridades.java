package com.unc.concurrente.utils;

public enum Prioridades {

    T0(0), T1(1), T2(2), T3(3), T4(4), T5(5), T6(6), T7(7), T8(8), T9(9), T10(10), T11(11), T12(12), T13(13), T14(14), T15(15), T16(16), T17(17),T18(18),T19(19);

    int numPrioridad;

    Prioridades(int numPrioridad) {
        this.numPrioridad = numPrioridad;
    }

    public int getNumPrioridad() {
        return numPrioridad;
    }

}

