package blps.lab1.model.requests;

import blps.lab1.model.domain.TopicCategory;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CreateTopicRequest implements Serializable {
    private String title;
    private String description;
    private String content;
    private String category;
}
