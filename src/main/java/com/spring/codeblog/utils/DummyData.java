package com.spring.codeblog.utils;

import com.spring.codeblog.model.Post;
import com.spring.codeblog.repository.CodeBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DummyData {

    @Autowired
    CodeBlogRepository codeBlogRepository;

//    @PostConstruct
    public void savePosts() {

        List<Post> postList = new ArrayList<>();

        Post post1 = new Post();
        post1.setAutor("Stephen King");
        post1.setTitulo("It - Acoisa");
        post1.setTexto("It é um romance de terror de 1986 do autor estadunidense Stephen King. Foi seu 22º livro e seu 17º romance escrito em seu próprio nome. A história segue as experiências de sete crianças, que são aterrorizadas por uma entidade maligna que explora os medos de suas vítimas para se disfarçar enquanto caçam suas presas.");
        post1.setData(LocalDate.now());

        Post post2 = new Post();
        post2.setAutor("Stephen King");
        post2.setTitulo("Carrie");
        post2.setTexto("Carrie é um romance epistolar de terror do autor estadunidense Stephen King. Foi seu primeiro romance publicado, lançado em 5 de abril de 1974, com uma primeira tiragem aproximada de 30.000 cópias.");
        post2.setData(LocalDate.now());

        postList.add(post1);
        postList.add(post2);

        if (!CollectionUtils.isEmpty(postList)) {
            for (Post post : postList) {
                Post postSaved = codeBlogRepository.save(post);
                System.out.println(postSaved.getId());
            }
        }

    }

}
