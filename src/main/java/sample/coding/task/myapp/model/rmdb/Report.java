package sample.coding.task.myapp.model.rmdb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sample.coding.task.myapp.model.User;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@ToString
@Getter @Setter
public class Report {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = IDENTITY)
    private Integer reportId;
    private String title;
    private String description;
    private Integer severity;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "user_fk")
    private User user;
    @JsonManagedReference
    @OneToMany(cascade = ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "report_fk")
    @ToString.Exclude
    private List<Note> notes;

    public enum Status {
        OPEN, CLOSED
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public void addNote(List<Note> notes) {
        notes.forEach(this::addNote);
    }

    public void clearNotes() {
        if(notes != null) notes.clear();
    }
}
