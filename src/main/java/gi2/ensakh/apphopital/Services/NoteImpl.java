package gi2.ensakh.apphopital.Services;

import gi2.ensakh.apphopital.Entities.Medecin;
import gi2.ensakh.apphopital.Entities.Note;
import gi2.ensakh.apphopital.Repositories.MedecinRepository;
import gi2.ensakh.apphopital.Repositories.NoteRepository;
import gi2.ensakh.apphopital.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import gi2.ensakh.apphopital.Dtos.noteMedecinDto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class NoteImpl implements NoteInf {
    @Autowired
    NoteRepository rep;
    @Autowired
    MedecinRepository repMed;
    @Autowired
    PatientRepository repPat;


    @Override
    public List<noteMedecinDto> getAllNotes() {
        return rep.findAll()
                .stream()
                .map(this::convertToNoteMedecinDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<noteMedecinDto> getAllNoteOfMedecinInDay(String id, LocalDate date) {
        return rep.getAllNoteOfMedecinInDay(id,date)
                .stream()
                .map(this::convertToNoteMedecinDto)
                .collect(Collectors.toList());
    }

    @Override
    public void saveNote(noteMedecinDto dtoNote) {
        Medecin med=repMed.findByCin(dtoNote.getCin());
        Note note= new Note();
        note.setId(dtoNote.getId());
        note.setTitle(dtoNote.getTitle());
        note.setDateEvent(dtoNote.getDateEvent());

        note.setMedecin(med);
        rep.save(note);
    }

    @Override
    public void deleteNote(Long id) {
        Note note=new Note();
        note.setId(id);
        rep.delete(note);
    }

    @Override
    public void updateNote(Long id,String title) {

        Note note1=rep.findById(id);
        note1.setTitle(title);
        rep.save(note1);

    }


    /* @Override
     public Medecin findMedecinByCin(String cin) {
         return rep.findMedecinByCin(cin);
     }*/
    private noteMedecinDto convertToNoteMedecinDto(Note note){
        noteMedecinDto dto=new noteMedecinDto();
        dto.setId(note.getId());
        dto.setDateEvent(note.getDateEvent());
        dto.setTitle(note.getTitle());

        Medecin medecin = note.getMedecin();
        if (medecin != null) {
            dto.setCin(medecin.getCin());
        }
        return dto;
    }

}
