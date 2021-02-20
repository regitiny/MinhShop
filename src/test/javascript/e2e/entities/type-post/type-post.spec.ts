import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import TypePostComponentsPage from './type-post.page-object';
import TypePostUpdatePage from './type-post-update.page-object';
import { getRecordsCount, isVisible, waitUntilCount, waitUntilDisplayed } from '../../util/utils';

const expect = chai.expect;

describe('TypePost e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let typePostComponentsPage: TypePostComponentsPage;
  let typePostUpdatePage: TypePostUpdatePage;
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
    typePostComponentsPage = new TypePostComponentsPage();
    typePostComponentsPage = await typePostComponentsPage.goToPage(navBarPage);
  });

  it('should load TypePosts', async () => {
    expect(await typePostComponentsPage.title.getText()).to.match(/Type Posts/);
    expect(await typePostComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete TypePosts', async () => {
    const beforeRecordsCount = (await isVisible(typePostComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(typePostComponentsPage.table);
    typePostUpdatePage = await typePostComponentsPage.goToCreateTypePost();
    await typePostUpdatePage.enterData();

    expect(await typePostComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(typePostComponentsPage.table);
    await waitUntilCount(typePostComponentsPage.records, beforeRecordsCount + 1);
    expect(await typePostComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await typePostComponentsPage.deleteTypePost();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(typePostComponentsPage.records, beforeRecordsCount);
      expect(await typePostComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(typePostComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
