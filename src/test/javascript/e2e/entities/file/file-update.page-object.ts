import { element, by, ElementFinder, protractor } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

import path from 'path';

const expect = chai.expect;

const fileToUpload = '../../../../../../src/main/webapp/content/images/logo-jhipster.png';
const absolutePath = path.resolve(__dirname, fileToUpload);
export default class FileUpdatePage {
  pageTitle: ElementFinder = element(by.id('minhShopApp.file.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#file-uuid'));
  videoDataInput: ElementFinder = element(by.css('input#file_videoData'));
  nameVideoInput: ElementFinder = element(by.css('input#file-nameVideo'));
  extensionInput: ElementFinder = element(by.css('input#file-extension'));
  typeFileInput: ElementFinder = element(by.css('input#file-typeFile'));
  roleInput: ElementFinder = element(by.css('input#file-role'));
  createdDateInput: ElementFinder = element(by.css('input#file-createdDate'));
  modifiedDateInput: ElementFinder = element(by.css('input#file-modifiedDate'));
  createdByInput: ElementFinder = element(by.css('input#file-createdBy'));
  modifiedByInput: ElementFinder = element(by.css('input#file-modifiedBy'));
  dataSizeInput: ElementFinder = element(by.css('input#file-dataSize'));
  commentInput: ElementFinder = element(by.css('input#file-comment'));
  deletedInput: ElementFinder = element(by.css('input#file-deleted'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
  }

  async setVideoDataInput(videoData) {
    await this.videoDataInput.sendKeys(videoData);
  }

  async getVideoDataInput() {
    return this.videoDataInput.getAttribute('value');
  }

  async setNameVideoInput(nameVideo) {
    await this.nameVideoInput.sendKeys(nameVideo);
  }

  async getNameVideoInput() {
    return this.nameVideoInput.getAttribute('value');
  }

  async setExtensionInput(extension) {
    await this.extensionInput.sendKeys(extension);
  }

  async getExtensionInput() {
    return this.extensionInput.getAttribute('value');
  }

  async setTypeFileInput(typeFile) {
    await this.typeFileInput.sendKeys(typeFile);
  }

  async getTypeFileInput() {
    return this.typeFileInput.getAttribute('value');
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

  getDeletedInput() {
    return this.deletedInput;
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
    await this.setVideoDataInput(absolutePath);
    await waitUntilDisplayed(this.saveButton);
    await this.setNameVideoInput('nameVideo');
    expect(await this.getNameVideoInput()).to.match(/nameVideo/);
    await waitUntilDisplayed(this.saveButton);
    await this.setExtensionInput('extension');
    expect(await this.getExtensionInput()).to.match(/extension/);
    await waitUntilDisplayed(this.saveButton);
    await this.setTypeFileInput('typeFile');
    expect(await this.getTypeFileInput()).to.match(/typeFile/);
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
    await waitUntilDisplayed(this.saveButton);
    const selectedDeleted = await this.getDeletedInput().isSelected();
    if (selectedDeleted) {
      await this.getDeletedInput().click();
      expect(await this.getDeletedInput().isSelected()).to.be.false;
    } else {
      await this.getDeletedInput().click();
      expect(await this.getDeletedInput().isSelected()).to.be.true;
    }
    await this.save();
    await waitUntilHidden(this.saveButton);
    expect(await isVisible(this.saveButton)).to.be.false;
  }
}
