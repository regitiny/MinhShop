import './form-insert.scss';
import React, { useState } from 'react';
import { Button, Form, FormGroup, Input, Label } from 'reactstrap';

import axios from 'axios';

import FroalaEditor from 'react-froala-wysiwyg';

import { Storage } from 'react-jhipster';

import 'froala-editor/css/froala_style.min.css';
import 'froala-editor/css/froala_editor.pkgd.min.css';
import 'froala-editor/js/plugins.pkgd.min.js';

export const PostUpdate = props => {
  const [contentState, setContentState]: any = useState('');

  const [inputValue, setInputValue] = useState({
    title: '',
    price: '',
    sellPrice: '',
    percentPrice: '',
    imageUrl: '',
    scores: '',
    otherInfo: '',
    searchField: '',
    role: '',
    typePost: '',
    postDetailsId: '',
    typePostFilter: '',
    simpleContent: '',
    content: '',
    comment: '',
  });

  const handleModelChange = modal => {
    setContentState(modal);
  };
  window.console.log(contentState);

  const onHandleChange = event => {
    window.console.log(contentState);
    // setInputValue({
    //   ...inputValue,
    //   [event.tartget.name]:event.tartget.value,
    //   content: contentState,
    // })
    setInputValue({
      ...inputValue,
      [event.target.name]: event.target.value,
      content: contentState,
    });
  };
  window.console.log(inputValue);

  const onSubmit = event => {
    event.preventDefault();
    window.console.log(inputValue);
    axios({
      method: 'post',
      url: 'http://localhost/api/post',
      headers: {},
      data: inputValue,
    });
  };
  const token = Storage.local.get('jhi-authenticationToken') || Storage.session.get('jhi-authenticationToken');
  const authToken = `Bearer ${token}`;
  window.console.log(authToken);
  return (
    <div className="d-flex justify-content-center">
      <Form onSubmit={onSubmit} className="col-6">
        <FormGroup>
          <Label for="examplePassword">Title</Label>
          <Input type="text" name="title" value={inputValue.title} onChange={onHandleChange} />
        </FormGroup>
        <FormGroup>
          <Label for="examplePassword">Price</Label>
          <Input type="string" name="price" onChange={onHandleChange} />
        </FormGroup>
        <FormGroup>
          <Label for="examplePassword">Sell Price</Label>
          <Input type="string" name="sellPrice" onChange={onHandleChange} />
        </FormGroup>
        <FormGroup>
          <Label for="examplePassword">Percent Price</Label>
          <Input type="string" name="percentPrice" onChange={onHandleChange} />
        </FormGroup>
        <FormGroup>
          <Label for="examplePassword">Image Url</Label>
          <Input type="text" name="imageUrl" onChange={onHandleChange} />
        </FormGroup>
        <FormGroup>
          <Label for="examplePassword">Score</Label>
          <Input type="string" name="scores" onChange={onHandleChange} />
        </FormGroup>
        <FormGroup>
          <Label for="examplePassword">Simple Content</Label>
          <Input type="string" name="simpleContent" onChange={onHandleChange} />
        </FormGroup>
        <FormGroup>
          <Label for="examplePassword">Other Info</Label>
          <Input type="text" name="otherInfo" onChange={onHandleChange} />
        </FormGroup>
        <FormGroup>
          <Label for="examplePassword">Search Field</Label>
          <Input type="textarea" name="searchField" onChange={onHandleChange} />
        </FormGroup>
        <FormGroup>
          <Label for="examplePassword">Role</Label>
          <Input type="text" name="role" onChange={onHandleChange} />
        </FormGroup>
        <FormGroup>
          <Label for="examplePassword">Type Post</Label>
          <Input type="text" name="typePost" onChange={onHandleChange} />
        </FormGroup>
        <FormGroup>
          <Label for="examplePassword">Type Post Filter</Label>
          <Input type="text" name="typePostFilter" onChange={onHandleChange} />
        </FormGroup>
        <FormGroup>
          <Label for="examplePassword">Post Details Id</Label>
          <Input type="text" name="content" pattern="[A-Z]+[0-9]+" onChange={onHandleChange} />
        </FormGroup>
        <FormGroup>
          <Label for="examplePassword">Content</Label>
          <FroalaEditor
            model={contentState}
            onModelChange={handleModelChange}
            config={{
              imageUploadURL: '/api/images/upload',
              imageUploadParam: 'imageDataFile',
              requestHeaders: {
                Authorization: authToken,
              },
              imageAllowedTypes: ['jpeg', 'jpg', 'png', 'gif'],
              imageUploadMethod: 'POST',
              imageUpload: true,
            }}
          />
        </FormGroup>
        <FormGroup>
          <Label for="examplePassword">Comment</Label>
          <Input type="text" name="comment" onChange={onHandleChange} />
        </FormGroup>
        <Button type="submit">Submit</Button>
      </Form>
    </div>
  );
};
