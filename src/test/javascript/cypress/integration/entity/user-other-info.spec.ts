import {
  entityTableSelector,
  entityDetailsButtonSelector,
  entityDetailsBackButtonSelector,
  entityCreateButtonSelector,
  entityCreateSaveButtonSelector,
  entityEditButtonSelector,
  entityDeleteButtonSelector,
  entityConfirmDeleteButtonSelector,
} from '../../support/entity';

describe('UserOtherInfo e2e test', () => {
  let startingEntitiesCount = 0;

  before(() => {
    cy.window().then(win => {
      win.sessionStorage.clear();
    });

    cy.clearCookies();
    cy.intercept('GET', '/api/user-other-infos*').as('entitiesRequest');
    cy.visit('');
    cy.login('admin', 'admin');
    cy.clickOnEntityMenuItem('user-other-info');
    cy.wait('@entitiesRequest').then(({ request, response }) => (startingEntitiesCount = response.body.length));
    cy.visit('/');
  });

  it('should load UserOtherInfos', () => {
    cy.intercept('GET', '/api/user-other-infos*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('user-other-info');
    cy.wait('@entitiesRequest');
    cy.getEntityHeading('UserOtherInfo').should('exist');
    if (startingEntitiesCount === 0) {
      cy.get(entityTableSelector).should('not.exist');
    } else {
      cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
    }
    cy.visit('/');
  });

  it('should load details UserOtherInfo page', () => {
    cy.intercept('GET', '/api/user-other-infos*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('user-other-info');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityDetailsButtonSelector).first().click({ force: true });
      cy.getEntityDetailsHeading('userOtherInfo');
      cy.get(entityDetailsBackButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should load create UserOtherInfo page', () => {
    cy.intercept('GET', '/api/user-other-infos*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('user-other-info');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('UserOtherInfo');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.visit('/');
  });

  it('should load edit UserOtherInfo page', () => {
    cy.intercept('GET', '/api/user-other-infos*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('user-other-info');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityEditButtonSelector).first().click({ force: true });
      cy.getEntityCreateUpdateHeading('UserOtherInfo');
      cy.get(entityCreateSaveButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should create an instance of UserOtherInfo', () => {
    cy.intercept('GET', '/api/user-other-infos*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('user-other-info');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('UserOtherInfo');

    cy.get(`[data-cy="uuid"]`)
      .type('62566f41-4c96-40be-ae9e-8f09c3d7776b')
      .invoke('val')
      .should('match', new RegExp('62566f41-4c96-40be-ae9e-8f09c3d7776b'));

    cy.get(`[data-cy="email"]`).type('H.Vng@gmail.com', { force: true }).invoke('val').should('match', new RegExp('H.Vng@gmail.com'));

    cy.get(`[data-cy="wardCode"]`).type('expedite', { force: true }).invoke('val').should('match', new RegExp('expedite'));

    cy.get(`[data-cy="distCode"]`)
      .type('overriding aggregate', { force: true })
      .invoke('val')
      .should('match', new RegExp('overriding aggregate'));

    cy.get(`[data-cy="cityCode"]`).type('Texas Row', { force: true }).invoke('val').should('match', new RegExp('Texas Row'));

    cy.get(`[data-cy="addressDetails"]`).type('Chief', { force: true }).invoke('val').should('match', new RegExp('Chief'));

    cy.get(`[data-cy="dateOfBirth"]`).type('2021-01-30').should('have.value', '2021-01-30');

    cy.get(`[data-cy="otherInfo"]`).type('back-end', { force: true }).invoke('val').should('match', new RegExp('back-end'));

    cy.get(`[data-cy="createdDate"]`).type('2021-01-30T07:51').invoke('val').should('equal', '2021-01-30T07:51');

    cy.get(`[data-cy="modifiedDate"]`).type('2021-01-30T00:41').invoke('val').should('equal', '2021-01-30T00:41');

    cy.get(`[data-cy="createdBy"]`).type('Market compress', { force: true }).invoke('val').should('match', new RegExp('Market compress'));

    cy.get(`[data-cy="modifiedBy"]`)
      .type('reserved Cheese Keyboard', { force: true })
      .invoke('val')
      .should('match', new RegExp('reserved Cheese Keyboard'));

    cy.setFieldSelectToLastOfEntity('userName');

    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.intercept('GET', '/api/user-other-infos*').as('entitiesRequestAfterCreate');
    cy.visit('/');
    cy.clickOnEntityMenuItem('user-other-info');
    cy.wait('@entitiesRequestAfterCreate');
    cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount + 1);
    cy.visit('/');
  });

  it('should delete last instance of UserOtherInfo', () => {
    cy.intercept('GET', '/api/user-other-infos*').as('entitiesRequest');
    cy.intercept('GET', '/api/user-other-infos/*').as('dialogDeleteRequest');
    cy.intercept('DELETE', '/api/user-other-infos/*').as('deleteEntityRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('user-other-info');
    cy.wait('@entitiesRequest').then(({ request, response }) => {
      startingEntitiesCount = response.body.length;
      if (startingEntitiesCount > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('userOtherInfo').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest');
        cy.intercept('GET', '/api/user-other-infos*').as('entitiesRequestAfterDelete');
        cy.visit('/');
        cy.clickOnEntityMenuItem('user-other-info');
        cy.wait('@entitiesRequestAfterDelete');
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount - 1);
      }
      cy.visit('/');
    });
  });
});
