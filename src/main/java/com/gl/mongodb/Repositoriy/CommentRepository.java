package com.gl.mongodb.Repositoriy;

import com.gl.mongodb.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, Long> {
}