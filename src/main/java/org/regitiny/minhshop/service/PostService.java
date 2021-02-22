package org.regitiny.minhshop.service;

import org.regitiny.minhshop.web.rest.custom.model.PostModel;

public interface PostService
{
  //  void createNewPost(PostModel postModel);
  //
  //  void partialUpdatePost(PostModel postModel, Long simplePost_Id, Long posDetail_Id);


  void save(Long simplifiedPostId, PostModel postModel);


  void deletePost(long simplePostId);
}
