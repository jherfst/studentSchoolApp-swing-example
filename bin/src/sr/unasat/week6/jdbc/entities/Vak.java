package sr.unasat.week6.jdbc.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by jherfst on 5/20/2017.
 */
public class Vak {
    private int id;
    private String naam;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

}
