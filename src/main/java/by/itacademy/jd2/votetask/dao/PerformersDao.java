package by.itacademy.jd2.votetask.dao;

import by.itacademy.jd2.votetask.dao.api.IPerformersDao;
import by.itacademy.jd2.votetask.dto.PerformerDTO;

import java.util.List;

public class PerformersDao implements IPerformersDao<PerformerDTO> {
    private static List<PerformerDTO> PERFORMERS = List.of(
            new PerformerDTO(1L,"Performer 1"),
            new PerformerDTO(2L,"Performer 2"),
            new PerformerDTO(3L,"Performer 3"),
            new PerformerDTO(4L,"Performer 4"));


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
    public boolean exist(Long id) {
        for (PerformerDTO performerDTO : PERFORMERS) {
            if(id.equals(performerDTO.getId())){
                return true;
            }
        }
        return false;
    }
}
