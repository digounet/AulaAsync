package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Messages {
    String chuckMessage;
    String advice;

    @Override
    public String toString() {
        return String.format("Chuck: %s\nAdvice: %s", chuckMessage, advice);
    }
}
