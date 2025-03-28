package org.hprtech.Model;


public class Mobile {

    int id;
    String name;
    String brand;
    int ram;
    int externalStorage;

    public Mobile(int id, String name, String brand, int ram, int externalStorage) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.ram = ram;
        this.externalStorage = externalStorage;
    }

    public Mobile(){
        super();
    }

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getExternalStorage() {
        return externalStorage;
    }

    public void setExternalStorage(int externalStorage) {
        this.externalStorage = externalStorage;
    }

    @Override
    public String toString() {
        return "Mobile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", ram=" + ram +
                ", externalStorage=" + externalStorage +
                '}';
    }
}
