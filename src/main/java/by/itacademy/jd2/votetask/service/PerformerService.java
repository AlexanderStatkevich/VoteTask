package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.dao.api.IPerformersDao;
import by.itacademy.jd2.votetask.dto.PerformerDTO;
import by.itacademy.jd2.votetask.service.api.IPerformerService;

import java.util.List;

public class PerformerService implements IPerformerService {


    private final IPerformersDao<PerformerDTO> performersDao;

    public PerformerService(IPerformersDao<PerformerDTO> performersDao) {
        this.performersDao = performersDao;
    }

    @Override
    public List<PerformerDTO> getContent() {
        return performersDao.readAll();
    }

    @Override
    public void create(PerformerDTO performerDTO) {
        performersDao.create(performerDTO);
    }

    @Override
    public void update(PerformerDTO performerDTO){
        performersDao.update();
    }


    @Override
    public void delete(PerformerDTO performerDTO) {
        performersDao.delete(performerDTO);
    }

    public boolean exist(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Performer nickname can't be empty");
        }
        return performersDao.exist(id);
    }
}
