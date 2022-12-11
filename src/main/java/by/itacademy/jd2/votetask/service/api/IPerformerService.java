package by.itacademy.jd2.votetask.service.api;

import java.util.List;

public interface IPerformerService {
    List<String> getContent();
    boolean exist(String name);
}
