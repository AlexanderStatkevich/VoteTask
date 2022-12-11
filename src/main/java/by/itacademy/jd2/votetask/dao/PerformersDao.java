package by.itacademy.jd2.votetask.dao;

import by.itacademy.jd2.votetask.dao.api.IPerformersDao;
import by.itacademy.jd2.votetask.dto.PerformerDTO;

import java.util.List;

public class PerformersDao implements IPerformersDao<PerformerDTO> {
    private static List<PerformerDTO> PERFORMERS = List.of(
            new PerformerDTO("PerformerDTO 1"),
            new PerformerDTO("PerformerDTO 2"),
            new PerformerDTO("PerformerDTO 3"),
            new PerformerDTO("PerformerDTO 4"));


    @Override
    public void create(PerformerDTO performerDTO) {
        PERFORMERS.add(performerDTO);
    }

    @Override
    public List<PerformerDTO> readAll() {
        return PERFORMERS;
    }

    @Override
    public void delete(PerformerDTO performerDTO) {
       PERFORMERS.remove(performerDTO);
    }

    @Override
    public boolean exist(String nickName) {
        for (PerformerDTO performerDTO : PERFORMERS) {
            if(nickName.equals(performerDTO.getNickName())){
                return true;
            }
        }
        return false;
    }
}
