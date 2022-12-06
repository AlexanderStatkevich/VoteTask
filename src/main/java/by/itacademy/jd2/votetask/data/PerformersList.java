package by.itacademy.jd2.votetask.data;

import by.itacademy.jd2.votetask.domain.Performer;

import java.util.List;

public class PerformersList {
    private static final List<Performer> PERFORMERS = List.of(new Performer("Performer 1"),
            new Performer("Performer 2"), new Performer("Performer 3"), new Performer("Performer 4"));

    public static List<Performer> getPERFORMERS() {
        return PERFORMERS;
    }
}
