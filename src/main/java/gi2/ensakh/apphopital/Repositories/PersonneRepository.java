package gi2.ensakh.apphopital.Repositories;



import gi2.ensakh.apphopital.Entities.Personne;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;




public interface PersonneRepository extends JpaRepository<Personne,String> {
    //@Query("select p from Personne p where p.compte.username =: id")
    //public Personne getPersonneByUserName(@Param("id")String id);

    @Query("select P from Personne P where P.compte.username=:id")
    public Personne getInf(@Param("id")String id);
    public Personne findByCin(String cin);
    @Query("select p.imageBlob from Personne p where p.cin= :cin")
    public byte[] getBlobImage(@Param("cin") String cin);


}