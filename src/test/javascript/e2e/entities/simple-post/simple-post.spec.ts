import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import SimplePostComponentsPage from './simple-post.page-object';
import SimplePostUpdatePage from './simple-post-update.page-object';
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

describe('SimplePost e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let simplePostComponentsPage: SimplePostComponentsPage;
  let simplePostUpdatePage: SimplePostUpdatePage;
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
    simplePostComponentsPage = new SimplePostComponentsPage();
    simplePostComponentsPage = await simplePostComponentsPage.goToPage(navBarPage);
  });

  it('should load SimplePosts', async () => {
    expect(await simplePostComponentsPage.title.getText()).to.match(/Simple Posts/);
    expect(await simplePostComponentsPage.createButton.isEnabled()).to.be.true;
  });

  /* it('should create and delete SimplePosts', async () => {
        const beforeRecordsCount = await isVisible(simplePostComponentsPage.noRecords) ? 0 : await getRecordsCount(simplePostComponentsPage.table);
        simplePostUpdatePage = await simplePostComponentsPage.goToCreateSimplePost();
        await simplePostUpdatePage.enterData();

        expect(await simplePostComponentsPage.createButton.isEnabled()).to.be.true;
        await waitUntilDisplayed(simplePostComponentsPage.table);
        await waitUntilCount(simplePostComponentsPage.records, beforeRecordsCount + 1);
        expect(await simplePostComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

        await simplePostComponentsPage.deleteSimplePost();
        if(beforeRecordsCount !== 0) {
          await waitUntilCount(simplePostComponentsPage.records, beforeRecordsCount);
          expect(await simplePostComponentsPage.records.count()).to.eq(beforeRecordsCount);
        } else {
          await waitUntilDisplayed(simplePostComponentsPage.noRecords);
        }
    }); */

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
