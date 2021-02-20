import { browser } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import UserOtherInfoComponentsPage from './user-other-info.page-object';
import UserOtherInfoUpdatePage from './user-other-info-update.page-object';
import { getRecordsCount, isVisible, waitUntilCount, waitUntilDisplayed } from '../../util/utils';

const expect = chai.expect;

describe('UserOtherInfo e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let userOtherInfoComponentsPage: UserOtherInfoComponentsPage;
  let userOtherInfoUpdatePage: UserOtherInfoUpdatePage;
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
    userOtherInfoComponentsPage = new UserOtherInfoComponentsPage();
    userOtherInfoComponentsPage = await userOtherInfoComponentsPage.goToPage(navBarPage);
  });

  it('should load UserOtherInfos', async () => {
    expect(await userOtherInfoComponentsPage.title.getText()).to.match(/User Other Infos/);
    expect(await userOtherInfoComponentsPage.createButton.isEnabled()).to.be.true;
  });

  it('should create and delete UserOtherInfos', async () => {
    const beforeRecordsCount = (await isVisible(userOtherInfoComponentsPage.noRecords))
      ? 0
      : await getRecordsCount(userOtherInfoComponentsPage.table);
    userOtherInfoUpdatePage = await userOtherInfoComponentsPage.goToCreateUserOtherInfo();
    await userOtherInfoUpdatePage.enterData();

    expect(await userOtherInfoComponentsPage.createButton.isEnabled()).to.be.true;
    await waitUntilDisplayed(userOtherInfoComponentsPage.table);
    await waitUntilCount(userOtherInfoComponentsPage.records, beforeRecordsCount + 1);
    expect(await userOtherInfoComponentsPage.records.count()).to.eq(beforeRecordsCount + 1);

    await userOtherInfoComponentsPage.deleteUserOtherInfo();
    if (beforeRecordsCount !== 0) {
      await waitUntilCount(userOtherInfoComponentsPage.records, beforeRecordsCount);
      expect(await userOtherInfoComponentsPage.records.count()).to.eq(beforeRecordsCount);
    } else {
      await waitUntilDisplayed(userOtherInfoComponentsPage.noRecords);
    }
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
