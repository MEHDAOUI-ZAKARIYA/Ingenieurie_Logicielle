package ma.ensa.hospital.service;

import lombok.Data;
import ma.ensa.hospital.entities.Consultation;
import ma.ensa.hospital.entities.Medecin;
import ma.ensa.hospital.entities.Patient;
import ma.ensa.hospital.entities.RenderVous;
import ma.ensa.hospital.repositories.ConsultationRepository;
import ma.ensa.hospital.repositories.MedecinRepository;
import ma.ensa.hospital.repositories.PatientRepository;
import ma.ensa.hospital.repositories.RenderVousRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@Data
public class HospitalServiceImpl implements HospitalService {

    private PatientRepository patientRepository;
    private MedecinRepository medecinRepository;
    private ConsultationRepository consultationRepository;
    private RenderVousRepository rendezVousRepository;


    public HospitalServiceImpl(PatientRepository patientRepository, MedecinRepository medecinRepository, ConsultationRepository consultationRepository, RenderVousRepository rendezVousRepository) {
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.consultationRepository = consultationRepository;
        this.rendezVousRepository = rendezVousRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {

        return medecinRepository.save(medecin);
    }

    @Override
    public RenderVous saveRenderVous(RenderVous renderVous) {
        renderVous.setId(UUID.randomUUID().toString());
        return  rendezVousRepository.save(renderVous);
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {
       return consultationRepository.save(consultation);
    }
}
