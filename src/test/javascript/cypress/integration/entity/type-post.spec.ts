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

describe('TypePost e2e test', () => {
  let startingEntitiesCount = 0;

  before(() => {
    cy.window().then(win => {
      win.sessionStorage.clear();
    });

    cy.clearCookies();
    cy.intercept('GET', '/api/type-posts*').as('entitiesRequest');
    cy.visit('');
    cy.login('admin', 'admin');
    cy.clickOnEntityMenuItem('type-post');
    cy.wait('@entitiesRequest').then(({ request, response }) => (startingEntitiesCount = response.body.length));
    cy.visit('/');
  });

  it('should load TypePosts', () => {
    cy.intercept('GET', '/api/type-posts*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('type-post');
    cy.wait('@entitiesRequest');
    cy.getEntityHeading('TypePost').should('exist');
    if (startingEntitiesCount === 0) {
      cy.get(entityTableSelector).should('not.exist');
    } else {
      cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
    }
    cy.visit('/');
  });

  it('should load details TypePost page', () => {
    cy.intercept('GET', '/api/type-posts*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('type-post');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityDetailsButtonSelector).first().click({ force: true });
      cy.getEntityDetailsHeading('typePost');
      cy.get(entityDetailsBackButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should load create TypePost page', () => {
    cy.intercept('GET', '/api/type-posts*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('type-post');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('TypePost');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.visit('/');
  });

  it('should load edit TypePost page', () => {
    cy.intercept('GET', '/api/type-posts*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('type-post');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityEditButtonSelector).first().click({ force: true });
      cy.getEntityCreateUpdateHeading('TypePost');
      cy.get(entityCreateSaveButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should create an instance of TypePost', () => {
    cy.intercept('GET', '/api/type-posts*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('type-post');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('TypePost');

    cy.get(`[data-cy="uuid"]`)
      .type('a398f59a-0c3d-4e69-865f-0a36c5d553e1')
      .invoke('val')
      .should('match', new RegExp('a398f59a-0c3d-4e69-865f-0a36c5d553e1'));

    cy.get(`[data-cy="typeName"]`)
      .type('web-enabled Burundi', { force: true })
      .invoke('val')
      .should('match', new RegExp('web-enabled Burundi'));

    cy.get(`[data-cy="role"]`).type('Towels copying', { force: true }).invoke('val').should('match', new RegExp('Towels copying'));

    cy.get(`[data-cy="createdDate"]`).type('2021-01-30T01:47').invoke('val').should('equal', '2021-01-30T01:47');

    cy.get(`[data-cy="modifiedDate"]`).type('2021-01-30T10:12').invoke('val').should('equal', '2021-01-30T10:12');

    cy.get(`[data-cy="createdBy"]`)
      .type('indexing blue ivory', { force: true })
      .invoke('val')
      .should('match', new RegExp('indexing blue ivory'));

    cy.get(`[data-cy="modifiedBy"]`).type('Markets Tasty', { force: true }).invoke('val').should('match', new RegExp('Markets Tasty'));

    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.intercept('GET', '/api/type-posts*').as('entitiesRequestAfterCreate');
    cy.visit('/');
    cy.clickOnEntityMenuItem('type-post');
    cy.wait('@entitiesRequestAfterCreate');
    cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount + 1);
    cy.visit('/');
  });

  it('should delete last instance of TypePost', () => {
    cy.intercept('GET', '/api/type-posts*').as('entitiesRequest');
    cy.intercept('GET', '/api/type-posts/*').as('dialogDeleteRequest');
    cy.intercept('DELETE', '/api/type-posts/*').as('deleteEntityRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('type-post');
    cy.wait('@entitiesRequest').then(({ request, response }) => {
      startingEntitiesCount = response.body.length;
      if (startingEntitiesCount > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('typePost').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest');
        cy.intercept('GET', '/api/type-posts*').as('entitiesRequestAfterDelete');
        cy.visit('/');
        cy.clickOnEntityMenuItem('type-post');
        cy.wait('@entitiesRequestAfterDelete');
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount - 1);
      }
      cy.visit('/');
    });
  });
});
