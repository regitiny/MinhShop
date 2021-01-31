import { element, by, ElementFinder, protractor } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class UserOtherInfoUpdatePage {
  pageTitle: ElementFinder = element(by.id('minhShopApp.userOtherInfo.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#user-other-info-uuid'));
  phoneNumberInput: ElementFinder = element(by.css('input#user-other-info-phoneNumber'));
  emailInput: ElementFinder = element(by.css('input#user-other-info-email'));
  wardCodeInput: ElementFinder = element(by.css('input#user-other-info-wardCode'));
  distCodeInput: ElementFinder = element(by.css('input#user-other-info-distCode'));
  cityCodeInput: ElementFinder = element(by.css('input#user-other-info-cityCode'));
  addressDetailsInput: ElementFinder = element(by.css('input#user-other-info-addressDetails'));
  dateOfBirthInput: ElementFinder = element(by.css('input#user-other-info-dateOfBirth'));
  otherInfoInput: ElementFinder = element(by.css('input#user-other-info-otherInfo'));
  roleInput: ElementFinder = element(by.css('input#user-other-info-role'));
  createdDateInput: ElementFinder = element(by.css('input#user-other-info-createdDate'));
  modifiedDateInput: ElementFinder = element(by.css('input#user-other-info-modifiedDate'));
  createdByInput: ElementFinder = element(by.css('input#user-other-info-createdBy'));
  modifiedByInput: ElementFinder = element(by.css('input#user-other-info-modifiedBy'));
  userNameSelect: ElementFinder = element(by.css('select#user-other-info-userName'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
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

  async setWardCodeInput(wardCode) {
    await this.wardCodeInput.sendKeys(wardCode);
  }

  async getWardCodeInput() {
    return this.wardCodeInput.getAttribute('value');
  }

  async setDistCodeInput(distCode) {
    await this.distCodeInput.sendKeys(distCode);
  }

  async getDistCodeInput() {
    return this.distCodeInput.getAttribute('value');
  }

  async setCityCodeInput(cityCode) {
    await this.cityCodeInput.sendKeys(cityCode);
  }

  async getCityCodeInput() {
    return this.cityCodeInput.getAttribute('value');
  }

  async setAddressDetailsInput(addressDetails) {
    await this.addressDetailsInput.sendKeys(addressDetails);
  }

  async getAddressDetailsInput() {
    return this.addressDetailsInput.getAttribute('value');
  }

  async setDateOfBirthInput(dateOfBirth) {
    await this.dateOfBirthInput.sendKeys(dateOfBirth);
  }

  async getDateOfBirthInput() {
    return this.dateOfBirthInput.getAttribute('value');
  }

  async setOtherInfoInput(otherInfo) {
    await this.otherInfoInput.sendKeys(otherInfo);
  }

  async getOtherInfoInput() {
    return this.otherInfoInput.getAttribute('value');
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

  async userNameSelectLastOption() {
    await this.userNameSelect.all(by.tagName('option')).last().click();
  }

  async userNameSelectOption(option) {
    await this.userNameSelect.sendKeys(option);
  }

  getUserNameSelect() {
    return this.userNameSelect;
  }

  async getUserNameSelectedOption() {
    return this.userNameSelect.element(by.css('option:checked')).getText();
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
    await this.setPhoneNumberInput('089059');
    expect(await this.getPhoneNumberInput()).to.match(/089059/);
    await waitUntilDisplayed(this.saveButton);
    await this.setEmailInput('email');
    expect(await this.getEmailInput()).to.match(/email/);
    await waitUntilDisplayed(this.saveButton);
    await this.setWardCodeInput('wardCode');
    expect(await this.getWardCodeInput()).to.match(/wardCode/);
    await waitUntilDisplayed(this.saveButton);
    await this.setDistCodeInput('distCode');
    expect(await this.getDistCodeInput()).to.match(/distCode/);
    await waitUntilDisplayed(this.saveButton);
    await this.setCityCodeInput('cityCode');
    expect(await this.getCityCodeInput()).to.match(/cityCode/);
    await waitUntilDisplayed(this.saveButton);
    await this.setAddressDetailsInput('addressDetails');
    expect(await this.getAddressDetailsInput()).to.match(/addressDetails/);
    await waitUntilDisplayed(this.saveButton);
    await this.setDateOfBirthInput('01-01-2001');
    expect(await this.getDateOfBirthInput()).to.eq('2001-01-01');
    await waitUntilDisplayed(this.saveButton);
    await this.setOtherInfoInput('otherInfo');
    expect(await this.getOtherInfoInput()).to.match(/otherInfo/);
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
    await this.userNameSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
    expect(await isVisible(this.saveButton)).to.be.false;
  }
}
