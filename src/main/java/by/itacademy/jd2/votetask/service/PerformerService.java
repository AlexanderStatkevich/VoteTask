package by.itacademy.jd2.votetask.service;

import by.itacademy.jd2.votetask.dao.api.IPerformersDao;
import by.itacademy.jd2.votetask.dto.PerformerDTO;
import by.itacademy.jd2.votetask.service.api.IPerformerService;

import java.util.List;
import java.util.stream.Collectors;

public class PerformerService implements IPerformerService {


    private final IPerformersDao<PerformerDTO> performersDao;

    public PerformerService(IPerformersDao<PerformerDTO> performersDao) {
        this.performersDao = performersDao;
    }

    @Override
    public List<String> getContent() {
        return performersDao.readAll().stream()
                .map(PerformerDTO::getNickName)
                .collect(Collectors.toList());
    }

    public boolean exist(String name) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Performer nickname can't be empty");
        }
        return performersDao.exist(name);
    }
}
