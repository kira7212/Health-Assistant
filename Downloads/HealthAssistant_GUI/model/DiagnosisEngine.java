package model;

import java.util.*;

public class DiagnosisEngine {

    private final Map<String, Map<String, Integer>> symptomDiseaseMap;
    private final List<String> allSymptoms;

    public DiagnosisEngine() {
        symptomDiseaseMap = new HashMap<>();
        allSymptoms = new ArrayList<>();
        initialize();
    }

    public List<String> getAllSymptoms() {
        return allSymptoms;
    }

    private void initialize() {
       
        addSymptom("Fever", Map.of("COVID-19", 3, "Malaria", 2, "Flu", 2, "Pneumonia", 1));
        addSymptom("Cough", Map.of("COVID-19", 3, "Tuberculosis", 2, "Bronchitis", 2, "Asthma", 1));
        addSymptom("Shortness of breath", Map.of("Asthma", 3, "COVID-19", 2, "Pneumonia", 2, "Heart Disease", 1));
        addSymptom("Sore throat", Map.of("Throat Infection", 3, "Flu", 2, "COVID-19", 1, "Strep Throat", 2));
        addSymptom("Runny nose", Map.of("Common Cold", 3, "Allergy", 2, "Flu", 1));
        addSymptom("Sneezing", Map.of("Allergy", 3, "Common Cold", 2));
        addSymptom("Chest pain", Map.of("Heart Attack", 3, "Angina", 2, "Pneumonia", 1, "GERD", 1));
        
        // Head/Neurological Symptoms
        
        addSymptom("Dizziness", Map.of("Anemia", 2, "Low Blood Pressure", 2, "Vertigo", 3));
        addSymptom("Numbness", Map.of("Stroke", 3, "Diabetes", 2, "Pinched Nerve", 1));
        
        // Gastrointestinal Symptoms
        addSymptom("Nausea", Map.of("Food Poisoning", 3, "Pregnancy", 2, "Gastritis", 2, "Migraine", 1));
        addSymptom("Diarrhea", Map.of("Cholera", 3, "Food Poisoning", 2, "Irritable Bowel Syndrome", 2));
        addSymptom("Abdominal pain", Map.of("Appendicitis", 3, "Gastritis", 2, "Gallstones", 2));
        addSymptom("Constipation", Map.of("IBS", 2, "Low Fiber Diet", 2, "Hypothyroidism", 1));
        addSymptom("Vomiting", Map.of("Food Poisoning", 3, "Migraine", 2, "Pregnancy", 2));
        
        // Musculoskeletal Symptoms
        addSymptom("Joint pain", Map.of("Arthritis", 3, "Lupus", 2, "Gout", 2));
        addSymptom("Back pain", Map.of("Muscle Strain", 2, "Kidney Infection", 2, "Herniated Disc", 3));
        addSymptom("Muscle pain", Map.of("Flu", 2, "Fibromyalgia", 3));
        
        // Skin Symptoms
        addSymptom("Skin rash", Map.of("Allergy", 2, "Measles", 3, "Chickenpox", 3));
        addSymptom("Itching", Map.of("Allergy", 2, "Scabies", 3, "Eczema", 2));
        addSymptom("Yellow skin", Map.of("Hepatitis", 3, "Liver Disease", 2));
       
        // Urinary Symptoms
        addSymptom("Painful urination", Map.of("UTI", 3, "Kidney Infection", 2, "STD", 2));
        addSymptom("Blood in urine", Map.of("Kidney Stone", 3, "Bladder Infection", 2, "Kidney Disease", 2));
        
        // Eye/Ear Symptoms
        addSymptom("Blurred vision", Map.of("Diabetes", 2, "Glaucoma", 3, "Cataract", 2));
        addSymptom("Eye pain", Map.of("Glaucoma", 3, "Migraine", 1, "Eye Infection", 2));
        addSymptom("Ear pain", Map.of("Ear Infection", 3, "TMJ Disorder", 1));
       
        // General Symptoms
        addSymptom("Fatigue", Map.of("Anemia", 2, "Depression", 2, "COVID-19", 1, "Hypothyroidism", 3));
        addSymptom("Swollen lymph nodes", Map.of("Infection", 2, "Lymphoma", 3));
        addSymptom("Night sweats", Map.of("Tuberculosis", 2, "Lymphoma", 2, "Menopause", 3));
       
       // Mental Health Symptoms
        addSymptom("Mood swings", Map.of("Bipolar Disorder", 3, "PMS", 2, "Menopause", 2));
        addSymptom("Depression", Map.of("Depressive Disorder", 3, "Bipolar Disorder", 2));
       
    }
    private void addSymptom(String symptom, Map<String, Integer> diseaseWeights) {
        symptomDiseaseMap.put(symptom, diseaseWeights);
        if (!allSymptoms.contains(symptom)) {
            allSymptoms.add(symptom);
        }
    }

    public String diagnose(List<String> symptoms) {
        Map<String, Integer> diagnosisScores = new HashMap<>();

        for (String symptom : symptoms) {
            Map<String, Integer> diseases = symptomDiseaseMap.getOrDefault(symptom, new HashMap<>());
            for (Map.Entry<String, Integer> entry : diseases.entrySet()) {
                diagnosisScores.put(entry.getKey(),
                    diagnosisScores.getOrDefault(entry.getKey(), 0) + entry.getValue());
            }
        }

        if (diagnosisScores.isEmpty()) {
            return "Unable to determine. Please consult a healthcare provider.";
        }

        return diagnosisScores.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("Unknown condition");
        
    }
}