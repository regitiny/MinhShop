import {browser} from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import BillComponentsPage from './bill.page-object';
import BillUpdatePage from './bill-update.page-object';
import {getRecordsCount, isVisible, waitUntilCount, waitUntilDisplayed} from '../../util/utils';

const expect = chai.expect;

describe('Bill e2e test', () =>
{
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let billComponentsPage: BillComponentsPage;
  let billUpdatePage: BillUpdatePage;
  const username = process.env.E2E_USERNAME ?? 'admin';
  const password = process.env.E2E_PASSWORD ?? 'admin';

  before(async () =>
  {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.waitUntilDisplayed();
    await signInPage.username.sendKeys(username);
    await signInPage.password.sendKeys(password);
    await signInPage.loginButton.click();
    await signInPage.waitUntilHidden();
    await waitUntilDisplayed(navBarPage.entityMenu);
    await waitUntilDisplayed(navBarPage.adminMenu);
    await waitUntilDisplayed(navBarPage.accountMenu);
  });

  beforeEach(async () =>
  {
    await browser.get('/');
    await waitUntilDisplayed(navBarPage.entityMenu);
    billComponentsPage = new BillComponentsPage();
    billComponentsPage = await billComponentsPage.goToPage(navBarPage);
  });

  it('should load Bills', async () =>
  {
    expect(await billComponentsPage.title.getText()).to.match(/Bills/);
    expect(await billComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete Bills', async () =>
  {
    const beforeRecordsCount = (await isVisible(billComponentsPage.noRecords)) ? 0 : await getRecordsCount(billComponentsPage.table);
    billUpdatePage = await billComponentsPage.goToCreateBill();
    await billUpdatePage.enterData();

    expect(await billComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(billComponentsPage.table);
    await waitUntilCount(billComponentsPage.records, beforeRecordsCount + 1);
    expect(await billComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await billComponentsPage.deleteBill();
    if (beforeRecordsCount !== 0)
    {
      await waitUntilCount(billComponentsPage.records, beforeRecordsCount);
      expect(await billComponentsPage.records.count()).to.eq(beforeRecordsCount);
    }
    else
    {
      await waitUntilDisplayed(billComponentsPage.noRecords);
    }
  });

  after(async () =>
  {
    await navBarPage.autoSignOut();
  });
});
