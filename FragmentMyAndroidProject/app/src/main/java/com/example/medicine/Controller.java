package com.example.medicine;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Controller implements IController {
    public List<Patient> patients = new ArrayList<>();
    public List<SalesMedicalAgents> salesMedicalAgents = new ArrayList<>();
    public DBController dbController;

    public Controller(Context context) {
        patients = new ArrayList<>();
        salesMedicalAgents = new ArrayList<>();
        dbController = new DBController(context);
    }

    @Override
    public long addPatient(Patient patient) {
        return dbController.addPatient(patient);
    }

    @Override
    public long addTherapy(Therapy therapy) {
        return dbController.addTherapy(therapy);
    }

    @Override
    public void addNewPatientAndFirstTherapy(Patient patient, Therapy therapy) {
        dbController.addNewPatientAndFirstTherapy(patient, therapy);
    }

    @Override
    public Cursor getPatientFromCursor(int countField) {
        return dbController.getPatientsForCursor(countField);
    }

    @Override
    public Cursor getAllDatesTherapy(int patient_id) {
        return dbController.getAllDatesTherapy(patient_id);
    }

    @Override
    public Cursor getAllTherapiesFromDate(int grp_id) {
        return dbController.getAllTherapiesFromDate(grp_id);
    }

    @Override
    public void addTherapyIntoPatient(int patient_Id, Therapy therapy) {
        dbController.addTherapyIntoPatient(patient_Id, therapy);
    }

    @Override
    public List<Patient> getFullListsPatients() {
        return dbController.getFullListsPatients();
    }

    @Override
    public List<Therapy> getFullListTerapyFromPatient(int patient_id) {
        return dbController.getFullListTerapyFromPatient(patient_id);
    }

    @Override
    public List<Therapy> getFullListTerapy() {
        return dbController.getFullListTerapy();
    }

    @Override
    public Therapy getTherapy(int therapy_id) {
        return dbController.getTherapy(therapy_id);
    }

    @Override
    public Therapy getTherapyFromConnectTable(int patTher_id) {
        return dbController.getTherapyFromConnectTable(patTher_id);
    }

    @Override
    public Patient getPatient(int patient_id) {
        return dbController.getPatient(patient_id);
    }

    @Override
    public int updatePatient(Patient patient) {
        return dbController.updatePatient(patient);
    }

    @Override
    public int updateTherapy(Therapy therapy) {
        return dbController.updateTherapy(therapy);
    }

    @Override
    public void deletePatient(int patient_id) {
        dbController.deletePatient(patient_id);
    }

    @Override
    public void deleteAllPatients() {
        dbController.deleteAllPatients();
    }

    @Override
    public void deleteAllTherapies() {
        dbController.deleteAllTherapies();
    }

    @Override
    public void deleteAllTherapyFromPatient(int patient_id) {
        dbController.deleteAllTherapyFromPatient(patient_id);
    }

    @Override
    public void deleteTherapyFromPatient(int patient_id, int therapy_id) {
        dbController.deleteTherapyFromPatient(patient_id, therapy_id);
    }

    @Override
    public int getCountPatient() {
        return dbController.getCountPatient();
    }

    @Override
    public int getCountTherapy() {
        return dbController.getCountTherapy();
    }

    @Override
    public Map<Integer, String> getPatientTherapy(int patient_id) {
        return dbController.getPatientTherapy(patient_id);
    }

    @Override
    public Map<Integer, Integer> getUniqPatTherapy(int patient_id) {
        return dbController.getUniqPatTherapy(patient_id);
    }

    @Override
    public Map<Integer, Integer> getUniqPatTherapy2(int patient_id) {
        return dbController.getUniqPatTherapy2(patient_id);
    }
}
