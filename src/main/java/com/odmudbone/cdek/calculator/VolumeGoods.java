package com.odmudbone.cdek.calculator;

/**
 * Represents goods parameter of request
 * with weight and volume properties
 */
class VolumeGoods implements Goods {

    /**
     * weight of item
     * sets in kilograms
     */
    private float weight;
    /**
     * volume of item
     * sets in cbm
     */
    private float volume;

    VolumeGoods(float weight, float volume){
        this.weight = weight;
        this.volume = volume;
    }

}
