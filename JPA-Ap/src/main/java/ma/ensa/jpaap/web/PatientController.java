package ma.ensa.jpaap.web;

import lombok.AllArgsConstructor;
import ma.ensa.jpaap.entities.Patient;
import ma.ensa.jpaap.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.IntStream;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;

    @GetMapping("/index")
    public String patients(
            Model model,
            @RequestParam(defaultValue = "0") int page
,  @RequestParam(name = "keyword" ,defaultValue = "") String keyword   ) {
        Page<Patient> patientsPage =
                patientRepository.findByNomContains( keyword,PageRequest.of(page, 5));

        int totalPages = patientsPage.getTotalPages();
        int currentPage = page;

        int start = Math.max(0, currentPage - 1);
        int end = Math.min(totalPages - 1, currentPage + 1);

        model.addAttribute("ListePatients", patientsPage.getContent());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pages", IntStream.rangeClosed(start, end).toArray());
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("keyword",keyword);
        return "patient";
    }
    @GetMapping("/delete")
    public String Delete(Long id ,String keyword , int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page ="+page+"&keyword="+keyword;
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/index";
    }

    //si je veux Utilise l'approche rendu cote client
    @GetMapping("/patients")
    @ResponseBody // Dire a dispatcherServlet  que ce resultat l'a serialise dans le corps de la reponse
    //RestController "cest un controlleur qui utilise toute les methodes ResponseBody
    public List<Patient> patientList(){
        return patientRepository.findAll();
    }
}
