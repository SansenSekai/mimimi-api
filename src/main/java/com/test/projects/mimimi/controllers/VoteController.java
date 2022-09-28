package com.test.projects.mimimi.controllers;

import com.test.projects.mimimi.exceptions.ObjectNotFoundException;
import com.test.projects.mimimi.services.vote.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/vote")
public class VoteController {
    private final VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pairs")
    public ResponseEntity<? extends Object> getVotePairs(@RequestParam(required = true) String subjectCategory){
        try {
           return ResponseEntity.ok(voteService.getVoteSubjects(subjectCategory));
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
