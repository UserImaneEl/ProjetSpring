package gi2.ensakh.apphopital.Dtos;

import java.time.LocalDateTime;
import java.util.Date;

public class DtoSecValiderRdv {
    LocalDateTime date;
    String cinP;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCinP() {
        return cinP;
    }

    public void setCinP(String cinP) {
        this.cinP = cinP;
    }
}
