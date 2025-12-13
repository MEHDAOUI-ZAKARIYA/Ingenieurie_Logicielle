package ma.ensa.jpa_ap.repositories;

import ma.ensa.jpa_ap.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findPatientByMalade(boolean maladie);

    Page<Patient> findByMalade(boolean maladie , Pageable pageable);

    List<Patient>  findByMaladeAndScoreLessThan(boolean malade ,int score);

    List<Patient> findByDateNaissanceBetweenAndMaladeIsTrueOrNomLike(Date d1 , Date d2 , String mc);

    // pour Eviter les nom de fonctions qui seraient trop Long like the functions above we use the annotations @Query to explain what the function do exactly and inject the parameters by the annotations : @Param

    @Query("select p from Patient p where p.dateNaissance between :x and :y or p.nom like :z")
    List<Patient> chercherPatient (@Param("x") Date d1 ,@Param("y") Date d2 ,@Param("z") String Nom);



    //Autre exemples:

    @Query("select p from Patient p where p.nom like :x and p.score <:y")
    List<Patient> chercherPatient (@Param("x") String Nom , @Param("y") int scoreMin);
}
