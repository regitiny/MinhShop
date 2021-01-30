import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import TypePostFilterComponentsPage from './type-post-filter.page-object';
import TypePostFilterUpdatePage from './type-post-filter-update.page-object';
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

describe('TypePostFilter e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let typePostFilterComponentsPage: TypePostFilterComponentsPage;
  let typePostFilterUpdatePage: TypePostFilterUpdatePage;
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
    typePostFilterComponentsPage = new TypePostFilterComponentsPage();
    typePostFilterComponentsPage = await typePostFilterComponentsPage.goToPage(navBarPage);
  });

  it('should load TypePostFilters', async () => {
    expect(await typePostFilterComponentsPage.title.getText()).to.match(/Type Post Filters/);
    expect(await typePostFilterComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete TypePostFilters', async () => {
    const beforeRecordsCount = (await isVisible(typePostFilterComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(typePostFilterComponentsPage.table);
    typePostFilterUpdatePage = await typePostFilterComponentsPage.goToCreateTypePostFilter();
    await typePostFilterUpdatePage.enterData();

    expect(await typePostFilterComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(typePostFilterComponentsPage.table);
    await waitUntilCount(typePostFilterComponentsPage.records, beforeRecordsCount + 1);
    expect(await typePostFilterComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await typePostFilterComponentsPage.deleteTypePostFilter();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(typePostFilterComponentsPage.records, beforeRecordsCount);
      expect(await typePostFilterComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(typePostFilterComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
