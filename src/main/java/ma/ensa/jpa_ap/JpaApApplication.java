package ma.ensa.jpa_ap;


import ma.ensa.jpa_ap.entities.Patient;
import ma.ensa.jpa_ap.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.print.attribute.standard.PageRanges;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class JpaApApplication implements CommandLineRunner
{
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {

        SpringApplication.run(JpaApApplication.class, args); // Demarage du conteneur de spring
    }

    @Override
    public void run(String... args) throws Exception {


//        patientRepository.save(new Patient(null , "Mehdaoui" , LocalDate.of(2000, 5, 20)  , false , 56));
//        patientRepository.save(new Patient(null , "hassan" , LocalDate.of(2001, 7, 19)  , true , 100));
//        patientRepository.save(new Patient(null , "ibrahim" , LocalDate.of(2004, 5, 28)  , false , 90));


        for (int i =0 ; i < 100 ; i ++){
            patientRepository.save(new Patient((Long) null, "Mehdaoui" , LocalDate.of(2000, 5, 20)  , Math.random() > 0.5, (int) (Math.random()*100)));
        }
        System.out.println("-----------------------------");

        // Lorsque il ya beaucoup de donnees il me faut de paginer les donnees
        Page<Patient> patients = patientRepository.findAll(PageRequest.of(0 , 5));

//        System.out.println("Total pages : " +patients.getTotalPages());
//        System.out.println("Total elements : " + patients.getTotalElements());
//        System.out.println("Num page  :" + patients.getNumber());
//        List<Patient> content = patients.getContent();

//        List<Patient> malade =  patientRepository.findPatientByMalade(true);
//
//        malade.forEach( p -> {
//            System.out.println(p.getId());
//            System.out.println(p.getNom());
//            System.out.println(p.getDatenaissance());
//            System.out.println(p.getScore());
//            System.out.println(p.isMalade());
//
//        });

//        Page<Patient> Malade = patientRepository.findByMalade(true,PageRequest.of(0,5));
//        Malade.forEach( p -> {
//            System.out.println(p.getId());
//            System.out.println(p.getNom());
//            System.out.println(p.getDatenaissance());
//            System.out.println(p.getScore());
//            System.out.println(p.isMalade());
//
//        });

        List<Patient> patientList = patientRepository.chercherPatient("%M%" , 70);

        patientList.forEach( p -> {
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getDateNaissance());
            System.out.println(p.getScore());
            System.out.println(p.isMalade());

        });


//        System.out.println("---------------------------------------");
//        Patient patient  = patientRepository.findById(1L).orElseThrow(()-> new RuntimeException("Patient not found"));
//        System.out.println("the patient is found  : ");
//        System.out.println(patient.getId());
//        System.out.println(patient.getNom());
//        System.out.println(patient.getDatenaissance());
//        System.out.println(patient.getScore());
//        System.out.println(patient.isMalade());

//        patient.setScore(80000);
//        patientRepository.save(patient);
//
//        System.out.println("the patient "+patient.getNom()+ " after updating is : ");
//        System.out.println(patient.getId());
//        System.out.println(patient.getNom());
//        System.out.println(patient.getDatenaissance());
//        System.out.println(patient.getScore());
//        System.out.println(patient.isMalade());

    }


}
