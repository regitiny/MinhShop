import {by, element, ElementFinder, protractor} from 'protractor';
import {isVisible, waitUntilDisplayed, waitUntilHidden} from '../../util/utils';

import path from 'path';

const expect = chai.expect;

const fileToUpload = '../../../../../../src/main/webapp/content/images/logo-jhipster.png';
const absolutePath = path.resolve(__dirname, fileToUpload);
export default class ImageUpdatePage
{
  pageTitle: ElementFinder = element(by.id('minhShopApp.image.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#image-uuid'));
  imageDataInput: ElementFinder = element(by.css('input#file_imageData'));
  nameImageInput: ElementFinder = element(by.css('input#image-nameImage'));
  extensionInput: ElementFinder = element(by.css('input#image-extension'));
  typeFileInput: ElementFinder = element(by.css('input#image-typeFile'));
  searchFieldInput: ElementFinder = element(by.css('textarea#image-searchField'));
  roleInput: ElementFinder = element(by.css('input#image-role'));
  createdDateInput: ElementFinder = element(by.css('input#image-createdDate'));
  modifiedDateInput: ElementFinder = element(by.css('input#image-modifiedDate'));
  createdByInput: ElementFinder = element(by.css('input#image-createdBy'));
  modifiedByInput: ElementFinder = element(by.css('input#image-modifiedBy'));
  dataSizeInput: ElementFinder = element(by.css('input#image-dataSize'));
  commentInput: ElementFinder = element(by.css('input#image-comment'));

  getPageTitle()
  {
    return this.pageTitle;
  }

  async setUuidInput(uuid)
  {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput()
  {
    return this.uuidInput.getAttribute('value');
  }

  async setImageDataInput(imageData)
  {
    await this.imageDataInput.sendKeys(imageData);
  }

  async getImageDataInput()
  {
    return this.imageDataInput.getAttribute('value');
  }

  async setNameImageInput(nameImage)
  {
    await this.nameImageInput.sendKeys(nameImage);
  }

  async getNameImageInput()
  {
    return this.nameImageInput.getAttribute('value');
  }

  async setExtensionInput(extension)
  {
    await this.extensionInput.sendKeys(extension);
  }

  async getExtensionInput()
  {
    return this.extensionInput.getAttribute('value');
  }

  async setTypeFileInput(typeFile)
  {
    await this.typeFileInput.sendKeys(typeFile);
  }

  async getTypeFileInput()
  {
    return this.typeFileInput.getAttribute('value');
  }

  async setSearchFieldInput(searchField)
  {
    await this.searchFieldInput.sendKeys(searchField);
  }

  async getSearchFieldInput()
  {
    return this.searchFieldInput.getAttribute('value');
  }

  async setRoleInput(role)
  {
    await this.roleInput.sendKeys(role);
  }

  async getRoleInput()
  {
    return this.roleInput.getAttribute('value');
  }

  async setCreatedDateInput(createdDate)
  {
    await this.createdDateInput.sendKeys(createdDate);
  }

  async getCreatedDateInput()
  {
    return this.createdDateInput.getAttribute('value');
  }

  async setModifiedDateInput(modifiedDate)
  {
    await this.modifiedDateInput.sendKeys(modifiedDate);
  }

  async getModifiedDateInput()
  {
    return this.modifiedDateInput.getAttribute('value');
  }

  async setCreatedByInput(createdBy)
  {
    await this.createdByInput.sendKeys(createdBy);
  }

  async getCreatedByInput()
  {
    return this.createdByInput.getAttribute('value');
  }

  async setModifiedByInput(modifiedBy)
  {
    await this.modifiedByInput.sendKeys(modifiedBy);
  }

  async getModifiedByInput()
  {
    return this.modifiedByInput.getAttribute('value');
  }

  async setDataSizeInput(dataSize)
  {
    await this.dataSizeInput.sendKeys(dataSize);
  }

  async getDataSizeInput()
  {
    return this.dataSizeInput.getAttribute('value');
  }

  async setCommentInput(comment)
  {
    await this.commentInput.sendKeys(comment);
  }

  async getCommentInput()
  {
    return this.commentInput.getAttribute('value');
  }

  async save()
  {
    await this.saveButton.click();
  }

  async cancel()
  {
    await this.cancelButton.click();
  }

  getSaveButton()
  {
    return this.saveButton;
  }

  async enterData()
  {
    await waitUntilDisplayed(this.saveButton);
    await this.setUuidInput('64c99148-3908-465d-8c4a-e510e3ade974');
    expect(await this.getUuidInput()).to.match(/64c99148-3908-465d-8c4a-e510e3ade974/);
    await waitUntilDisplayed(this.saveButton);
    await this.setImageDataInput(absolutePath);
    await waitUntilDisplayed(this.saveButton);
    await this.setNameImageInput('nameImage');
    expect(await this.getNameImageInput()).to.match(/nameImage/);
    await waitUntilDisplayed(this.saveButton);
    await this.setExtensionInput('extension');
    expect(await this.getExtensionInput()).to.match(/extension/);
    await waitUntilDisplayed(this.saveButton);
    await this.setTypeFileInput('typeFile');
    expect(await this.getTypeFileInput()).to.match(/typeFile/);
    await waitUntilDisplayed(this.saveButton);
    await this.setSearchFieldInput('searchField');
    expect(await this.getSearchFieldInput()).to.match(/searchField/);
    await waitUntilDisplayed(this.saveButton);
    await this.setRoleInput('role');
    expect(await this.getRoleInput()).to.match(/role/);
    await waitUntilDisplayed(this.saveButton);
    await this.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await this.getCreatedDateInput()).to.contain('2001-01-01T02:30');
    await waitUntilDisplayed(this.saveButton);
    await this.setModifiedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    expect(await this.getModifiedDateInput()).to.contain('2001-01-01T02:30');
    await waitUntilDisplayed(this.saveButton);
    await this.setCreatedByInput('createdBy');
    expect(await this.getCreatedByInput()).to.match(/createdBy/);
    await waitUntilDisplayed(this.saveButton);
    await this.setModifiedByInput('modifiedBy');
    expect(await this.getModifiedByInput()).to.match(/modifiedBy/);
    await waitUntilDisplayed(this.saveButton);
    await this.setDataSizeInput('5');
    expect(await this.getDataSizeInput()).to.eq('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setCommentInput('comment');
    expect(await this.getCommentInput()).to.match(/comment/);
    await this.save();
    await waitUntilHidden(this.saveButton);
    expect(await isVisible(this.saveButton)).to.be.false;
  }
}
