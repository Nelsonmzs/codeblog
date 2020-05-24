package com.spring.codeblog.service;

import com.spring.codeblog.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CodeblogService {

    Page<Post> findAll(Pageable pageable);

    Post findById(long id);

    Post save(Post post);
}
