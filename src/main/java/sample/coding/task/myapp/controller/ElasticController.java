package sample.coding.task.myapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sample.coding.task.myapp.model.elastic.Case;
import sample.coding.task.myapp.service.CaseService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ElasticController {

    private final CaseService caseService;

    @GetMapping("/cases")
    List<Case> getAllDocuments() {
        return caseService.getAllDocuments();
    }

    @GetMapping("/case/{caseId}")
    Case getDocument(@PathVariable String caseId) {
        return caseService.getDocument(caseId);
    }

    @PostMapping(value = "/case/create", consumes = "application/json")
    Case createDocument(@RequestBody Case caseToCreate) {
        return caseService.createDocument(caseToCreate);
    }

    @DeleteMapping("/cases/clear")
    void deleteAllDocs() {
        caseService.deleteAllDocuments();
    }

    @GetMapping("/cases/status/{status}")
    List<Case> getDocsWithStatus(@PathVariable Case.Status status) {
        return caseService.getDocumentsWithStatus(status);
    }

    @GetMapping("/cases/user/{userId}")
    List<Case> getUserOpenCases(@PathVariable Integer userId) {
        return caseService.getUserOpenCases(userId);
    }

    @GetMapping("/cases/user/{userId}/status/{status}")
    List<Case> getOpenCases(@PathVariable Integer userId, @PathVariable Case.Status status) {
        return caseService.getUserCasesWithStatus(userId, status);
    }

}
