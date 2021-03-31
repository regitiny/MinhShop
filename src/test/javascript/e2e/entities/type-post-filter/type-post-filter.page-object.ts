import {by, element, ElementArrayFinder, ElementFinder} from 'protractor';

import {click, isVisible, waitUntilAnyDisplayed, waitUntilDisplayed, waitUntilHidden} from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import TypePostFilterUpdatePage from './type-post-filter-update.page-object';

const expect = chai.expect;

export class TypePostFilterDeleteDialog
{
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('minhShopApp.typePostFilter.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-typePostFilter'));

  getDialogTitle()
  {
    return this.dialogTitle;
  }

  async clickOnConfirmButton()
  {
    await this.confirmButton.click();
  }
}

export default class TypePostFilterComponentsPage
{
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('type-post-filter-heading'));
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
    await navBarPage.getEntityPage('type-post-filter');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateTypePostFilter()
  {
    await this.createButton.click();
    return new TypePostFilterUpdatePage();
  }

  async deleteTypePostFilter()
  {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const typePostFilterDeleteDialog = new TypePostFilterDeleteDialog();
    await waitUntilDisplayed(typePostFilterDeleteDialog.deleteModal);
    expect(await typePostFilterDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/minhShopApp.typePostFilter.delete.question/);
    await typePostFilterDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(typePostFilterDeleteDialog.deleteModal);

    expect(await isVisible(typePostFilterDeleteDialog.deleteModal)).to.be.false;
  }
}
