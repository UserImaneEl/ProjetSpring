package gi2.ensakh.apphopital.Services;

import org.springframework.stereotype.Component;
import gi2.ensakh.apphopital.Dtos.noteMedecinDto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Component

public interface NoteInf {
    List<noteMedecinDto> getAllNotes();

    List<noteMedecinDto> getAllNoteOfMedecinInDay(String id, LocalDate date);
    void saveNote(noteMedecinDto note);
   // Medecin findMedecinByCin(String cin);
    void deleteNote(Long id);



    void updateNote(Long id, String title);


}
