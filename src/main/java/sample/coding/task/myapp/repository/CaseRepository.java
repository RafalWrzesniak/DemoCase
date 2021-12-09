package sample.coding.task.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sample.coding.task.myapp.model.rmdb.Report;

import java.util.List;

import static sample.coding.task.myapp.model.rmdb.Report.Status;

@Repository
public interface CaseRepository extends JpaRepository<Report, Integer> {

    @Query("Select r From Report r WHERE r.status = ?1")
    List<Report> findAllReportsWithStatus(Status status);

    @Query("Select r From Report r WHERE r.user.userId = ?1")
    List<Report> findAllReportsWithUserId(Integer userId);

    @Query("Select r From Report r WHERE r.user.userId = ?1 AND r.status = ?2")
    List<Report> findAllReportsWithUserIdAndStatus(Integer userId, Status status);
}
