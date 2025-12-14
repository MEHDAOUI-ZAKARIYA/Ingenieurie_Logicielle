package ma.ensa.hospital.service;

import ma.ensa.hospital.entities.Consultation;
import ma.ensa.hospital.entities.Medecin;
import ma.ensa.hospital.entities.Patient;
import ma.ensa.hospital.entities.RenderVous;

public interface HospitalService {
    Patient savePatient(Patient patient);
    Medecin saveMedecin (Medecin medecin);
    RenderVous saveRenderVous (RenderVous renderVous);
    Consultation saveConsultation (Consultation consultation);
}
