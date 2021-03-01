package org.regitiny.minhshop.web.rest.custom;

import lombok.extern.log4j.Log4j2;
import org.regitiny.minhshop.service.PostService;
import org.regitiny.minhshop.web.rest.custom.model.PostModel;
import org.regitiny.minhshop.web.rest.errors.BadRequestAlertException;
import org.regitiny.minhshop.web.rest.errors.EmailAlreadyUsedException;
import org.regitiny.minhshop.web.rest.errors.InvalidPasswordException;
import org.regitiny.minhshop.web.rest.errors.LoginAlreadyUsedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

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
    postService.save(postModel);
  }

  /**
   * {@code PATCH  /posts/{id}} : update a post by simplePost_Id.
   *
   * @throws InvalidPasswordException  {@code 400 (Bad Request)} if the password is incorrect.
   * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
   * @throws LoginAlreadyUsedException {@code 400 (Bad Request)} if the login is already used.
   */
  @PatchMapping("/posts")
  @ResponseStatus(HttpStatus.CREATED)
  public void updatePost(@Valid @RequestBody PostModel postModel)
  {
    log.info(postModel);
    postService.save(postModel);
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


  /**
   * {@code PATCH  /posts/{id}} : update a post by simplePost_Id.
   *
   * @throws InvalidPasswordException  {@code 400 (Bad Request)} if the password is incorrect.
   * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
   * @throws LoginAlreadyUsedException {@code 400 (Bad Request)} if the login is already used.
   */
  @GetMapping("/posts/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<PostModel> getPostBySimplePostId(@PathVariable Long id)
  {
    log.debug("id of simplePost is : {}", id);
    Optional<PostModel> optionalPostModel = postService.getPostBySimplePost_Id(id);
    log.debug("result PostModel.isPresent = {} ", optionalPostModel.isPresent());
    return optionalPostModel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  /**
   * {@code GET  /posts} : get all the Posts.
   *
   * @param pageable the pagination information.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of simplePosts in body.
   */
  @GetMapping("/posts")
  public ResponseEntity<List<PostModel>> getAllPosts(Pageable pageable)
  {
    log.debug("REST request to get a page of SimplePosts");
    Page<PostModel> page = postService.getAllPost(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }


  /**
   * {@code PUT  /posts} : Updates an existing simplePost.
   *
   * @param postModel the PostDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated simplePostDTO,
   * or with status {@code 400 (Bad Request)} if the simplePostDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the simplePostDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/posts")
  public ResponseEntity<PostModel> updateSimplePost(@Valid @RequestBody PostModel postModel) throws URISyntaxException
  {
    log.debug("REST request to update SimplePost : {}", postModel);
    if (postModel.getId() == null)
    {
      throw new BadRequestAlertException("Invalid id", null, "idnull");
    }
    Long result = postService.save(postModel);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, null, result.toString()))
      .body(PostModel.builder().id(result).build());
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
