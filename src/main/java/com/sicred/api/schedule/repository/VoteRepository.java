package com.sicred.api.schedule.repository;

import com.sicred.api.schedule.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    //Vote findById(long id);
}
