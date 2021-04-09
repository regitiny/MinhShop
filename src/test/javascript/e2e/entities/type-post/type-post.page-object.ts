import {by, element, ElementArrayFinder, ElementFinder} from 'protractor';

import {click, isVisible, waitUntilAnyDisplayed, waitUntilDisplayed, waitUntilHidden} from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import TypePostUpdatePage from './type-post-update.page-object';

const expect = chai.expect;

export class TypePostDeleteDialog
{
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('minhShopApp.typePost.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-typePost'));

  getDialogTitle()
  {
    return this.dialogTitle;
  }

  async clickOnConfirmButton()
  {
    await this.confirmButton.click();
  }
}

export default class TypePostComponentsPage
{
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('type-post-heading'));
  noRecords: ElementFinder = element(by.css('#app-view-container .table-responsive div.alert.alert-warning'));
  table: ElementFinder = element(by.css('#app-view-container div.table-responsive > table'));

  records: ElementArrayFinder = this.table.all(by.css('tbody tr'));

  getDetailsButton(record: ElementFinder)
  {
    return record.element(by.css('a.btn.btn-info.btn-sm'));
  }

  getEditButton(record: ElementFinder)
  {
    return record.element(by.css('a.btn.btn-primary.btn-sm'));
  }

  getDeleteButton(record: ElementFinder)
  {
    return record.element(by.css('a.btn.btn-danger.btn-sm'));
  }

  async goToPage(navBarPage: NavBarPage)
  {
    await navBarPage.getEntityPage('type-post');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateTypePost()
  {
    await this.createButton.click();
    return new TypePostUpdatePage();
  }

  async deleteTypePost()
  {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const typePostDeleteDialog = new TypePostDeleteDialog();
    await waitUntilDisplayed(typePostDeleteDialog.deleteModal);
    expect(await typePostDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/minhShopApp.typePost.delete.question/);
    await typePostDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(typePostDeleteDialog.deleteModal);

    expect(await isVisible(typePostDeleteDialog.deleteModal)).to.be.false;
  }
}
