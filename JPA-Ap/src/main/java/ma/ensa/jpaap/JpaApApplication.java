package ma.ensa.jpaap;

import ma.ensa.jpaap.entities.Patient;
import ma.ensa.jpaap.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDate;
import java.util.Random;
import java.util.List;


@SpringBootApplication

// @SpringBootApplication  regroupe
// 1. @Configuration : cette classe peut contenir des @Bean
// 2. @ComponentScan: Spring cherche automatiquement :
//
//@Component
//
//@Service
//
//@Repository
//
//@Controller
// 3. @EnableAutoConfiguration  :Spring Boot configure tout seul :
//
//DataSource
//
//JPA
//
//Tomcat
//
//Hibernate…

public class JpaApApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner (PatientRepository patientRepository){
        return args -> {

            Random random = new Random();

            List<String> noms = List.of(
                    "Hassan", "Mohammed", "Hanane", "Ibrahim",
                    "Youssef", "Khadija", "Sara", "Amine"
            );

            for (int i = 0; i < 100; i++) {

                String nom = noms.get(random.nextInt(noms.size()));

                // Date de naissance entre 1980 et 2015
                int year = 1980 + random.nextInt(36);
                int month = 1 + random.nextInt(12);
                int day = 1 + random.nextInt(28); // safe pour tous les mois

                LocalDate dateNaissance = LocalDate.of(year, month, day);

                boolean malade = random.nextBoolean();

                int score = random.nextInt(101); // score entre 0 et 100

                patientRepository.save(
                        new Patient(null, nom, dateNaissance, malade, score)
                );
            }


            patientRepository.findAll().forEach( p ->{
                System.out.println(p.getNom());
            });
        };
    }
}
