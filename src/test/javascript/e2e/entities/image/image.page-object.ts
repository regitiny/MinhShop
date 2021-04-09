import {by, element, ElementArrayFinder, ElementFinder} from 'protractor';

import {click, isVisible, waitUntilAnyDisplayed, waitUntilDisplayed, waitUntilHidden} from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import ImageUpdatePage from './image-update.page-object';

const expect = chai.expect;

export class ImageDeleteDialog
{
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('minhShopApp.image.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-image'));

  getDialogTitle()
  {
    return this.dialogTitle;
  }

  async clickOnConfirmButton()
  {
    await this.confirmButton.click();
  }
}

export default class ImageComponentsPage
{
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('image-heading'));
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
    await navBarPage.getEntityPage('image');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateImage()
  {
    await this.createButton.click();
    return new ImageUpdatePage();
  }

  async deleteImage()
  {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const imageDeleteDialog = new ImageDeleteDialog();
    await waitUntilDisplayed(imageDeleteDialog.deleteModal);
    expect(await imageDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/minhShopApp.image.delete.question/);
    await imageDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(imageDeleteDialog.deleteModal);

    expect(await isVisible(imageDeleteDialog.deleteModal)).to.be.false;
  }
}
