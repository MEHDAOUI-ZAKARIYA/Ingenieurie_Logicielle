package ma.ensa.jpaap.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.ensa.jpaap.entities.Patient;
import ma.ensa.jpaap.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.IntStream;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;

    @GetMapping("/index")
    public String patients(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(name = "keyword", defaultValue = "") String keyword) {

        Page<Patient> patientsPage = patientRepository.findByNomContains(
                keyword,
                PageRequest.of(page, 5)
        );

        int totalPages = patientsPage.getTotalPages();
        int currentPage = page;

        int start = Math.max(0, currentPage - 1);
        int end = Math.min(totalPages - 1, currentPage + 1);

        model.addAttribute("ListePatients", patientsPage.getContent());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pages", IntStream.rangeClosed(start, end).toArray());
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("keyword", keyword);

        return "patient";
    }

    @GetMapping("/delete")
    public String delete(Long id, String keyword, int page) {
        patientRepository.deleteById(id);
        // Correction : pas d'espace après "page ="
        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/index";
    }

    // API REST pour récupérer les patients (rendu côté client)
    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> patientList() {
        return patientRepository.findAll();
    }

    @GetMapping("/formPatients")
    public String formPatients(Model model) {
        model.addAttribute("patient", new Patient());
        return "formPatients";
    }

    // ✅ NOUVELLE MÉTHODE : Enregistrer un patient
    @PostMapping("/save")
    public String save(
//            Récupération et validation des données du formulaire
//                    @ModelAttribute("patient")
//                            Lie les champs du formulaire HTML à l’objet Patient
//                    Les noms des inputs doivent correspondre aux attributs de Patient
            @Valid @ModelAttribute("patient") Patient patient,
            BindingResult bindingResult,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue ="" ) String keyword,
            Model model) {

        // Si des erreurs de validation existent, retourner au formulaire
        if (bindingResult.hasErrors()) {
            return "formPatients";
        }

        // Sauvegarder le patient dans la base de données
        patientRepository.save(patient);

        // Rediriger vers la liste des patients
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }


    @GetMapping("/editPatient")
    public String editPatient(Model model, @RequestParam Long id, String keyword , int page ) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient introuvable"));

        model.addAttribute("patient", patient);
        model.addAttribute("page" , page);
        model.addAttribute("keyword" , keyword);
        return "editPatient";
    }



}