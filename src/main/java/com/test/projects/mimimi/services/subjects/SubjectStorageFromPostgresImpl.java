package com.test.projects.mimimi.services.subjects;

import com.test.projects.mimimi.domains.VoteSubject;
import com.test.projects.mimimi.dtos.VotePairDTO;
import com.test.projects.mimimi.exceptions.ObjectNotFoundException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

/**
 *  Сервис для кеширования и выдачи списка пар для голосования с использованием базы данных Postgres
 */

@Service("subjectStorageFromPostgres")
@EnableScheduling
public class SubjectStorageFromPostgresImpl implements SubjectStorage {
    Map<UUID, ArrayList<VotePairDTO>> votePairs = new HashMap<>();

    @Override
    public ArrayList<VotePairDTO> getVotePairs(String subjectCategories) throws ObjectNotFoundException {
        ArrayList<VotePairDTO> votePairsDTOs = votePairs.get(UUID.fromString(subjectCategories));
        if(votePairsDTOs == null) {
            throw new ObjectNotFoundException("Subjects of this category not founded");
        }
        return votePairsDTOs;
    }
}
