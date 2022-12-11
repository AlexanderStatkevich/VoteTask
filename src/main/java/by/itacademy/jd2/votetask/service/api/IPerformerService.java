package by.itacademy.jd2.votetask.service.api;

import by.itacademy.jd2.votetask.dto.PerformerDTO;

import java.util.List;

public interface IPerformerService {
    List<PerformerDTO> getContent();
    boolean exist(Long id);
}
