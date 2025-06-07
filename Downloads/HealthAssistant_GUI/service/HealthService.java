package service;

import model.DiagnosisEngine;
import model.MedicalProvider;

import java.util.*;

public class HealthService {

    private final DiagnosisEngine diagnosisEngine;
    private final Map<String, List<MedicalProvider>> providerMap;
    private final String[] symptoms;

    public HealthService() {
        this.diagnosisEngine = new DiagnosisEngine();
        this.providerMap = new HashMap<>();
        this.symptoms = diagnosisEngine.getAllSymptoms().toArray(new String[0]);
        initializeProviders();
    }

    public String[] getSymptoms() {
        return symptoms;
    }

    public List<String> getSelectedSymptoms(String[] choices) {
        List<String> selected = new ArrayList<>();
        for (String choice : choices) {
            int index = Integer.parseInt(choice.trim()) - 1;
            if (index >= 0 && index < symptoms.length) {
                selected.add(symptoms[index]);
            }
        }
        return selected;
    }

    public String getDiagnosis(List<String> symptoms) {
        return diagnosisEngine.diagnose(symptoms);
    }

    public List<MedicalProvider> getProviders(String diagnosis) {
        return providerMap.getOrDefault(diagnosis, providerMap.get("Default"));
    }
private void initializeProviders() {
    // Infectious Diseases
    providerMap.put("COVID-19", Arrays.asList(
        new MedicalProvider("Dr. Alemayehu Kebede - Infectious Disease Specialist"),
        new MedicalProvider("Dr. Selamawit Haile - Pulmonologist"),
        new MedicalProvider("St. Paul's Hospital Millennium Medical College"),
        new MedicalProvider("Tikur Anbessa Specialized Hospital"),
        new MedicalProvider("ALERT Hospital"),
        new MedicalProvider("Yekatit 12 Hospital"),
        new MedicalProvider("Black Lion Hospital"),
        new MedicalProvider("Addis Hiwot Hospital"),
        new MedicalProvider("Landmark General Hospital"),
        new MedicalProvider("Zewditu Memorial Hospital")
    ));

    providerMap.put("Malaria", Arrays.asList(
        new MedicalProvider("Dr. Mesfin Wossen - Tropical Disease Specialist"),
        new MedicalProvider("Dr. Eden Teshome - General Physician"),
        new MedicalProvider("Armauer Hansen Research Institute"),
        new MedicalProvider("Ethiopian Public Health Institute"),
        new MedicalProvider("St. Peter's Specialized Hospital")
    ));

    providerMap.put("Tuberculosis", Arrays.asList(
        new MedicalProvider("Dr. Yohannes Negash - Pulmonologist"),
        new MedicalProvider("Dr. Sara Mohammed - TB Specialist"),
        new MedicalProvider("ALERT Center TB Clinic"),
        new MedicalProvider("St. Peter's TB Specialized Hospital"),
        new MedicalProvider("Tikur Anbessa TB Clinic")
    ));

    // Respiratory Conditions
    providerMap.put("Asthma", Arrays.asList(
        new MedicalProvider("Dr. Michael Asrat - Pulmonologist"),
        new MedicalProvider("Dr. Helen Girma - Allergy Specialist"),
        new MedicalProvider("Addis Ababa Chest Clinic"),
        new MedicalProvider("Bole Respiratory Center"),
        new MedicalProvider("Myungsung Christian Medical Center Respiratory Unit")
    ));

    providerMap.put("Pneumonia", Arrays.asList(
        new MedicalProvider("Dr. Daniel Mekonnen - Pulmonologist"),
        new MedicalProvider("Dr. Sofia Abebe - Pediatric Pulmonologist"),
        new MedicalProvider("St. Paul's Hospital Respiratory Unit"),
        new MedicalProvider("Tikur Anbessa Emergency Department")
    ));
    providerMap.put("Migraine", Arrays.asList(
        new MedicalProvider("Dr. Sara Tesfaye - Neurologist"),
        new MedicalProvider("Dr. Michael Asrat - Neurologist"),
        new MedicalProvider("Addis Ababa Neurology Clinic"),
        new MedicalProvider("Bole Medical Center Neurology Department")
    ));

    providerMap.put("Parkinson's", Arrays.asList(
        new MedicalProvider("Dr. Samson Gebre - Movement Disorder Specialist"),
        new MedicalProvider("Dr. Bethlehem Ayele - Neurologist"),
        new MedicalProvider("Black Lion Hospital Neurology Department")
    ));

    providerMap.put("Food Poisoning", Arrays.asList(
        new MedicalProvider("Dr. Mekdes Berhanu - Gastroenterologist"),
        new MedicalProvider("Dr. Yohannes Tadesse - Internal Medicine"),
        new MedicalProvider("Myungsung Christian Medical Center"),
        new MedicalProvider("Bethzatha General Hospital Emergency")
    ));

    providerMap.put("Cholera", Arrays.asList(
        new MedicalProvider("Dr. Tewodros Haile - Infectious Disease Specialist"),
        new MedicalProvider("Dr. Kalkidan Mekonnen - Emergency Medicine"),
        new MedicalProvider("Ethiopian Public Health Institute"),
        new MedicalProvider("St. Peter's Specialized Hospital Isolation Unit")
    ));

    // Cardiovascular Conditions
    providerMap.put("Heart Attack", Arrays.asList(
        new MedicalProvider("Dr. Girma Tefera - Cardiologist"),
        new MedicalProvider("Dr. Selamawit Fisseha - Interventional Cardiologist"),
        new MedicalProvider("Cardiac Center Ethiopia"),
        new MedicalProvider("St. Paul's Hospital Cardiac Unit")
    ));
    providerMap.put("Diabetes", Arrays.asList(
        new MedicalProvider("Dr. Kalkidan Tsehay - Endocrinologist"),
        new MedicalProvider("Dr. Samuel Wolde - Diabetologist"),
        new MedicalProvider("Diabetes Center Ethiopia"),
        new MedicalProvider("Black Lion Hospital Endocrinology Clinic")
    ));

    providerMap.put("Kidney Stone", Arrays.asList(
        new MedicalProvider("Dr. Yonas Bekele - Urologist"),
        new MedicalProvider("Dr. Mahlet Assefa - Nephrologist"),
        new MedicalProvider("Myungsung Christian Medical Center Urology Department"),
        new MedicalProvider("St. Paul's Hospital Renal Unit")
    ));
    providerMap.put("Depression", Arrays.asList(
        new MedicalProvider("Dr. Bethlehem Ayele - Psychiatrist"),
        new MedicalProvider("Dr. Samuel Getachew - Clinical Psychologist"),
        new MedicalProvider("Amanuel Mental Specialized Hospital"),
        new MedicalProvider("St. Paul's Hospital Psychiatry Department")
    ));

    providerMap.put("Throat Infection", Arrays.asList(
        new MedicalProvider("Dr. Yonatan Mulu - ENT Specialist"),
        new MedicalProvider("Dr. Helen Assefa - ENT Specialist"),
        new MedicalProvider("Hayat Hospital ENT Department"),
        new MedicalProvider("Zewditu Memorial Hospital ENT Clinic")
    ));

    providerMap.put("Flu", Arrays.asList(
        new MedicalProvider("Dr. Eden Teshome - General Physician"),
        new MedicalProvider("Dr. Samuel Wolde - Internal Medicine"),
        new MedicalProvider("Bole Medical Center"),
        new MedicalProvider("Landmark General Hospital")
    ));

    providerMap.put("Bronchitis", Arrays.asList(
        new MedicalProvider("Dr. Michael Asrat - Pulmonologist"),
        new MedicalProvider("Dr. Sofia Abebe - Respiratory Specialist"),
        new MedicalProvider("Addis Ababa Chest Clinic"),
        new MedicalProvider("Myungsung Christian Medical Center")
    ));

    providerMap.put("Default", Arrays.asList(
        new MedicalProvider("Dr. Samuel Wolde - General Physician"),
        new MedicalProvider("Black Lion Hospital General Clinic"),
        new MedicalProvider("St. Paul's Hospital Emergency Department"),
        new MedicalProvider("Tikur Anbessa Specialized Hospital")
    ));
}
}