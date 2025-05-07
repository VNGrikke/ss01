package model;

public class User {
    private String name;
    private String className;
    private String type;
    private String licensePlate;

    public User(String name, String className, String type, String licensePlate) {
        this.name = name;
        this.className = className;
        this.type = type;
        this.licensePlate = licensePlate;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    public String getType() {
        return type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}