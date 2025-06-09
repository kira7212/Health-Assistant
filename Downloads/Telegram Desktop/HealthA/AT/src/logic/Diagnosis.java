package src.logic;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import model.Disease;
import model.Doctors;
import model.DoctorsDetails;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.*;

public class Diagnosis {
    private List<Doctors> doctors;
    private List<Disease> diseases;
    private Map<String, List<String>> symptoms;

    private final String symptomsJsonPath = "src/data/symptoms.json";
    private final String diseasesJsonPath = "src/data/diseases.json";
    private final String doctorsJsonPath = "src/data/doctors.json";

    public Diagnosis() throws FileNotFoundException {
        Gson gson = new Gson();

        Type listDisease = new TypeToken<List<Disease>>() {}.getType();

        Type listSpecialist = new TypeToken<List<Doctors>>() {}.getType();

        doctors = gson.fromJson(new FileReader(doctorsJsonPath), listSpecialist);

        diseases = gson.fromJson(new FileReader(diseasesJsonPath), listDisease);

        symptoms = gson.fromJson(new FileReader(symptomsJsonPath), new TypeToken<Map<String, List<String>>>() {}.getType());

    }


    public Map<String, Double> diagnose(List<String> selectedSymptoms) {
        Map<String, Double> results = new HashMap<>();

        for (Disease disease : diseases) {
            if (disease.symptoms == null || disease.symptoms.isEmpty()) continue;

            int matchCount = 0;

            for (String string : selectedSymptoms) {
                if (disease.symptoms.contains(string)) {
                    matchCount++;
                }
            }

            double chances = (double) matchCount / disease.symptoms.size();
            if (chances > 0) {
                results.put(disease.name, chances);
            }
        }


        return sortDiseases(results);
    }

    public DoctorsDetails<String, String> getDoctorsDetail(String disease) {

        List<String> qualifiedDoctors = new ArrayList<>();
        List<String> hospitals = new ArrayList<>();


        String neededSpecialist = null;


        for (Disease diseaseInList : diseases) {
            if (diseaseInList.name.equalsIgnoreCase(disease)) {
                neededSpecialist = diseaseInList.specialist;
                break;
            }
        }

        if (neededSpecialist == null) {
            return new DoctorsDetails<>(qualifiedDoctors, hospitals);
        }


        for (Doctors doctorsInList : doctors) {
            if (doctorsInList.specialist != null &&
                    doctorsInList.specialist.contains(neededSpecialist)) {

                qualifiedDoctors.add(doctorsInList.name);
                hospitals.add(doctorsInList.hospital);
            }
        }

        return new DoctorsDetails<>(qualifiedDoctors, hospitals);
    }


    private Map<String, Double> sortDiseases(Map<String, Double> map) {
        List<Map.Entry<String, Double>> list = new ArrayList<>(map.entrySet());
        list.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

        Map<String, Double> sortedData = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : list) {
            sortedData.put(entry.getKey(), entry.getValue());
        }
        return sortedData;
    }

    public Map<String, List<String>> getCategorizedSymptoms() {
        return symptoms;
    }
}


