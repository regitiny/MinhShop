import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import PostDetailsUpdatePage from './post-details-update.page-object';

const expect = chai.expect;
export class PostDetailsDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('minhShopApp.postDetails.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-postDetails'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class PostDetailsComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('post-details-heading'));
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
    await navBarPage.getEntityPage('post-details');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreatePostDetails() {
    await this.createButton.click();
    return new PostDetailsUpdatePage();
  }

  async deletePostDetails() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const postDetailsDeleteDialog = new PostDetailsDeleteDialog();
    await waitUntilDisplayed(postDetailsDeleteDialog.deleteModal);
    expect(await postDetailsDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/minhShopApp.postDetails.delete.question/);
    await postDetailsDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(postDetailsDeleteDialog.deleteModal);

    expect(await isVisible(postDetailsDeleteDialog.deleteModal)).to.be.false;
  }
}
