package com.gl.mongodb.controllers;

import com.gl.mongodb.dto.CommentDto;
import com.gl.mongodb.exception.AllAlreadyExisteException;
import com.gl.mongodb.exception.AllDoesntExisteException;
import com.gl.mongodb.model.Comment;
import com.gl.mongodb.model.News;
import com.gl.mongodb.repositoriy.CommentRepository;
import com.gl.mongodb.repositoriy.NewsRepository;
import com.gl.mongodb.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {
    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository ;

    @PostMapping
    public News create(@RequestBody CommentDto commentDto) throws AllDoesntExisteException, AllAlreadyExisteException {
        Comment comment = new Comment(commentDto.getComment(), commentDto.getAuteur());
        News myNew = newsRepository.findById(commentDto.getIdNews()).orElseThrow(()->new AllDoesntExisteException("n'exist pas"));
        Comment myNewComment = commentRepository.save(comment);
        myNew.getComments().add(myNewComment);
        return newsRepository.save(myNew);
    }
    @GetMapping("/{id}")
    public Optional<Comment> findOne(@PathVariable String id){
        return commentRepository.findById(id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        commentRepository.deleteById(id);
    }
}
