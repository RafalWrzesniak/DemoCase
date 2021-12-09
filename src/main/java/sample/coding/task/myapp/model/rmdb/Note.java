package sample.coding.task.myapp.model.rmdb;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@ToString
@Getter @Setter
public class Note {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = IDENTITY)
    private Integer noteId;
    @JsonBackReference
    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "report_fk")
    private Report report;
    private String details;

}
