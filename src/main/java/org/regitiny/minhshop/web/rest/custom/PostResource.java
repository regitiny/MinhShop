package org.regitiny.minhshop.web.rest.custom;

import lombok.extern.log4j.Log4j2;
import org.regitiny.minhshop.service.PostService;
import org.regitiny.minhshop.web.rest.custom.model.PostModel;
import org.regitiny.minhshop.web.rest.errors.EmailAlreadyUsedException;
import org.regitiny.minhshop.web.rest.errors.InvalidPasswordException;
import org.regitiny.minhshop.web.rest.errors.LoginAlreadyUsedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;

import javax.validation.Valid;

/**
 * REST controller for Post( SimplePost and PostDetails ).
 */
@Log4j2
@RestController
@RequestMapping("/api")
public class PostResource
{

  private final PostService postService;

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  public PostResource(PostService postService)
  {
    this.postService = postService;
  }

  /**
   * {@code POST  /posts} : create a new post.
   *
   * @throws InvalidPasswordException  {@code 400 (Bad Request)} if the password is incorrect.
   * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
   * @throws LoginAlreadyUsedException {@code 400 (Bad Request)} if the login is already used.
   */
  @PostMapping("/posts")
  @ResponseStatus(HttpStatus.CREATED)
  public void createPost(@Valid @RequestBody PostModel postModel)
  {
    log.info(postModel);
    postService.save(null, postModel);
  }

  /**
   * {@code PATCH  /posts/{id}} : update a post by simplePost_Id.
   *
   * @throws InvalidPasswordException  {@code 400 (Bad Request)} if the password is incorrect.
   * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
   * @throws LoginAlreadyUsedException {@code 400 (Bad Request)} if the login is already used.
   */
  @PatchMapping("/posts/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public void updatePost(@PathVariable Long id, @Valid @RequestBody PostModel postModel)
  {
    log.info(postModel);
    postService.save(id, postModel);
  }

  /**
   * {@code POST  /posts/{id}} : delete a post by simplePost_Id.
   *
   * @throws InvalidPasswordException  {@code 400 (Bad Request)} if the password is incorrect.
   * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
   * @throws LoginAlreadyUsedException {@code 400 (Bad Request)} if the login is already used.
   */
  @DeleteMapping("/posts/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Void> registerAccount(@PathVariable Long id)
  {
    log.info("simplePost Id = {}", id);
    postService.deletePost(id);
    return ResponseEntity.noContent().headers(HeaderUtil.createAlert(applicationName, "deleted succeed", id.toString())).build();
  }
  //    /**
  //     * {@code POST  /post/{id}} : deleta a post by simplePost_Id.
  //     *
  //     * @throws InvalidPasswordException {@code 400 (Bad Request)} if the password is incorrect.
  //     * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
  //     * @throws LoginAlreadyUsedException {@code 400 (Bad Request)} if the login is already used.
  //     */
  //    @GetMapping("/posts")
  //    @ResponseStatus(HttpStatus.CREATED)
  //    public ResponseEntity<> getAll() {
  //        log.info("get all ");
  //        postService.deletePost(id);
  //    }
}
