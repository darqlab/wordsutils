package org.ssd;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class Result {


    private int id_;
    private String value_;

    private int word_count;

    public Result(int id_, String value_, int count_) {
        this.id_ = id_;
        this.value_ = value_;
        this.word_count = count_;
    }
}
