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

describe('TypePostFilter e2e test', () =>
{
  let startingEntitiesCount = 0;

  before(() =>
  {
    cy.window().then(win =>
    {
      win.sessionStorage.clear();
    });

    cy.clearCookies();
    cy.intercept('GET', '/api/type-post-filters*').as('entitiesRequest');
    cy.visit('');
    cy.login('admin', 'admin');
    cy.clickOnEntityMenuItem('type-post-filter');
    cy.wait('@entitiesRequest').then(({request, response}) => (startingEntitiesCount = response.body.length));
    cy.visit('/');
  });

  it('should load TypePostFilters', () =>
  {
    cy.intercept('GET', '/api/type-post-filters*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('type-post-filter');
    cy.wait('@entitiesRequest');
    cy.getEntityHeading('TypePostFilter').should('exist');
    if (startingEntitiesCount === 0)
    {
      cy.get(entityTableSelector).should('not.exist');
    }
    else
    {
      cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
    }
    cy.visit('/');
  });

  it('should load details TypePostFilter page', () =>
  {
    cy.intercept('GET', '/api/type-post-filters*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('type-post-filter');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0)
    {
      cy.get(entityDetailsButtonSelector).first().click({force: true});
      cy.getEntityDetailsHeading('typePostFilter');
      cy.get(entityDetailsBackButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should load create TypePostFilter page', () =>
  {
    cy.intercept('GET', '/api/type-post-filters*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('type-post-filter');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({force: true});
    cy.getEntityCreateUpdateHeading('TypePostFilter');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.visit('/');
  });

  it('should load edit TypePostFilter page', () =>
  {
    cy.intercept('GET', '/api/type-post-filters*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('type-post-filter');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0)
    {
      cy.get(entityEditButtonSelector).first().click({force: true});
      cy.getEntityCreateUpdateHeading('TypePostFilter');
      cy.get(entityCreateSaveButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should create an instance of TypePostFilter', () =>
  {
    cy.intercept('GET', '/api/type-post-filters*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('type-post-filter');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({force: true});
    cy.getEntityCreateUpdateHeading('TypePostFilter');

    cy.get(`[data-cy="uuid"]`)
      .type('0b5f947e-76b4-4960-bcfc-a1c2e2611db3')
      .invoke('val')
      .should('match', new RegExp('0b5f947e-76b4-4960-bcfc-a1c2e2611db3'));

    cy.get(`[data-cy="typeFilterName"]`)
      .type('Delaware programming payment', {force: true})
      .invoke('val')
      .should('match', new RegExp('Delaware programming payment'));

    cy.get(`[data-cy="searchField"]`)
      .type('../fake-data/blob/hipster.txt', {force: true})
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.get(`[data-cy="role"]`)
      .type('Function-based Tuna violet', {force: true})
      .invoke('val')
      .should('match', new RegExp('Function-based Tuna violet'));

    cy.get(`[data-cy="createdDate"]`).type('2021-01-30T08:39').invoke('val').should('equal', '2021-01-30T08:39');

    cy.get(`[data-cy="modifiedDate"]`).type('2021-01-30T05:35').invoke('val').should('equal', '2021-01-30T05:35');

    cy.get(`[data-cy="createdBy"]`)
      .type('Plastic grow bricks-and-clicks', {force: true})
      .invoke('val')
      .should('match', new RegExp('Plastic grow bricks-and-clicks'));

    cy.get(`[data-cy="modifiedBy"]`)
      .type('Computers Intelligent', {force: true})
      .invoke('val')
      .should('match', new RegExp('Computers Intelligent'));

    cy.get(`[data-cy="dataSize"]`).type('83678').should('have.value', '83678');

    cy.get(`[data-cy="comment"]`).type('bus Automated', {force: true}).invoke('val').should('match', new RegExp('bus Automated'));

    cy.get(entityCreateSaveButtonSelector).click({force: true});
    cy.scrollTo('top', {ensureScrollable: false});
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.intercept('GET', '/api/type-post-filters*').as('entitiesRequestAfterCreate');
    cy.visit('/');
    cy.clickOnEntityMenuItem('type-post-filter');
    cy.wait('@entitiesRequestAfterCreate');
    cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount + 1);
    cy.visit('/');
  });

  it('should delete last instance of TypePostFilter', () =>
  {
    cy.intercept('GET', '/api/type-post-filters*').as('entitiesRequest');
    cy.intercept('GET', '/api/type-post-filters/*').as('dialogDeleteRequest');
    cy.intercept('DELETE', '/api/type-post-filters/*').as('deleteEntityRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('type-post-filter');
    cy.wait('@entitiesRequest').then(({request, response}) =>
    {
      startingEntitiesCount = response.body.length;
      if (startingEntitiesCount > 0)
      {
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
        cy.get(entityDeleteButtonSelector).last().click({force: true});
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('typePostFilter').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({force: true});
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
