package ma.ensa.hospital;

import ma.ensa.hospital.entities.*;
import ma.ensa.hospital.repositories.MedecinRepository;
import ma.ensa.hospital.repositories.PatientRepository;
import ma.ensa.hospital.repositories.RenderVousRepository;
import ma.ensa.hospital.service.HospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//
//    }

    @Bean // il devrait executer au demarage , et au meme temps va retiurner un objet qui est un objet spring
    CommandLineRunner start (HospitalService hospitalService , PatientRepository patientRepository , RenderVousRepository rendezVousRepository , MedecinRepository medecinRepository){
        return args -> {
            Stream.of("Mohammed","Hassan","Najat").forEach(name -> {
                Patient p = new Patient();
                p.setNom(name);
                p.setDateNuisance(LocalDate.of(2001, 12,18));
                p.setMalade(false);
                hospitalService.savePatient(p);
            });
//            patientRepository.save(new Patient(null ,"Hassan" , LocalDate.of(2000,12,17) ,false , null));

            Stream.of("Mehdaoui","Hassonne","Hanane").forEach(name -> {
                Medecin m = new Medecin();
                m.setNom(name);
                m.setSpecialite(Math.random()>0.5?"Cardio":"Dentist");
                m.setEmail(name +"@gmail.com");
                hospitalService.saveMedecin(m);
            });

            Patient  patient = patientRepository.findById(1L).orElse(null);
            Patient patient1 = patientRepository.findByNom("Hassan");

            Medecin medecin = medecinRepository.findByNom("Hassonne");

            RenderVous rendezVous = new RenderVous();
            rendezVous.setDate(LocalDate.of(2025,12,14));
            rendezVous.setStatus(StatusRDV.PENDING);
            rendezVous.setMedecin((medecin));
            rendezVous.setPatient(patient);

            hospitalService.saveRenderVous(rendezVous);

            RenderVous renderVous = rendezVousRepository.findAll().get(0);
            System.out.println(renderVous.getId());
            Consultation consultation = new Consultation();
            consultation.setDateConsultation(renderVous.getDate());
            consultation.setRendezVous(rendezVous);
            consultation.setRapport("Rapport de la consultation .......");
            hospitalService.saveConsultation(consultation);
  };
}
}
