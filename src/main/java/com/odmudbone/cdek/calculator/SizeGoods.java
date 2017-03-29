package com.odmudbone.cdek.calculator;

/**
 * Represents goods parameter of request
 * with weight and it`s three direct size parameters
 */
class SizeGoods implements Goods {

    /**
     * weight of item
     * sets in kilograms
     */
    private float weight;

    /**
     * length if item
     * sets in sm
     */
    private int length;
    /**
     * width if item
     * sets in sm
     */
    private int width;
    /**
     * height if item
     * sets in sm
     */
    private int height;

    SizeGoods(float weight, int length, int width, int height){

        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;

    }

}
