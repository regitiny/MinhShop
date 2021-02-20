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

describe('Payment e2e test', () => {
  let startingEntitiesCount = 0;

  before(() => {
    cy.window().then(win => {
      win.sessionStorage.clear();
    });

    cy.clearCookies();
    cy.intercept('GET', '/api/payments*').as('entitiesRequest');
    cy.visit('');
    cy.login('admin', 'admin');
    cy.clickOnEntityMenuItem('payment');
    cy.wait('@entitiesRequest').then(({ request, response }) => (startingEntitiesCount = response.body.length));
    cy.visit('/');
  });

  it('should load Payments', () => {
    cy.intercept('GET', '/api/payments*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('payment');
    cy.wait('@entitiesRequest');
    cy.getEntityHeading('Payment').should('exist');
    if (startingEntitiesCount === 0) {
      cy.get(entityTableSelector).should('not.exist');
    } else {
      cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
    }
    cy.visit('/');
  });

  it('should load details Payment page', () => {
    cy.intercept('GET', '/api/payments*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('payment');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityDetailsButtonSelector).first().click({ force: true });
      cy.getEntityDetailsHeading('payment');
      cy.get(entityDetailsBackButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should load create Payment page', () => {
    cy.intercept('GET', '/api/payments*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('payment');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('Payment');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.visit('/');
  });

  it('should load edit Payment page', () => {
    cy.intercept('GET', '/api/payments*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('payment');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityEditButtonSelector).first().click({ force: true });
      cy.getEntityCreateUpdateHeading('Payment');
      cy.get(entityCreateSaveButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should create an instance of Payment', () => {
    cy.intercept('GET', '/api/payments*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('payment');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('Payment');

    cy.get(`[data-cy="uuid"]`)
      .type('b9162214-f0d5-4b15-8579-42f034b42a3d')
      .invoke('val')
      .should('match', new RegExp('b9162214-f0d5-4b15-8579-42f034b42a3d'));

    cy.get(`[data-cy="status"]`)
      .type('1080p eco-centric Managed', { force: true })
      .invoke('val')
      .should('match', new RegExp('1080p eco-centric Managed'));

    cy.get(`[data-cy="searchField"]`)
      .type('../fake-data/blob/hipster.txt', { force: true })
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.get(`[data-cy="role"]`).type('back-end', { force: true }).invoke('val').should('match', new RegExp('back-end'));

    cy.get(`[data-cy="createdDate"]`).type('2021-01-30T05:08').invoke('val').should('equal', '2021-01-30T05:08');

    cy.get(`[data-cy="modifiedDate"]`).type('2021-01-30T04:02').invoke('val').should('equal', '2021-01-30T04:02');

    cy.get(`[data-cy="createdBy"]`).type('eyeballs', { force: true }).invoke('val').should('match', new RegExp('eyeballs'));

    cy.get(`[data-cy="modifiedBy"]`)
      .type('calculate Strategist', { force: true })
      .invoke('val')
      .should('match', new RegExp('calculate Strategist'));

    cy.get(`[data-cy="dataSize"]`).type('92747').should('have.value', '92747');

    cy.get(`[data-cy="comment"]`).type('Latvia 1080p', { force: true }).invoke('val').should('match', new RegExp('Latvia 1080p'));

    cy.setFieldSelectToLastOfEntity('billId');

    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.intercept('GET', '/api/payments*').as('entitiesRequestAfterCreate');
    cy.visit('/');
    cy.clickOnEntityMenuItem('payment');
    cy.wait('@entitiesRequestAfterCreate');
    cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount + 1);
    cy.visit('/');
  });

  it('should delete last instance of Payment', () => {
    cy.intercept('GET', '/api/payments*').as('entitiesRequest');
    cy.intercept('GET', '/api/payments/*').as('dialogDeleteRequest');
    cy.intercept('DELETE', '/api/payments/*').as('deleteEntityRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('payment');
    cy.wait('@entitiesRequest').then(({ request, response }) => {
      startingEntitiesCount = response.body.length;
      if (startingEntitiesCount > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('payment').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest');
        cy.intercept('GET', '/api/payments*').as('entitiesRequestAfterDelete');
        cy.visit('/');
        cy.clickOnEntityMenuItem('payment');
        cy.wait('@entitiesRequestAfterDelete');
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount - 1);
      }
      cy.visit('/');
    });
  });
});
