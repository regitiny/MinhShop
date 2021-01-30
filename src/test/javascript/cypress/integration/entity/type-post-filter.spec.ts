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

describe('TypePostFilter e2e test', () => {
  let startingEntitiesCount = 0;

  before(() => {
    cy.window().then(win => {
      win.sessionStorage.clear();
    });

    cy.clearCookies();
    cy.intercept('GET', '/api/type-post-filters*').as('entitiesRequest');
    cy.visit('');
    cy.login('admin', 'admin');
    cy.clickOnEntityMenuItem('type-post-filter');
    cy.wait('@entitiesRequest').then(({ request, response }) => (startingEntitiesCount = response.body.length));
    cy.visit('/');
  });

  it('should load TypePostFilters', () => {
    cy.intercept('GET', '/api/type-post-filters*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('type-post-filter');
    cy.wait('@entitiesRequest');
    cy.getEntityHeading('TypePostFilter').should('exist');
    if (startingEntitiesCount === 0) {
      cy.get(entityTableSelector).should('not.exist');
    } else {
      cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
    }
    cy.visit('/');
  });

  it('should load details TypePostFilter page', () => {
    cy.intercept('GET', '/api/type-post-filters*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('type-post-filter');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityDetailsButtonSelector).first().click({ force: true });
      cy.getEntityDetailsHeading('typePostFilter');
      cy.get(entityDetailsBackButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should load create TypePostFilter page', () => {
    cy.intercept('GET', '/api/type-post-filters*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('type-post-filter');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('TypePostFilter');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.visit('/');
  });

  it('should load edit TypePostFilter page', () => {
    cy.intercept('GET', '/api/type-post-filters*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('type-post-filter');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityEditButtonSelector).first().click({ force: true });
      cy.getEntityCreateUpdateHeading('TypePostFilter');
      cy.get(entityCreateSaveButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should create an instance of TypePostFilter', () => {
    cy.intercept('GET', '/api/type-post-filters*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('type-post-filter');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('TypePostFilter');

    cy.get(`[data-cy="uuid"]`)
      .type('0b5f947e-76b4-4960-bcfc-a1c2e2611db3')
      .invoke('val')
      .should('match', new RegExp('0b5f947e-76b4-4960-bcfc-a1c2e2611db3'));

    cy.get(`[data-cy="typeFilterName"]`)
      .type('Delaware programming payment', { force: true })
      .invoke('val')
      .should('match', new RegExp('Delaware programming payment'));

    cy.get(`[data-cy="createdDate"]`).type('2021-01-29T21:05').invoke('val').should('equal', '2021-01-29T21:05');

    cy.get(`[data-cy="modifiedDate"]`).type('2021-01-30T10:45').invoke('val').should('equal', '2021-01-30T10:45');

    cy.get(`[data-cy="createdBy"]`).type('green', { force: true }).invoke('val').should('match', new RegExp('green'));

    cy.get(`[data-cy="modifiedBy"]`)
      .type('violet hierarchy Plastic', { force: true })
      .invoke('val')
      .should('match', new RegExp('violet hierarchy Plastic'));

    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.intercept('GET', '/api/type-post-filters*').as('entitiesRequestAfterCreate');
    cy.visit('/');
    cy.clickOnEntityMenuItem('type-post-filter');
    cy.wait('@entitiesRequestAfterCreate');
    cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount + 1);
    cy.visit('/');
  });

  it('should delete last instance of TypePostFilter', () => {
    cy.intercept('GET', '/api/type-post-filters*').as('entitiesRequest');
    cy.intercept('GET', '/api/type-post-filters/*').as('dialogDeleteRequest');
    cy.intercept('DELETE', '/api/type-post-filters/*').as('deleteEntityRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('type-post-filter');
    cy.wait('@entitiesRequest').then(({ request, response }) => {
      startingEntitiesCount = response.body.length;
      if (startingEntitiesCount > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('typePostFilter').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest');
        cy.intercept('GET', '/api/type-post-filters*').as('entitiesRequestAfterDelete');
        cy.visit('/');
        cy.clickOnEntityMenuItem('type-post-filter');
        cy.wait('@entitiesRequestAfterDelete');
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount - 1);
      }
      cy.visit('/');
    });
  });
});
