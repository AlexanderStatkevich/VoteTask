package by.itacademy.jd2.votetask.dto;

import java.util.List;
import java.util.Objects;

public class VoteDto {
    private final Long voiceForPerformer;
    private final List<Long> voicesForGenres;
    private final String about;

    public VoteDto(Long voiceForPerformer, List<Long> voicesForGenres, String about) {
        this.voiceForPerformer = voiceForPerformer;
        this.voicesForGenres = voicesForGenres;
        this.about = about;
    }

    public Long getVoiceForPerformer() {
        return voiceForPerformer;
    }

    public List<Long> getVoicesForGenres() {
        return voicesForGenres;
    }

    public String getAbout() {
        return about;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteDto voteDto = (VoteDto) o;
        return Objects.equals(voiceForPerformer, voteDto.voiceForPerformer) && Objects.equals(voicesForGenres, voteDto.voicesForGenres) && Objects.equals(about, voteDto.about);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voiceForPerformer, voicesForGenres, about);
    }

    @Override
    public String toString() {
        return "VoteDto{" +
                "voiceForPerformer=" + voiceForPerformer +
                ", voicesForGenres=" + voicesForGenres +
                ", about='" + about + '\'' +
                '}';
    }
}
