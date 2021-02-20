import { by, element, ElementArrayFinder, ElementFinder } from 'protractor';

import { click, isVisible, waitUntilAnyDisplayed, waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import UserOtherInfoUpdatePage from './user-other-info-update.page-object';

const expect = chai.expect;

export class UserOtherInfoDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('minhShopApp.userOtherInfo.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-userOtherInfo'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class UserOtherInfoComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('user-other-info-heading'));
  noRecords: ElementFinder = element(by.css('#app-view-container .table-responsive div.alert.alert-warning'));
  table: ElementFinder = element(by.css('#app-view-container div.table-responsive > table'));

  records: ElementArrayFinder = this.table.all(by.css('tbody tr'));

  getDetailsButton(record: ElementFinder) {
    return record.element(by.css('a.btn.btn-info.btn-sm'));
  }

  getEditButton(record: ElementFinder) {
    return record.element(by.css('a.btn.btn-primary.btn-sm'));
  }

  getDeleteButton(record: ElementFinder) {
    return record.element(by.css('a.btn.btn-danger.btn-sm'));
  }

  async goToPage(navBarPage: NavBarPage) {
    await navBarPage.getEntityPage('user-other-info');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateUserOtherInfo() {
    await this.createButton.click();
    return new UserOtherInfoUpdatePage();
  }

  async deleteUserOtherInfo() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const userOtherInfoDeleteDialog = new UserOtherInfoDeleteDialog();
    await waitUntilDisplayed(userOtherInfoDeleteDialog.deleteModal);
    expect(await userOtherInfoDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/minhShopApp.userOtherInfo.delete.question/);
    await userOtherInfoDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(userOtherInfoDeleteDialog.deleteModal);

    expect(await isVisible(userOtherInfoDeleteDialog.deleteModal)).to.be.false;
  }
}
