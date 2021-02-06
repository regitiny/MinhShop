package org.regitiny.minhshop.web.rest.custom;

import javax.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.regitiny.minhshop.service.PostService;
import org.regitiny.minhshop.web.rest.custom.model.PostModel;
import org.regitiny.minhshop.web.rest.errors.EmailAlreadyUsedException;
import org.regitiny.minhshop.web.rest.errors.InvalidPasswordException;
import org.regitiny.minhshop.web.rest.errors.LoginAlreadyUsedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for Post( SimplePost and PostDetails ).
 */
@Log4j2
@RestController
@RequestMapping("/api")
public class PostResource {

    private final PostService postService;

    public PostResource(PostService postService) {
        this.postService = postService;
    }

    /**
     * {@code POST  /post} : create a new post.
     *
     * @throws InvalidPasswordException {@code 400 (Bad Request)} if the password is incorrect.
     * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
     * @throws LoginAlreadyUsedException {@code 400 (Bad Request)} if the login is already used.
     */
    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@Valid @RequestBody PostModel postModel) {
        log.info(postModel);
        postService.createNewPost(postModel);
    }
}
