package gi2.ensakh.apphopital.Repositories;

import gi2.ensakh.apphopital.Entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note,Integer> {
    Note findNotesByDateEventAndMedecin_Cin(LocalDate dateEvent, String cin );
    @Query("SELECT n FROM Note n WHERE n.medecin.cin = :cin AND n.dateEvent = :date ")
    List<Note> getAllNoteOfMedecinInDay(@Param("cin")String cin, @Param("date") LocalDate date);
    //Medecin findMedecinByCin(@Param("cin")String cin);
    Note findById(Long id);
}
