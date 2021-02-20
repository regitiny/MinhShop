import { by, element, ElementFinder, protractor } from 'protractor';
import { isVisible, waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

export default class SimplePostUpdatePage {
  pageTitle: ElementFinder = element(by.id('minhShopApp.simplePost.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#simple-post-uuid'));
  titleInput: ElementFinder = element(by.css('input#simple-post-title'));
  priceInput: ElementFinder = element(by.css('input#simple-post-price'));
  salePriceInput: ElementFinder = element(by.css('input#simple-post-salePrice'));
  percentSaleInput: ElementFinder = element(by.css('input#simple-post-percentSale'));
  imageUrlInput: ElementFinder = element(by.css('input#simple-post-imageUrl'));
  scoresInput: ElementFinder = element(by.css('input#simple-post-scores'));
  simpleContentInput: ElementFinder = element(by.css('input#simple-post-simpleContent'));
  otherInfoInput: ElementFinder = element(by.css('input#simple-post-otherInfo'));
  searchFieldInput: ElementFinder = element(by.css('textarea#simple-post-searchField'));
  roleInput: ElementFinder = element(by.css('input#simple-post-role'));
  createdDateInput: ElementFinder = element(by.css('input#simple-post-createdDate'));
  modifiedDateInput: ElementFinder = element(by.css('input#simple-post-modifiedDate'));
  createdByInput: ElementFinder = element(by.css('input#simple-post-createdBy'));
  modifiedByInput: ElementFinder = element(by.css('input#simple-post-modifiedBy'));
  dataSizeInput: ElementFinder = element(by.css('input#simple-post-dataSize'));
  commentInput: ElementFinder = element(by.css('input#simple-post-comment'));
  postDetailsSelect: ElementFinder = element(by.css('select#simple-post-postDetails'));
  typePostSelect: ElementFinder = element(by.css('select#simple-post-typePost'));
  typePostFilterSelect: ElementFinder = element(by.css('select#simple-post-typePostFilter'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
  }

  async setTitleInput(title) {
    await this.titleInput.sendKeys(title);
  }

  async getTitleInput() {
    return this.titleInput.getAttribute('value');
  }

  async setPriceInput(price) {
    await this.priceInput.sendKeys(price);
  }

  async getPriceInput() {
    return this.priceInput.getAttribute('value');
  }

  async setSalePriceInput(salePrice) {
    await this.salePriceInput.sendKeys(salePrice);
  }

  async getSalePriceInput() {
    return this.salePriceInput.getAttribute('value');
  }

  async setPercentSaleInput(percentSale) {
    await this.percentSaleInput.sendKeys(percentSale);
  }

  async getPercentSaleInput() {
    return this.percentSaleInput.getAttribute('value');
  }

  async setImageUrlInput(imageUrl) {
    await this.imageUrlInput.sendKeys(imageUrl);
  }

  async getImageUrlInput() {
    return this.imageUrlInput.getAttribute('value');
  }

  async setScoresInput(scores) {
    await this.scoresInput.sendKeys(scores);
  }

  async getScoresInput() {
    return this.scoresInput.getAttribute('value');
  }

  async setSimpleContentInput(simpleContent) {
    await this.simpleContentInput.sendKeys(simpleContent);
  }

  async getSimpleContentInput() {
    return this.simpleContentInput.getAttribute('value');
  }

  async setOtherInfoInput(otherInfo) {
    await this.otherInfoInput.sendKeys(otherInfo);
  }

  async getOtherInfoInput() {
    return this.otherInfoInput.getAttribute('value');
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

  async postDetailsSelectLastOption() {
    await this.postDetailsSelect.all(by.tagName('option')).last().click();
  }

  async postDetailsSelectOption(option) {
    await this.postDetailsSelect.sendKeys(option);
  }

  getPostDetailsSelect() {
    return this.postDetailsSelect;
  }

  async getPostDetailsSelectedOption() {
    return this.postDetailsSelect.element(by.css('option:checked')).getText();
  }

  async typePostSelectLastOption() {
    await this.typePostSelect.all(by.tagName('option')).last().click();
  }

  async typePostSelectOption(option) {
    await this.typePostSelect.sendKeys(option);
  }

  getTypePostSelect() {
    return this.typePostSelect;
  }

  async getTypePostSelectedOption() {
    return this.typePostSelect.element(by.css('option:checked')).getText();
  }

  async typePostFilterSelectLastOption() {
    await this.typePostFilterSelect.all(by.tagName('option')).last().click();
  }

  async typePostFilterSelectOption(option) {
    await this.typePostFilterSelect.sendKeys(option);
  }

  getTypePostFilterSelect() {
    return this.typePostFilterSelect;
  }

  async getTypePostFilterSelectedOption() {
    return this.typePostFilterSelect.element(by.css('option:checked')).getText();
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
    await this.setTitleInput('title');
    expect(await this.getTitleInput()).to.match(/title/);
    await waitUntilDisplayed(this.saveButton);
    await this.setPriceInput('5');
    expect(await this.getPriceInput()).to.eq('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setSalePriceInput('5');
    expect(await this.getSalePriceInput()).to.eq('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setPercentSaleInput('5');
    expect(await this.getPercentSaleInput()).to.eq('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setImageUrlInput('imageUrl');
    expect(await this.getImageUrlInput()).to.match(/imageUrl/);
    await waitUntilDisplayed(this.saveButton);
    await this.setScoresInput('5');
    expect(await this.getScoresInput()).to.eq('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setSimpleContentInput('simpleContent');
    expect(await this.getSimpleContentInput()).to.match(/simpleContent/);
    await waitUntilDisplayed(this.saveButton);
    await this.setOtherInfoInput('otherInfo');
    expect(await this.getOtherInfoInput()).to.match(/otherInfo/);
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
    await this.postDetailsSelectLastOption();
    await this.typePostSelectLastOption();
    // this.typePostFilterSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
    expect(await isVisible(this.saveButton)).to.be.false;
  }
}
