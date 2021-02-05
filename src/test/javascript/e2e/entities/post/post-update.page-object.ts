import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class PostUpdatePage {
  pageTitle: ElementFinder = element(by.id('minhShopApp.post.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  titleInput: ElementFinder = element(by.css('input#post-title'));
  priceInput: ElementFinder = element(by.css('input#post-price'));
  salePriceInput: ElementFinder = element(by.css('input#post-salePrice'));
  percentSaleInput: ElementFinder = element(by.css('input#post-percentSale'));
  imageUrlInput: ElementFinder = element(by.css('input#post-imageUrl'));
  scoresInput: ElementFinder = element(by.css('input#post-scores'));
  simpleContentInput: ElementFinder = element(by.css('input#post-simpleContent'));
  otherInfoInput: ElementFinder = element(by.css('input#post-otherInfo'));
  postDetailsIdInput: ElementFinder = element(by.css('input#post-postDetailsId'));
  contentInput: ElementFinder = element(by.css('textarea#post-content'));
  roleInput: ElementFinder = element(by.css('input#post-role'));
  createdByInput: ElementFinder = element(by.css('input#post-createdBy'));
  modifiedByInput: ElementFinder = element(by.css('input#post-modifiedBy'));
  commentInput: ElementFinder = element(by.css('input#post-comment'));

  getPageTitle() {
    return this.pageTitle;
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

  async setPostDetailsIdInput(postDetailsId) {
    await this.postDetailsIdInput.sendKeys(postDetailsId);
  }

  async getPostDetailsIdInput() {
    return this.postDetailsIdInput.getAttribute('value');
  }

  async setContentInput(content) {
    await this.contentInput.sendKeys(content);
  }

  async getContentInput() {
    return this.contentInput.getAttribute('value');
  }

  async setRoleInput(role) {
    await this.roleInput.sendKeys(role);
  }

  async getRoleInput() {
    return this.roleInput.getAttribute('value');
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
    await this.setPostDetailsIdInput('ijW6850');
    expect(await this.getPostDetailsIdInput()).to.match(/ijW6850/);
    await waitUntilDisplayed(this.saveButton);
    await this.setContentInput('content');
    expect(await this.getContentInput()).to.match(/content/);
    await waitUntilDisplayed(this.saveButton);
    await this.setRoleInput('role');
    expect(await this.getRoleInput()).to.match(/role/);
    await waitUntilDisplayed(this.saveButton);
    await this.setCreatedByInput('createdBy');
    expect(await this.getCreatedByInput()).to.match(/createdBy/);
    await waitUntilDisplayed(this.saveButton);
    await this.setModifiedByInput('modifiedBy');
    expect(await this.getModifiedByInput()).to.match(/modifiedBy/);
    await waitUntilDisplayed(this.saveButton);
    await this.setCommentInput('comment');
    expect(await this.getCommentInput()).to.match(/comment/);
    await this.save();
    await waitUntilHidden(this.saveButton);
    expect(await isVisible(this.saveButton)).to.be.false;
  }
}
