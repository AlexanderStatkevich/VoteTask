package by.itacademy.jd2.votetask.dto;

import java.util.Objects;

public class PerformerDTO {

    private final Long id;
    private final String nickName;

    public PerformerDTO(Long id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }

    public Long getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerformerDTO that = (PerformerDTO) o;
        return Objects.equals(nickName, that.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickName);
    }

    @Override
    public String toString() {
        return "PerformerDTO{" +
                "nickName='" + nickName + '\'' +
                '}';
    }
}
