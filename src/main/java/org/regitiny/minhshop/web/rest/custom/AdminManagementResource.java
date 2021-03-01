package org.regitiny.minhshop.web.rest.custom;

import lombok.extern.log4j.Log4j2;
import org.regitiny.minhshop.domain.*;
import org.regitiny.minhshop.repository.*;
import org.regitiny.minhshop.repository.search.*;
import org.regitiny.minhshop.web.rest.errors.EmailAlreadyUsedException;
import org.regitiny.minhshop.web.rest.errors.InvalidPasswordException;
import org.regitiny.minhshop.web.rest.errors.LoginAlreadyUsedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api")
public class AdminManagementResource
{
  private final SimplePostRepository simplePostRepository;
  private final SimplePostSearchRepository simplePostSearchRepository;
  private final PostDetailsRepository postDetailsRepository;
  private final PostDetailsSearchRepository postDetailsSearchRepository;
  private final BillRepository billRepository;
  private final BillSearchRepository billSearchRepository;
  private final PaymentRepository paymentRepository;
  private final PaymentSearchRepository paymentSearchRepository;
  private final TypePostRepository typePostRepository;
  private final TypePostSearchRepository typePostSearchRepository;
  private final TypePostFilterRepository typePostFilterRepository;
  private final TypePostFilterSearchRepository typePostFilterSearchRepository;
  private final UserOtherInfoRepository userOtherInfoRepository;
  private final UserOtherInfoSearchRepository userOtherInfoSearchRepository;

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  public AdminManagementResource(
    SimplePostRepository simplePostRepository, SimplePostSearchRepository simplePostSearchRepository, PostDetailsRepository postDetailsRepository,
    PostDetailsSearchRepository postDetailsSearchRepository, BillRepository billRepository, BillSearchRepository billSearchRepository,
    PaymentRepository paymentRepository, PaymentSearchRepository paymentSearchRepository, TypePostRepository typePostRepository, TypePostSearchRepository typePostSearchRepository,
    TypePostFilterRepository typePostFilterRepository, TypePostFilterSearchRepository typePostFilterSearchRepository, UserOtherInfoRepository userOtherInfoRepository, UserOtherInfoSearchRepository userOtherInfoSearchRepository)
  {
    this.simplePostRepository = simplePostRepository;
    this.simplePostSearchRepository = simplePostSearchRepository;
    this.postDetailsRepository = postDetailsRepository;
    this.postDetailsSearchRepository = postDetailsSearchRepository;
    this.billRepository = billRepository;
    this.billSearchRepository = billSearchRepository;
    this.paymentRepository = paymentRepository;
    this.paymentSearchRepository = paymentSearchRepository;
    this.typePostRepository = typePostRepository;
    this.typePostSearchRepository = typePostSearchRepository;
    this.typePostFilterRepository = typePostFilterRepository;
    this.typePostFilterSearchRepository = typePostFilterSearchRepository;
    this.userOtherInfoRepository = userOtherInfoRepository;
    this.userOtherInfoSearchRepository = userOtherInfoSearchRepository;
  }

  /**
   * {@code POST  /posts} : create a new post.
   *
   * @throws InvalidPasswordException  {@code 400 (Bad Request)} if the password is incorrect.
   * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
   * @throws LoginAlreadyUsedException {@code 400 (Bad Request)} if the login is already used.
   */
  @PutMapping("/management/admin/database/elastichsearch/syncs")
  @ResponseStatus(HttpStatus.CREATED)
  @Transactional(readOnly = true)
  public ResponseEntity<String> reindexAllElasticsearch()
  {
    log.info("reindex all database syncs()");
    log.debug("start reindex.");

////  yuvytung: practical experience
//    simplePostSearchRepository.deleteAll();
//    simplePostRepository.findAll().stream()
//      .map(simplePostMapper::cleanNested)
//      .forEach(simplePostSearchRepository::save);
//    log.debug("reindexAll : SimplePost succeed");

    billSearchRepository.deleteAll();
    billRepository.findAll().stream()
      .peek(Bill::cleanInfiniteInterlockingRelationship)
      .forEach(billSearchRepository::save);
    log.debug("reindexAll : Bill succeed");

    paymentSearchRepository.deleteAll();
    paymentRepository.findAll().stream()
      .peek(Payment::cleanInfiniteInterlockingRelationship)
      .forEach(paymentSearchRepository::save);
    log.debug("reindexAll : Payment succeed");

    postDetailsSearchRepository.deleteAll();
    postDetailsRepository.findAll().stream()
      .peek(PostDetails::cleanInfiniteInterlockingRelationship)
      .forEach(postDetailsSearchRepository::save);
    log.debug("reindexAll : postDetail succeed");

    simplePostSearchRepository.deleteAll();
    simplePostRepository.findAll().stream()
      .peek(SimplePost::cleanInfiniteInterlockingRelationship)
      .forEach(simplePostSearchRepository::save);
    log.debug("reindexAll : SimplePost succeed");

    typePostSearchRepository.deleteAll();
    typePostRepository.findAll().
      forEach(typePostSearchRepository::save);
    log.debug("reindexAll : TypePost succeed");

    typePostFilterSearchRepository.deleteAll();
    typePostFilterRepository.findAll().stream()
      .peek(TypePostFilter::cleanInfiniteInterlockingRelationship)
      .forEach(typePostFilterSearchRepository::save);
    log.debug("reindexAll : TypePostFilter succeed");

    userOtherInfoSearchRepository.deleteAll();
    userOtherInfoRepository.findAll()
      .forEach(userOtherInfoSearchRepository::save);
    log.debug("reindexAll : UserOtherInfo succeed");


    return null;
  }
}

