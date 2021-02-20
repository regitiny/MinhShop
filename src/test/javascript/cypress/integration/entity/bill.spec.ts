import {
  entityConfirmDeleteButtonSelector,
  entityCreateButtonSelector,
  entityCreateSaveButtonSelector,
  entityDeleteButtonSelector,
  entityDetailsBackButtonSelector,
  entityDetailsButtonSelector,
  entityEditButtonSelector,
  entityTableSelector,
} from '../../support/entity';

describe('Bill e2e test', () => {
  let startingEntitiesCount = 0;

  before(() => {
    cy.window().then(win => {
      win.sessionStorage.clear();
    });

    cy.clearCookies();
    cy.intercept('GET', '/api/bills*').as('entitiesRequest');
    cy.visit('');
    cy.login('admin', 'admin');
    cy.clickOnEntityMenuItem('bill');
    cy.wait('@entitiesRequest').then(({ request, response }) => (startingEntitiesCount = response.body.length));
    cy.visit('/');
  });

  it('should load Bills', () => {
    cy.intercept('GET', '/api/bills*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('bill');
    cy.wait('@entitiesRequest');
    cy.getEntityHeading('Bill').should('exist');
    if (startingEntitiesCount === 0) {
      cy.get(entityTableSelector).should('not.exist');
    } else {
      cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
    }
    cy.visit('/');
  });

  it('should load details Bill page', () => {
    cy.intercept('GET', '/api/bills*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('bill');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityDetailsButtonSelector).first().click({ force: true });
      cy.getEntityDetailsHeading('bill');
      cy.get(entityDetailsBackButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should load create Bill page', () => {
    cy.intercept('GET', '/api/bills*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('bill');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('Bill');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.visit('/');
  });

  it('should load edit Bill page', () => {
    cy.intercept('GET', '/api/bills*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('bill');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityEditButtonSelector).first().click({ force: true });
      cy.getEntityCreateUpdateHeading('Bill');
      cy.get(entityCreateSaveButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should create an instance of Bill', () => {
    cy.intercept('GET', '/api/bills*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('bill');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('Bill');

    cy.get(`[data-cy="uuid"]`)
      .type('79401471-d04e-41d2-9ead-cee673f19079')
      .invoke('val')
      .should('match', new RegExp('79401471-d04e-41d2-9ead-cee673f19079'));

    cy.get(`[data-cy="billId"]`).type('Rubber program', { force: true }).invoke('val').should('match', new RegExp('Rubber program'));

    cy.get(`[data-cy="email"]`).type('T_Tng39@gmail.com', { force: true }).invoke('val').should('match', new RegExp('T_Tng39@gmail.com'));

    cy.get(`[data-cy="addressDetails"]`).type('time-frame', { force: true }).invoke('val').should('match', new RegExp('time-frame'));

    cy.get(`[data-cy="addressCode"]`).type('Decentralized', { force: true }).invoke('val').should('match', new RegExp('Decentralized'));

    cy.get(`[data-cy="product"]`)
      .type('plum archive Gardens', { force: true })
      .invoke('val')
      .should('match', new RegExp('plum archive Gardens'));

    cy.get(`[data-cy="comment"]`).type('wireless', { force: true }).invoke('val').should('match', new RegExp('wireless'));

    cy.get(`[data-cy="searchField"]`)
      .type('../fake-data/blob/hipster.txt', { force: true })
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.get(`[data-cy="role"]`).type('COM', { force: true }).invoke('val').should('match', new RegExp('COM'));

    cy.get(`[data-cy="createdDate"]`).type('2021-01-29T22:21').invoke('val').should('equal', '2021-01-29T22:21');

    cy.get(`[data-cy="modifiedDate"]`).type('2021-01-30T06:21').invoke('val').should('equal', '2021-01-30T06:21');

    cy.get(`[data-cy="createdBy"]`)
      .type('Future Fantastic clear-thinking', { force: true })
      .invoke('val')
      .should('match', new RegExp('Future Fantastic clear-thinking'));

    cy.get(`[data-cy="modifiedBy"]`).type('panel', { force: true }).invoke('val').should('match', new RegExp('panel'));

    cy.get(`[data-cy="dataSize"]`).type('50368').should('have.value', '50368');

    cy.setFieldSelectToLastOfEntity('userOtherInfo');

    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.intercept('GET', '/api/bills*').as('entitiesRequestAfterCreate');
    cy.visit('/');
    cy.clickOnEntityMenuItem('bill');
    cy.wait('@entitiesRequestAfterCreate');
    cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount + 1);
    cy.visit('/');
  });

  it('should delete last instance of Bill', () => {
    cy.intercept('GET', '/api/bills*').as('entitiesRequest');
    cy.intercept('GET', '/api/bills/*').as('dialogDeleteRequest');
    cy.intercept('DELETE', '/api/bills/*').as('deleteEntityRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('bill');
    cy.wait('@entitiesRequest').then(({ request, response }) => {
      startingEntitiesCount = response.body.length;
      if (startingEntitiesCount > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('bill').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest');
        cy.intercept('GET', '/api/bills*').as('entitiesRequestAfterDelete');
        cy.visit('/');
        cy.clickOnEntityMenuItem('bill');
        cy.wait('@entitiesRequestAfterDelete');
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount - 1);
      }
      cy.visit('/');
    });
  });
});
