package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    private int id;
    private String topic;
    private String text;

    public Question(String topic, String text) {
        this.text = text;
        this.topic = topic;
    }

}
