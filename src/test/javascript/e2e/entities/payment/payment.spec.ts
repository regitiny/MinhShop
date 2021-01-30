import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import PaymentComponentsPage from './payment.page-object';
import PaymentUpdatePage from './payment-update.page-object';
import {
  waitUntilDisplayed,
  waitUntilAnyDisplayed,
  click,
  getRecordsCount,
  waitUntilHidden,
  waitUntilCount,
  isVisible,
} from '../../util/utils';

const expect = chai.expect;

describe('Payment e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let paymentComponentsPage: PaymentComponentsPage;
  let paymentUpdatePage: PaymentUpdatePage;
  const username = process.env.E2E_USERNAME ?? 'admin';
  const password = process.env.E2E_PASSWORD ?? 'admin';

  before(async () => {
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

  beforeEach(async () => {
    await browser.get('/');
    await waitUntilDisplayed(navBarPage.entityMenu);
    paymentComponentsPage = new PaymentComponentsPage();
    paymentComponentsPage = await paymentComponentsPage.goToPage(navBarPage);
  });

  it('should load Payments', async () => {
    expect(await paymentComponentsPage.title.getText()).to.match(/Payments/);
    expect(await paymentComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete Payments', async () => {
    const beforeRecordsCount = (await isVisible(paymentComponentsPage.noRecords)) ? 0 : await getRecordsCount(paymentComponentsPage.table);
    paymentUpdatePage = await paymentComponentsPage.goToCreatePayment();
    await paymentUpdatePage.enterData();

    expect(await paymentComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(paymentComponentsPage.table);
    await waitUntilCount(paymentComponentsPage.records, beforeRecordsCount + 1);
    expect(await paymentComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await paymentComponentsPage.deletePayment();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(paymentComponentsPage.records, beforeRecordsCount);
      expect(await paymentComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(paymentComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
