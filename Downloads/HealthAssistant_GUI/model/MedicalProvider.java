package model;

public class MedicalProvider {
    private final String name;

    public MedicalProvider(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}