import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import SimplePostUpdatePage from './simple-post-update.page-object';

const expect = chai.expect;
export class SimplePostDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('minhShopApp.simplePost.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-simplePost'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class SimplePostComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('simple-post-heading'));
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
    await navBarPage.getEntityPage('simple-post');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateSimplePost() {
    await this.createButton.click();
    return new SimplePostUpdatePage();
  }

  async deleteSimplePost() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const simplePostDeleteDialog = new SimplePostDeleteDialog();
    await waitUntilDisplayed(simplePostDeleteDialog.deleteModal);
    expect(await simplePostDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/minhShopApp.simplePost.delete.question/);
    await simplePostDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(simplePostDeleteDialog.deleteModal);

    expect(await isVisible(simplePostDeleteDialog.deleteModal)).to.be.false;
  }
}
