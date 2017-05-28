package sr.unasat.week6.jdbc.entities;

/**
 * Created by jherfst on 5/20/2017.
 */
public class StudentVak {
    private int id;
    private int student;
    private int vak;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent() {
        return student;
    }

    public void setStudent(int student) {
        this.student = student;
    }

    public int getVak() {
        return vak;
    }

    public void setVak(int vak) {
        this.vak = vak;
    }
}
