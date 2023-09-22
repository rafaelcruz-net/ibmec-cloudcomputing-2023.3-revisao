package br.edu.ibmec.cloudcomputing.revisao.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ibmec.cloudcomputing.revisao.model.Comment;
import br.edu.ibmec.cloudcomputing.revisao.model.Post;
import br.edu.ibmec.cloudcomputing.revisao.service.CommentService;
import br.edu.ibmec.cloudcomputing.revisao.service.PostService;

@RestController
@RequestMapping("/post/{idPost}/comment")
class resourceNameController {

    @Autowired
    CommentService commentService;

    @Autowired PostService postService;

    @GetMapping
    public ResponseEntity<List<Comment>> getAll(@PathVariable("idPost") long idPost) {
        try {
            Optional<Post> opPost = this.postService.getById(idPost);

            if (opPost.isPresent() == false) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);    
            }

            return new ResponseEntity<>(opPost.get().getComments(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Comment> getById(@PathVariable("id") long id) {
        Optional<Comment> existingItemOptional = commentService.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Comment> create(@PathVariable("idPost") long idPost, @RequestBody Comment item) {
        try {
            Comment savedItem = commentService.save(idPost, item);
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Comment> update(@PathVariable("id") long id, @RequestBody Comment item) {
        try {
            return new ResponseEntity<>(commentService.update(id, item), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        try {
            commentService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}