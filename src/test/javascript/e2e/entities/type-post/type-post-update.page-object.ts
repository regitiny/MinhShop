import { element, by, ElementFinder, protractor } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class TypePostUpdatePage {
  pageTitle: ElementFinder = element(by.id('minhShopApp.typePost.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#type-post-uuid'));
  typeNameInput: ElementFinder = element(by.css('input#type-post-typeName'));
  searchFieldInput: ElementFinder = element(by.css('textarea#type-post-searchField'));
  roleInput: ElementFinder = element(by.css('input#type-post-role'));
  createdDateInput: ElementFinder = element(by.css('input#type-post-createdDate'));
  modifiedDateInput: ElementFinder = element(by.css('input#type-post-modifiedDate'));
  createdByInput: ElementFinder = element(by.css('input#type-post-createdBy'));
  modifiedByInput: ElementFinder = element(by.css('input#type-post-modifiedBy'));
  dataSizeInput: ElementFinder = element(by.css('input#type-post-dataSize'));
  commentInput: ElementFinder = element(by.css('input#type-post-comment'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
  }

  async setTypeNameInput(typeName) {
    await this.typeNameInput.sendKeys(typeName);
  }

  async getTypeNameInput() {
    return this.typeNameInput.getAttribute('value');
  }

  async setSearchFieldInput(searchField) {
    await this.searchFieldInput.sendKeys(searchField);
  }

  async getSearchFieldInput() {
    return this.searchFieldInput.getAttribute('value');
  }

  async setRoleInput(role) {
    await this.roleInput.sendKeys(role);
  }

  async getRoleInput() {
    return this.roleInput.getAttribute('value');
  }

  async setCreatedDateInput(createdDate) {
    await this.createdDateInput.sendKeys(createdDate);
  }

  async getCreatedDateInput() {
    return this.createdDateInput.getAttribute('value');
  }

  async setModifiedDateInput(modifiedDate) {
    await this.modifiedDateInput.sendKeys(modifiedDate);
  }

  async getModifiedDateInput() {
    return this.modifiedDateInput.getAttribute('value');
  }

  async setCreatedByInput(createdBy) {
    await this.createdByInput.sendKeys(createdBy);
  }

  async getCreatedByInput() {
    return this.createdByInput.getAttribute('value');
  }

  async setModifiedByInput(modifiedBy) {
    await this.modifiedByInput.sendKeys(modifiedBy);
  }

  async getModifiedByInput() {
    return this.modifiedByInput.getAttribute('value');
  }

  async setDataSizeInput(dataSize) {
    await this.dataSizeInput.sendKeys(dataSize);
  }

  async getDataSizeInput() {
    return this.dataSizeInput.getAttribute('value');
  }

  async setCommentInput(comment) {
    await this.commentInput.sendKeys(comment);
  }

  async getCommentInput() {
    return this.commentInput.getAttribute('value');
  }

  async save() {
    await this.saveButton.click();
  }

  async cancel() {
    await this.cancelButton.click();
  }

  getSaveButton() {
    return this.saveButton;
  }

  async enterData() {
    await waitUntilDisplayed(this.saveButton);
    await this.setUuidInput('64c99148-3908-465d-8c4a-e510e3ade974');
    expect(await this.getUuidInput()).to.match(/64c99148-3908-465d-8c4a-e510e3ade974/);
    await waitUntilDisplayed(this.saveButton);
    await this.setTypeNameInput('typeName');
    expect(await this.getTypeNameInput()).to.match(/typeName/);
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
