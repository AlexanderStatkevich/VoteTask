package by.itacademy.jd2.votetask.dto;

import java.util.List;
import java.util.Objects;

public class VoteDto {
    private final String voiceForPerformer;
    private final List<String> voicesForGenres;
    private final String about;

    public VoteDto(String voiceForPerformer, List<String> voicesForGenres, String about) {
        this.voiceForPerformer = voiceForPerformer;
        this.voicesForGenres = voicesForGenres;
        this.about = about;
    }

    public String getVoiceForPerformer() {
        return voiceForPerformer;
    }

    public List<String> getVoicesForGenres() {
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
                "voiceForPerformer='" + voiceForPerformer + '\'' +
                ", voicesForGenres=" + voicesForGenres +
                ", about='" + about + '\'' +
                '}';
    }
}
