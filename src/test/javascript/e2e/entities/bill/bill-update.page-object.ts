import { element, by, ElementFinder, protractor } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class BillUpdatePage {
  pageTitle: ElementFinder = element(by.id('minhShopApp.bill.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#bill-uuid'));
  billIdInput: ElementFinder = element(by.css('input#bill-billId'));
  phoneNumberInput: ElementFinder = element(by.css('input#bill-phoneNumber'));
  emailInput: ElementFinder = element(by.css('input#bill-email'));
  addressDetailsInput: ElementFinder = element(by.css('input#bill-addressDetails'));
  addressCodeInput: ElementFinder = element(by.css('input#bill-addressCode'));
  commentInput: ElementFinder = element(by.css('input#bill-comment'));
  roleInput: ElementFinder = element(by.css('input#bill-role'));
  createdDateInput: ElementFinder = element(by.css('input#bill-createdDate'));
  modifiedDateInput: ElementFinder = element(by.css('input#bill-modifiedDate'));
  createdByInput: ElementFinder = element(by.css('input#bill-createdBy'));
  modifiedByInput: ElementFinder = element(by.css('input#bill-modifiedBy'));
  userOtherInfoSelect: ElementFinder = element(by.css('select#bill-userOtherInfo'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
  }

  async setBillIdInput(billId) {
    await this.billIdInput.sendKeys(billId);
  }

  async getBillIdInput() {
    return this.billIdInput.getAttribute('value');
  }

  async setPhoneNumberInput(phoneNumber) {
    await this.phoneNumberInput.sendKeys(phoneNumber);
  }

  async getPhoneNumberInput() {
    return this.phoneNumberInput.getAttribute('value');
  }

  async setEmailInput(email) {
    await this.emailInput.sendKeys(email);
  }

  async getEmailInput() {
    return this.emailInput.getAttribute('value');
  }

  async setAddressDetailsInput(addressDetails) {
    await this.addressDetailsInput.sendKeys(addressDetails);
  }

  async getAddressDetailsInput() {
    return this.addressDetailsInput.getAttribute('value');
  }

  async setAddressCodeInput(addressCode) {
    await this.addressCodeInput.sendKeys(addressCode);
  }

  async getAddressCodeInput() {
    return this.addressCodeInput.getAttribute('value');
  }

  async setCommentInput(comment) {
    await this.commentInput.sendKeys(comment);
  }

  async getCommentInput() {
    return this.commentInput.getAttribute('value');
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

  async userOtherInfoSelectLastOption() {
    await this.userOtherInfoSelect.all(by.tagName('option')).last().click();
  }

  async userOtherInfoSelectOption(option) {
    await this.userOtherInfoSelect.sendKeys(option);
  }

  getUserOtherInfoSelect() {
    return this.userOtherInfoSelect;
  }

  async getUserOtherInfoSelectedOption() {
    return this.userOtherInfoSelect.element(by.css('option:checked')).getText();
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
    await this.setBillIdInput('billId');
    expect(await this.getBillIdInput()).to.match(/billId/);
    await waitUntilDisplayed(this.saveButton);
    await this.setPhoneNumberInput('5');
    expect(await this.getPhoneNumberInput()).to.match(/5/);
    await waitUntilDisplayed(this.saveButton);
    await this.setEmailInput('email');
    expect(await this.getEmailInput()).to.match(/email/);
    await waitUntilDisplayed(this.saveButton);
    await this.setAddressDetailsInput('addressDetails');
    expect(await this.getAddressDetailsInput()).to.match(/addressDetails/);
    await waitUntilDisplayed(this.saveButton);
    await this.setAddressCodeInput('addressCode');
    expect(await this.getAddressCodeInput()).to.match(/addressCode/);
    await waitUntilDisplayed(this.saveButton);
    await this.setCommentInput('comment');
    expect(await this.getCommentInput()).to.match(/comment/);
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
    await this.userOtherInfoSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
    expect(await isVisible(this.saveButton)).to.be.false;
  }
}
