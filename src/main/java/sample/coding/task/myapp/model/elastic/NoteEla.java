package sample.coding.task.myapp.model.elastic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class NoteEla {

    private Integer noteId;
    private String caseId;
    private String details;

}