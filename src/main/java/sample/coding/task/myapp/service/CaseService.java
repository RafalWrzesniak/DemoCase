package sample.coding.task.myapp.service;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import sample.coding.task.myapp.model.elastic.Case;
import sample.coding.task.myapp.model.rmdb.Note;
import sample.coding.task.myapp.model.rmdb.Report;
import sample.coding.task.myapp.repository.CaseElastic;
import sample.coding.task.myapp.repository.CaseRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static sample.coding.task.myapp.model.rmdb.Report.Status;

@Slf4j
@Service
@RequiredArgsConstructor
public class CaseService {

    private final CaseRepository caseRepository;
    private final CaseElastic caseElastic;
    private final ElasticsearchOperations elasticsearchOperations;

    public List<Report> getAllCases() {
        return caseRepository.findAll();
    }

    public Report getCase(Integer caseId) {
        return caseRepository.findById(caseId).orElseThrow();
    }

    public List<Report> getCasesWithStatus(Status status) {
        return caseRepository.findAllReportsWithStatus(status);
    }

    public List<Report> getOpenCases(Integer userId) {
        return caseRepository.findAllReportsWithUserId(userId);
    }

    public List<Report> getOpenCases(Integer userId, Status status) {
        return caseRepository.findAllReportsWithUserIdAndStatus(userId, status);
    }


    public Report createCase(Report toCreate) {
        caseRepository.save(toCreate);
        Report created = caseRepository.findById(toCreate.getReportId()).orElseThrow();
        log.info("Creating new case {}", created);
        return created;
    }

    @Transactional
    public Note addNoteToCase(Integer reportId, String detail) {
        Report requestedReport = getCase(reportId);

        Note note = new Note();
        note.setReport(requestedReport);
        note.setDetails(detail);

        requestedReport.addNote(note);
        caseRepository.save(requestedReport);
        log.info("Adding note {} to case {}", note, requestedReport);
        return note;
    }

    // == elastic ==

    public List<Case> getAllDocuments() {
        return Lists.newArrayList(caseElastic.findAll());
    }

    public Case getDocument(String caseId) {
        return caseElastic.findById(caseId).orElseThrow();
    }

    public Case createDocument(Case caseToCreate) {
        caseElastic.save(caseToCreate);
        Case created = caseElastic.findById(caseToCreate.getCaseId()).orElseThrow();
        AtomicInteger i = new AtomicInteger();
        created.getNoteElas().forEach(note -> {
            note.setCaseId(created.getCaseId());
            note.setNoteId(i.getAndIncrement());
        });
        caseElastic.save(created);
        log.info("Creating new case {}", created);
        return created;
    }

    public void deleteAllDocuments() {
        log.warn("Removing all documents...");
        caseElastic.deleteAll();
    }

    public List<Case> getDocumentsWithStatus(Case.Status status) {
        Criteria criteria = new Criteria("status").matches(status);
        Query searchQuery = new CriteriaQuery(criteria);
        SearchHits<Case> cases = elasticsearchOperations.search(
                searchQuery,
                Case.class,
                IndexCoordinates.of("caseindex"));
        log.info("Searching for cases with status = {}. Found {} cases", status, cases.getTotalHits());
        return cases.get().map(SearchHit::getContent).collect(Collectors.toList());
    }

    public List<Case> getUserOpenCases(Integer userId) {
        Criteria criteria = new Criteria("user.userId").matches(userId);
        Query searchQuery = new CriteriaQuery(criteria);
        SearchHits<Case> cases = elasticsearchOperations.search(
                searchQuery,
                Case.class,
                IndexCoordinates.of("caseindex"));
        log.info("Searching for cases with user.userId = {}. Found {} cases", userId, cases.getTotalHits());
        return cases.get().map(SearchHit::getContent).collect(Collectors.toList());
    }


    public List<Case> getUserCasesWithStatus(Integer userId, Case.Status status) {
        Criteria criteria = new Criteria
                ("user.userId").matches(userId)
                .and("status").matches(status);
        Query searchQuery = new CriteriaQuery(criteria);
        SearchHits<Case> cases = elasticsearchOperations.search(
                searchQuery,
                Case.class,
                IndexCoordinates.of("caseindex"));
        log.info("Searching for cases with user.userId = {} and status {}. Found {} cases", userId, status, cases.getTotalHits());
        return cases.get().map(SearchHit::getContent).collect(Collectors.toList());
    }

}
