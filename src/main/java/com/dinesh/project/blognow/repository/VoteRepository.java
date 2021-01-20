package com.dinesh.project.blognow.repository;

import com.dinesh.project.blognow.model.Post;
import com.dinesh.project.blognow.model.User;
import com.dinesh.project.blognow.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
