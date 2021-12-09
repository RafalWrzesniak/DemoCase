package sample.coding.task.myapp.model.elastic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import sample.coding.task.myapp.model.User;

import java.util.List;

@Document(indexName = "caseindex")
@Getter @Setter
@ToString
public class Case {

    @Id
    @JsonIgnore
    private String caseId;
    private String title;
    private String description;
    private Integer severity;
    private Status status;
    private User user;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<NoteEla> noteElas;

    public enum Status {
        OPEN, CLOSED
    }

    @JsonIgnore
    public Integer getIndexForNewNote() {
        return noteElas.size();
    }
}
