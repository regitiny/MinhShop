package org.regitiny.minhshop.service;

import org.regitiny.minhshop.web.rest.custom.model.PostModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PostService
{
  //  void createNewPost(PostModel postModel);
  //
  //  void partialUpdatePost(PostModel postModel, Long simplePost_Id, Long posDetail_Id);


  Long save(PostModel postModel);


  void deletePost(long simplePostId);


  Optional<PostModel> getPostBySimplePost_Id(long simplePostId);


  Page<PostModel> getAllPost(Pageable pageable);
}
