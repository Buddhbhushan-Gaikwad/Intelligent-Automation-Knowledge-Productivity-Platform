package com.example.Intelligent.Automation.Knowledge.Productivity.Platform.repository;

import com.example.Intelligent.Automation.Knowledge.Productivity.Platform.entity.JournalEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {

    Page<JournalEntry> findByUserId(Long userId, Pageable pageable);

    @Query("""
            SELECT j
            FROM JournalEntry j
            WHERE j.user.id = :userId
            AND (
                    LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
                 OR LOWER(j.content) LIKE LOWER(CONCAT('%', :keyword, '%'))
            )
            """)
    List<JournalEntry> searchByUserAndKeyword(Long userId, String keyword);
}
