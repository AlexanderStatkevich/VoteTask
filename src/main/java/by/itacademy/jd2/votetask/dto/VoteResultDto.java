package by.itacademy.jd2.votetask.dto;

import java.util.List;
import java.util.Map;

public class VoteResultDto {
    private final Map<String, Long> sortedPerformerVotes;
    private final Map<String, Long> sortedGenreVotes;
    private final List<SavedVoteDTO> sortedVoteInfos;

    public VoteResultDto(Map<String, Long> sortedPerformerVotes, Map<String, Long> sortedGenreVotes, List<SavedVoteDTO> sortedVoteInfos) {
        this.sortedPerformerVotes = sortedPerformerVotes;
        this.sortedGenreVotes = sortedGenreVotes;
        this.sortedVoteInfos = sortedVoteInfos;
    }

    public Map<String, Long> getSortedPerformerVotes() {
        return sortedPerformerVotes;
    }

    public Map<String, Long> getSortedGenreVotes() {
        return sortedGenreVotes;
    }

    public List<SavedVoteDTO> getSortedVoteInfos() {
        return sortedVoteInfos;
    }
}
