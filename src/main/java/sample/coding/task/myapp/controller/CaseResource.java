package sample.coding.task.myapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sample.coding.task.myapp.model.rmdb.Note;
import sample.coding.task.myapp.model.rmdb.Report;
import sample.coding.task.myapp.service.CaseService;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static sample.coding.task.myapp.model.rmdb.Report.Status;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CaseResource {

    private final CaseService caseService;

    @GetMapping("/reports")
    ResponseEntity<List<Report>> getAllCases() {
        return new ResponseEntity<>(caseService.getAllCases(), OK);
    }

    @GetMapping("/reports/status/{status}")
    ResponseEntity<List<Report>> getCasesWithStatus(@PathVariable Status status) {
        return new ResponseEntity<>(caseService.getCasesWithStatus(status), OK);
    }

    @GetMapping("/reports/user/{userId}")
    ResponseEntity<List<Report>> getOpenCases(@PathVariable Integer userId) {
        return new ResponseEntity<>(caseService.getOpenCases(userId), OK);
    }

    @GetMapping("/reports/user/{userId}/status/{status}")
    ResponseEntity<List<Report>> getOpenCases(@PathVariable Integer userId, @PathVariable Status status) {
        return new ResponseEntity<>(caseService.getOpenCases(userId, status), OK);
    }

    @GetMapping("/report/{reportId}")
    ResponseEntity<Report> getCase(@PathVariable Integer reportId) {
        return new ResponseEntity<>(caseService.getCase(reportId), OK);
    }

    @PostMapping(value = "/report/create", consumes = "application/json")
    ResponseEntity<Report> createCase(@RequestBody Report toCreate) {
        return new ResponseEntity<>(caseService.createCase(toCreate), OK);
    }

    @PostMapping(value = "/report/{reportId}/addNote", consumes = "text/plain")
    ResponseEntity<Note> addNote(@PathVariable Integer reportId, @RequestBody String detail) {
        return new ResponseEntity<>(caseService.addNoteToCase(reportId, detail), OK);
    }



}
