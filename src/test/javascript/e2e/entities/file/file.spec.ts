import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import FileComponentsPage from './file.page-object';
import FileUpdatePage from './file-update.page-object';
import {
  waitUntilDisplayed,
  waitUntilAnyDisplayed,
  click,
  getRecordsCount,
  waitUntilHidden,
  waitUntilCount,
  isVisible,
} from '../../util/utils';
import path from 'path';

const expect = chai.expect;

describe('File e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let fileComponentsPage: FileComponentsPage;
  let fileUpdatePage: FileUpdatePage;
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
    fileComponentsPage = new FileComponentsPage();
    fileComponentsPage = await fileComponentsPage.goToPage(navBarPage);
  });

  it('should load Files', async () => {
    expect(await fileComponentsPage.title.getText()).to.match(/Files/);
    expect(await fileComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete Files', async () => {
    const beforeRecordsCount = (await isVisible(fileComponentsPage.noRecords)) ? 0 : await getRecordsCount(fileComponentsPage.table);
    fileUpdatePage = await fileComponentsPage.goToCreateFile();
    await fileUpdatePage.enterData();

    expect(await fileComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(fileComponentsPage.table);
    await waitUntilCount(fileComponentsPage.records, beforeRecordsCount + 1);
    expect(await fileComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await fileComponentsPage.deleteFile();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(fileComponentsPage.records, beforeRecordsCount);
      expect(await fileComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(fileComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
