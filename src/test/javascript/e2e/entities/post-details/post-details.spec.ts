import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import PostDetailsComponentsPage from './post-details.page-object';
import PostDetailsUpdatePage from './post-details-update.page-object';
import { getRecordsCount, isVisible, waitUntilCount, waitUntilDisplayed } from '../../util/utils';

const expect = chai.expect;

describe('PostDetails e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let postDetailsComponentsPage: PostDetailsComponentsPage;
  let postDetailsUpdatePage: PostDetailsUpdatePage;
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
    postDetailsComponentsPage = new PostDetailsComponentsPage();
    postDetailsComponentsPage = await postDetailsComponentsPage.goToPage(navBarPage);
  });

  it('should load PostDetails', async () => {
    expect(await postDetailsComponentsPage.title.getText()).to.match(/Post Details/);
    expect(await postDetailsComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete PostDetails', async () => {
    const beforeRecordsCount = (await isVisible(postDetailsComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(postDetailsComponentsPage.table);
    postDetailsUpdatePage = await postDetailsComponentsPage.goToCreatePostDetails();
    await postDetailsUpdatePage.enterData();

    expect(await postDetailsComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(postDetailsComponentsPage.table);
    await waitUntilCount(postDetailsComponentsPage.records, beforeRecordsCount + 1);
    expect(await postDetailsComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await postDetailsComponentsPage.deletePostDetails();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(postDetailsComponentsPage.records, beforeRecordsCount);
      expect(await postDetailsComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(postDetailsComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
