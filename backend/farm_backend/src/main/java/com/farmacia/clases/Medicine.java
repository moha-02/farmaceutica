package com.farmacia.clases;

public class Medicine {

    private int id;
    private String name;
    private float tmax;
    private float tmin;

    public Medicine(int id, String name, float tmax, float tmin) {
        this.id = id;
        this.name = name;
        this.tmax = tmax;
        this.tmin = tmin;
    }
    public Medicine() {}
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getTmax() {
        return tmax;
    }
    public void setTmax(float tmax) {
        this.tmax = tmax;
    }
    public float getTmin() {
        return tmin;
    }
    public void setTmin(float tmin) {
        this.tmin = tmin;
    }

    public void load(int id){

    }
}
