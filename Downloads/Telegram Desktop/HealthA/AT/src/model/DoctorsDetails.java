package model;

import java.util.List;

public class DoctorsDetails<x, y>{
    public final List<x> doctorNames;
    public final List<y> hospitals;

    public DoctorsDetails(List<x> list1, List<y> list2) {
        this.doctorNames = list1;
        this.hospitals = list2;
    }
}
