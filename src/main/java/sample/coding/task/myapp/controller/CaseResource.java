package sample.coding.task.myapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sample.coding.task.myapp.model.rmdb.Note;
import sample.coding.task.myapp.model.rmdb.Report;
import sample.coding.task.myapp.service.CaseService;

import java.util.List;

import static sample.coding.task.myapp.model.rmdb.Report.Status;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CaseResource {

    private final CaseService caseService;

    @GetMapping("/reports")
    List<Report> getAllCases() {
        return caseService.getAllCases();
    }

    @GetMapping("/reports/status/{status}")
    List<Report> getCasesWithStatus(@PathVariable Status status) {
        return caseService.getCasesWithStatus(status);
    }

    @GetMapping("/reports/user/{userId}")
    List<Report> getOpenCases(@PathVariable Integer userId) {
        return caseService.getOpenCases(userId);
    }

    @GetMapping("/reports/user/{userId}/status/{status}")
    List<Report> getOpenCases(@PathVariable Integer userId, @PathVariable Status status) {
        return caseService.getOpenCases(userId, status);
    }

    @GetMapping("/report/{reportId}")
    Report getCase(@PathVariable Integer reportId) {
        return caseService.getCase(reportId);
    }

    @PostMapping(value = "/report/create", consumes = "application/json")
    Report createCase(@RequestBody Report toCreate) {
        return caseService.createCase(toCreate);
    }

    @PostMapping(value = "/report/{reportId}/addNote", consumes = "text/plain")
    Note addNote(@PathVariable Integer reportId, @RequestBody String detail) {
        return caseService.addNoteToCase(reportId, detail);
    }



}
