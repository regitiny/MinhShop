import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import BillUpdatePage from './bill-update.page-object';

const expect = chai.expect;
export class BillDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('minhShopApp.bill.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-bill'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class BillComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('bill-heading'));
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
    await navBarPage.getEntityPage('bill');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateBill() {
    await this.createButton.click();
    return new BillUpdatePage();
  }

  async deleteBill() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const billDeleteDialog = new BillDeleteDialog();
    await waitUntilDisplayed(billDeleteDialog.deleteModal);
    expect(await billDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/minhShopApp.bill.delete.question/);
    await billDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(billDeleteDialog.deleteModal);

    expect(await isVisible(billDeleteDialog.deleteModal)).to.be.false;
  }
}
