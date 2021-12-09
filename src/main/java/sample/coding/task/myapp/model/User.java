package sample.coding.task.myapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@ToString
@Getter @Setter
@Table(name = "USERS")
public class User {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = IDENTITY)
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;

}


