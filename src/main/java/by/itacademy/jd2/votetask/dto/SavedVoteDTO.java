package by.itacademy.jd2.votetask.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class SavedVoteDTO {

    private LocalDateTime createDateTime;
    private final VoteDto vote;


    public SavedVoteDTO(VoteDto vote) {
        this.createDateTime = LocalDateTime.now();
        this.vote = vote;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public VoteDto getVote() {
        return vote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SavedVoteDTO that = (SavedVoteDTO) o;
        return Objects.equals(createDateTime, that.createDateTime) && Objects.equals(vote, that.vote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createDateTime, vote);
    }

    @Override
    public String toString() {
        return "SavedVoteDTO{" +
                "createDateTime=" + createDateTime +
                ", vote=" + vote +
                '}';
    }
}
