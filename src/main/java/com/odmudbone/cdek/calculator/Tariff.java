package com.odmudbone.cdek.calculator;

/**
 * Object, that represents tariff
 */
class Tariff {

    /**
     * Id of tariff
     */
    private int id;
    /**
     * Priority of tariff
     */
    private int priority;

    Tariff(int id, int priority){
        this.id = id;
        this.priority = priority;
    }

    int getId() {
        return id;
    }

}
