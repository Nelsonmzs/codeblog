package com.spring.codeblog.controller;

import com.spring.codeblog.model.Post;
import com.spring.codeblog.service.CodeblogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class CodeblogController {

    @Autowired
    CodeblogService codeblogService;

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ModelAndView getPosts(Pageable pageable) {

        ModelAndView modelAndView = new ModelAndView("posts");

        Page<Post> posts = codeblogService.findAll(pageable);

        modelAndView.addObject("posts", posts);

        return modelAndView;
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public ModelAndView getPostDetails(@PathVariable("id") long id) {

        ModelAndView modelAndView = new ModelAndView("postDetails");
        Post post = codeblogService.findById(id);

        modelAndView.addObject("post", post);

        return modelAndView;
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.GET)
    public String getPostForm() {
        return "postForm";
    }

    @RequestMapping(value = "/newpost   ", method = RequestMethod.POST)
    public String savePost(@Valid Post post, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos corretamente!");
            return "redirect:/newpost";
        }

            post.setData(LocalDate.now());
            codeblogService.save(post);
//          redirectAttributes.addFlashAttribute("sucessoMsg", "Post criado com sucesso!");

            return "redirect:/posts";
    }

}