package by.itacademy.jd2.votetask.util;


import by.itacademy.jd2.votetask.dto.SavedVoteDTO;

import java.util.Comparator;

public class SavedVoteComparatorByTime implements Comparator<SavedVoteDTO> {
        public int compare(SavedVoteDTO o1, SavedVoteDTO o2) {
            return o2.getCreateDateTime().compareTo(o1.getCreateDateTime());
        }
    }

