package by.itacademy.jd2.votetask.dao;

import by.itacademy.jd2.votetask.dao.api.IVoteDao;
import by.itacademy.jd2.votetask.dto.SavedVoteDTO;

import java.util.ArrayList;
import java.util.List;

public class VoteDao implements IVoteDao<SavedVoteDTO> {

    private final List<SavedVoteDTO> savedVoteDTOS = new ArrayList<>();

    @Override
    public void create(SavedVoteDTO savedVoteDTO) {
        savedVoteDTOS.add(savedVoteDTO);
    }
    @Override
    public List<SavedVoteDTO> readAll() {
        return savedVoteDTOS;
    }

    @Override
    public void delete(SavedVoteDTO savedVoteDTO) {
        savedVoteDTOS.remove(savedVoteDTO);
    }


}
