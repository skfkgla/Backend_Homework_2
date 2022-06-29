package com.BackendHomework2.repository;

import com.BackendHomework2.entity.PointEventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointEventLogsRepository extends JpaRepository<PointEventLog, Long> {
}
