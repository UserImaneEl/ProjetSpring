package gi2.ensakh.apphopital.Web;

import gi2.ensakh.apphopital.Dtos.*;
import gi2.ensakh.apphopital.Entities.*;
import gi2.ensakh.apphopital.Mappers.HopitalMappers;
import gi2.ensakh.apphopital.Repositories.*;
import gi2.ensakh.apphopital.Services.*;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class AppRestController {
    private static final Logger logger = LoggerFactory.getLogger(AppRestController.class);

    public RendezVousRepository rendezVousRepository;
    public ConsultationService ConsultationService;
    private PatientRepository patientRepository;
    private HopitalMappers dtoMapper;
    private MedecinRepository medecinRepository;
    private NoteRepository noteRepository;
    private RendezVousInf repRdv;
    @Autowired
    private NoteInf noteService;
    @Autowired
    private PatientInf patService;
    @Autowired
    private RendezVousInf rendezVousService;
    @Autowired
    private PersonneService personneService;
    @Autowired
    private PersonneRepository repPr;

    @GetMapping("/listeRDVID/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public Patient ListeRDVID(@PathVariable(name = "id") Long Id) {
        String cinMed = rendezVousRepository.findCinPatientByIdRDV(Id);
        return patientRepository.findPatientByCin(cinMed);
    }

    @GetMapping("/SecByRDV/{idRDV}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public Secretaire SecByRDV(@PathVariable(name = "idRDV") Long idRDV) {
        return rendezVousRepository.findSecretaireByRdvId(idRDV);
    }

    @GetMapping("/SEC/RdvBySec/{username}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_SECRETAIRE')")
    public List<PatientRdvDto> listRdvBySec(@PathVariable(name = "username") String username) {
        List<RendezVous> listepatientRdv = rendezVousRepository.listRdvBySec(username);
        List<PatientRdvDto> listpatientRdvDtos = listepatientRdv.stream()
                .map(rdvPatient -> dtoMapper.fromPatientRdv(rdvPatient))
                .collect(Collectors.toList());
        return listpatientRdvDtos;
    }
    @GetMapping("/listMedecins/{username}")
    public List<medecinDto> getAllMedecins(@PathVariable(name = "username") String username) {
        List<Medecin> medecins = medecinRepository.getAllMedecins(username);
        List<medecinDto> list=medecins.stream()
                .map(med -> dtoMapper.fromMedToDto(med))
                .collect(Collectors.toList());
        return list;
    }
    @GetMapping("/listMedecinsAdmin")
    public List<medecinDto> getAllMedecinsAdmin() {
        List<Medecin> medecins = medecinRepository.findAll();
        List<medecinDto> list=medecins.stream()
                .map(med -> dtoMapper.fromMedToDto(med))
                .collect(Collectors.toList());
        return list;
    }
    @GetMapping("medecins/{nom}")
    public List<medecinDto> getMedByName(@PathVariable(name="nom") String nom) {
        List<Medecin> medecins = medecinRepository.findMedecinByKeyword(nom);
        List<medecinDto> med=medecins.stream()
                .map(m -> dtoMapper.fromMedToDto(m))
                .collect(Collectors.toList());
        return med;
     }
    @PostMapping("/valider/{idRdv}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_SECRETAIRE')")
    public void validerRdv(@PathVariable("idRdv") Long idRdv, @RequestBody DtoSecValiderRdv rdv) {
        LocalDateTime date = rdv.getDate();
        String cinP = rdv.getCinP();

            repRdv.ValiderRDV(idRdv, date, cinP);

    }

    @GetMapping("/demandes/count/{username}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_SECRETAIRE')")
    public ResponseEntity<Integer> countDemandes(@PathVariable("username") String username) {

        return ResponseEntity.ok(repRdv.countDemandes(username));
    }

    @GetMapping("/ListDoctors")
    public List<Medecin> medecinList() {
        return medecinRepository.findAll();
    }

    @PostMapping("/priseRdvNewPatient")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_SECRETAIRE')")
    public ResponseEntity<Map<String, String>> priseRDV(@RequestBody RdvDto request) {
        RendezVous rendezVous = dtoMapper.fromRdvDTO(request);
        String patientCin = rendezVous.getPatient().getCin();//iciiiiiiiiii
        String username = rendezVous.getCompte().getUsername();
        String doctorCin = rendezVous.getMedecin().getCin();

        List<StatusRDV> statuses = Arrays.asList(StatusRDV.EnAttente, StatusRDV.valide);
        int NumexistingRdv = rendezVousRepository.RendezVousTaken(doctorCin, patientCin, username, statuses);
        if (NumexistingRdv != 0) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Appointment is already taken.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        } else {
            rendezVousRepository.save(rendezVous);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Appointment created successfully.");
            return ResponseEntity.ok(response);
        }
    }


    @PostMapping("/patient")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_SECRETAIRE')")
    public void patient(@RequestBody Patient patient1) {
        patientRepository.save(patient1);

    }

    @GetMapping("/listpatient")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_SECRETAIRE')")
    public List<Medecin> listpatient() {
        return medecinRepository.findAll();

    }

    @PostMapping("/nbrRDV")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_SECRETAIRE')")
    public int numRDV(@RequestBody checkTime request) {
        String cinMed = request.getCin();
        String username = request.getUsername();
        Date dateDemande = request.getDate_demande();
        return rendezVousRepository.numRdvByDay(cinMed, username, dateDemande);
    }

    @GetMapping("/listRDV")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_SECRETAIRE')")
    public List<RendezVous> listRDV() {
        return rendezVousRepository.findAll();

    }

    @GetMapping("/appointments/latest-time/{date}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_SECRETAIRE')")
    public LocalTime getLatestAppointmentTimeForDate(@PathVariable("date") LocalDate date) {
        String timeString = rendezVousRepository.findFirstAppointmentTimeForDate(date);
        return LocalTime.parse(timeString);
    }

    @GetMapping("/appointments/number/{date}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_SECRETAIRE')")
    public int getNumberRDVByDay(@PathVariable("date") LocalDate date) {
        return rendezVousRepository.countNumberRDVByDay(date);
    }

    @GetMapping("/appointments/proposed-time/{cinMed}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_SECRETAIRE')")
    public Map<String, Object> getProposedAppointmentDateTime(@PathVariable("cinMed") String cin) {

        LocalDate currentDate = LocalDate.now();
        LocalDate nextDay = currentDate.plusDays(1);
        LocalDate proposedDate = null;
        Medecin medecin = medecinRepository.findByCin(cin);
        int numberRDVNextDay = 0;

        while (proposedDate == null && nextDay.isBefore(currentDate.plusDays(200))) {
            if (nextDay.getDayOfWeek() != DayOfWeek.SUNDAY) {
                numberRDVNextDay = rendezVousRepository.countNumberRDVByDay(nextDay);
                if (numberRDVNextDay < medecin.getLimitNumRDV()) {
                    proposedDate = nextDay;
                }
            }
            nextDay = nextDay.plusDays(1);
        }

        if (medecin != null && proposedDate != null) {
            LocalTime proposedTime;
            if (numberRDVNextDay != 0) {
                LocalTime lastAppointmentTime = getLatestAppointmentTimeForDate(proposedDate);
                if (lastAppointmentTime != null) {
                    Integer durationRDV = medecin.getDurationRDV();
                    proposedTime = lastAppointmentTime.plusMinutes(durationRDV);
                } else {
                    return null;
                }
            } else {
                proposedTime = medecin.getDebutRDV();
            }

            Note note = noteRepository.findNotesByDateEventAndMedecin_Cin(proposedDate, cin);
            Map<String, Object> response = new HashMap<>();
            response.put("proposedDateTime", LocalDateTime.of(proposedDate, proposedTime));
            if (note != null) {
                response.put("message", "Note: There is a note for this day. Note Title: " + note.getTitle());
            }
            return response;
        } else {
            return null;
        }
    }

    @GetMapping("/rendez-vous")
    public ResponseEntity<List<RendezVous>> getAllRendezVous() {
        List<RendezVous> RDV = rendezVousService.getAllRendezVous();
        return new ResponseEntity<>(RDV, HttpStatus.OK);
    }

    @GetMapping("/notes")
    public List<noteMedecinDto> getAllNotes() {
        List<noteMedecinDto> notes = noteService.getAllNotes();
        return notes;
    }

    // @GetMapping("/with-patients")
    //public List<RendezVous> getRDVWithPatients() {
    // return rendezVousService.getRDVWithPatients();
    // }


    @GetMapping("/notes/{cin}/{date}")
    public List<noteMedecinDto> getAllNoteOfMedecinInDay(@PathVariable(name = "cin") String cin,
                                                         @PathVariable(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        List<noteMedecinDto> notes = noteService.getAllNoteOfMedecinInDay(cin, date);
        return notes;//format json

    }

    @PostMapping("/notes")
    public Void newNote(@RequestBody noteMedecinDto note) {
        LocalDate date = note.getDateEvent();

        // Ajouter un jour à la date
        LocalDate newDate = date.plusDays(1);

        // Mettre à jour la date dans la note
        note.setDateEvent(newDate);

        noteService.saveNote(note);
        return null;
    }

    //@GetMapping("/medecin")
    //public ResponseEntity<List<Medecin>> getAllNoteOfMedecins() {
    //  List<Medecin> meds = medService.getMedecins();
    //return new ResponseEntity<>(meds, HttpStatus.OK);
    //}
    @DeleteMapping("/notes/{id}")
    public void deleteNote(@PathVariable(name = "id") Long id) {
        noteService.deleteNote(id);

    }

    @PutMapping("/notes/{id}")
    public void updateNote(@PathVariable(name = "id") Long id, @RequestBody String title) {
        noteService.updateNote(id, title);

    }

    @GetMapping("/rendez-vous/{cinMed}")
    public List<patientDetailsDto> getPatientsDetails(@PathVariable(name = "cinMed") String cinMed) {
        List<patientDetailsDto> l = patService.getPatientDetailsByCinMed(cinMed);
        return l;
    }

    @GetMapping("/rendez-vous/reporte/{sec}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_SECRETAIRE')")
    public ResponseEntity<Integer> countRDVreporte(@PathVariable(name = "sec") String sec) {
        return ResponseEntity.ok(repRdv.countRDVreporte(sec));
    }

    @GetMapping("/countMed/{username}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_SECRETAIRE')")
    public ResponseEntity<Integer> countMed(@PathVariable(name = "username") String username) {
        return ResponseEntity.ok(repRdv.countMed(username));
    }

    @GetMapping("/countToday/{username}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_SECRETAIRE')")
    public ResponseEntity<Integer> countRendezVousToday(@PathVariable(name = "username") String username) {
        int count = rendezVousRepository.countRendezVousToday(username);
        return ResponseEntity.ok(count);

    }
    @PostMapping("/ajouterRdv")
    public void ajouterRDV(@RequestBody addPatientDto p) {

            // Recherche du patient par le cin
            Patient patient = patientRepository.findPatientByCin(p.getCin());

            // Si le patient n'existe pas, créer un nouveau patient
            if (patient == null) {
                patient = new Patient();
                patient.setNom(p.getNom());
                patient.setPrenom(p.getPrenom());
                patient.setCin(p.getCin());
                patient.setAdresse(p.getAd());
                patient.setEmail(p.getMail());
                patient.setTel(p.getTel());
                patientRepository.save(patient);
            }

            // Recherche du médecin par le nom
            Medecin medecin = medecinRepository.getMedByNomPrenom(p.getNomPrMed());
            Patient pat=patientRepository.getPatientByCin(p.getCin());


            // Création du rendez-vous
            RendezVous rendezVous = new RendezVous();
            rendezVous.setDate_RDV(p.getDate());
            rendezVous.setMedecin(medecin);
            rendezVous.setPatient(pat);
            rendezVous.setStatusRDV(StatusRDV.valide);
            rendezVousRepository.save(rendezVous);



}

    @GetMapping("/medByUsername/{username}")
    //@PreAuthorize("hasAuthority('SCOPE_ROLE_MEDECIN')")
    public medecinDto findByUsername(@PathVariable(name = "username") String username) {
       Medecin medecin = medecinRepository.findByUsername(username);

        medecinDto m=dtoMapper.fromMedToDto(medecin);
            return m;

    }
    @GetMapping("/infos/{username}")
    PersonneDto getInfos(@PathVariable(name = "username") String username){
        Personne p=personneService.getInf(username);
        PersonneDto per=dtoMapper.fromPerToDto(p);
        return  per;
    }
    @GetMapping("/rendezVous/{id}")
    public  ResponseEntity<List<rdvConsult>> getRdvByMedecin(@PathVariable(name = "id") String id){
        Personne p=personneService.getInf(id);
        List<RendezVous> l=rendezVousRepository.findRdvByMedecin(p.getCin(),StatusRDV.valide);
        List<rdvConsult> re= new ArrayList<>();
        // System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhh"+id+"son cin est "+p.getCin());
        System.out.println(l.size());
        for( RendezVous i : l) {
            System.out.println(i.getPatient().getNom());
            rdvConsult a=new rdvConsult();
            a.setId(i.getId());
            a.setNom(i.getPatient().getNom());
            a.setPrenom(i.getPatient().getPrenom());
            a.setCin(i.getPatient().getCin());

            re.add(a);
        }
        System.out.println("ooooooooooooooooooooooooo"+re.size());
        return new ResponseEntity<>(re, HttpStatus.OK);
    }
    @PostMapping("/consult")
    public ResponseEntity<?> insertConsult(@RequestBody Consultation c) {
        RendezVous r=rendezVousRepository.findRendezVousById(c.getId());
        // c.setIdM(rendezVousService.findCinMedByRdv(c.getId()));
        ConsultationService.ajouterConsultation(r.getMedecin().getCin(),r.getPatient().getCin(),r.getDate_RDV(),c.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/details/{id}")
    public ResponseEntity<patientDetailsDto> detailsP(@PathVariable(name = "id") int id) {
        System.out.println(id+"bonjouuuuuuuuuuuuuuur");
        RendezVous r=rendezVousRepository.findRendezVousById(id);
        patientDetailsDto dto=new  patientDetailsDto();

        dto.setCin(r.getPatient().getCin());
        dto.setNom(r.getPatient().getNom());
        dto.setPrenom(r.getPatient().getPrenom());
        System.out.println(dto.getCin());
        // c.setIdM(rendezVousService.findCinMedByRdv(c.getId()));
        return new ResponseEntity<>(dto, HttpStatus.OK);
}
    @GetMapping("/countWhenMed/{username}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_MEDECIN')")
    public ResponseEntity<Integer> countRdvOfMedByDay(@PathVariable(name = "username") String username){

         return ResponseEntity.ok(rendezVousRepository.countRdvOfMedByDay(username));
    }

    @GetMapping("/getImage/{cin}")
    public ResponseEntity<byte[]> getImage(@PathVariable String cin) {
        try {
            byte[] imageBytes = repPr.getBlobImage(cin);// Récupérez les données d'image à partir de la base de données en fonction de l'ID du patient (cin)
            if (imageBytes != null) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // Modifier selon le type d'image que vous récupérez
                return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        }
    @PostMapping("/uploadImage/{cin}")
    public void uploadImage(@PathVariable String cin, @RequestParam("file") MultipartFile file) throws IOException, IOException {
        // Vérifier si le fichier est vide
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Veuillez sélectionner un fichier");
        }

        // Lire les données du fichier et les convertir en byte[]
        byte[] imageData = file.getBytes();

        // Appeler votre méthode pour ajouter l'image avec le byte[] en tant que données de l'image
        personneService.ajouterImage(cin, imageData);
    }
    @PostMapping("/updateInfos/{cin}")
    public void updateInfos(@PathVariable String cin, @RequestBody PersonneDto per){
        Personne p=repPr.findByCin(cin);
        p.setEmail(per.getEmail());
        p.setAdresse(per.getAdr());
        p.setTel(per.getTel());

        repPr.save(p);
    }
    @PostMapping("/setLimitDuree/{cin}")
    public void setLimitDuree(@PathVariable String cin,@RequestBody medecinDto med){
         Medecin m=medecinRepository.findByCin(cin);
          m.setDurationRDV(med.getDuree());
          m.setLimitNumRDV(med.getLimit());
          medecinRepository.save(m);
    }
   @GetMapping("/getLimitDuree/{cin}")
   public ResponseEntity<medecinDto> getLimitDuree(@PathVariable String cin){
       Medecin m=medecinRepository.findByCin(cin);
       medecinDto med=dtoMapper.fromMedToDto(m);
       return ResponseEntity.ok(med);
   }
    }



