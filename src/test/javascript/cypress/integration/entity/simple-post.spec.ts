import {
  entityCreateButtonSelector,
  entityCreateSaveButtonSelector,
  entityDetailsBackButtonSelector,
  entityDetailsButtonSelector,
  entityEditButtonSelector,
  entityTableSelector,
} from '../../support/entity';

describe('SimplePost e2e test', () => {
  let startingEntitiesCount = 0;

  before(() => {
    cy.window().then(win => {
      win.sessionStorage.clear();
    });

    cy.clearCookies();
    cy.intercept('GET', '/api/simple-posts*').as('entitiesRequest');
    cy.visit('');
    cy.login('admin', 'admin');
    cy.clickOnEntityMenuItem('simple-post');
    cy.wait('@entitiesRequest').then(({ request, response }) => (startingEntitiesCount = response.body.length));
    cy.visit('/');
  });

  it('should load SimplePosts', () => {
    cy.intercept('GET', '/api/simple-posts*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('simple-post');
    cy.wait('@entitiesRequest');
    cy.getEntityHeading('SimplePost').should('exist');
    if (startingEntitiesCount === 0) {
      cy.get(entityTableSelector).should('not.exist');
    } else {
      cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
    }
    cy.visit('/');
  });

  it('should load details SimplePost page', () => {
    cy.intercept('GET', '/api/simple-posts*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('simple-post');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityDetailsButtonSelector).first().click({ force: true });
      cy.getEntityDetailsHeading('simplePost');
      cy.get(entityDetailsBackButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should load create SimplePost page', () => {
    cy.intercept('GET', '/api/simple-posts*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('simple-post');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('SimplePost');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.visit('/');
  });

  it('should load edit SimplePost page', () => {
    cy.intercept('GET', '/api/simple-posts*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('simple-post');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityEditButtonSelector).first().click({ force: true });
      cy.getEntityCreateUpdateHeading('SimplePost');
      cy.get(entityCreateSaveButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  /* this test is commented because it contains required relationships
  it('should create an instance of SimplePost', () => {
    cy.intercept('GET', '/api/simple-posts*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('simple-post');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({force: true});
    cy.getEntityCreateUpdateHeading('SimplePost');

    cy.get(`[data-cy="uuid"]`).type('4a86e12a-6e7c-4968-9dc7-3df929cae1f3').invoke('val').should('match', new RegExp('4a86e12a-6e7c-4968-9dc7-3df929cae1f3'));


    cy.get(`[data-cy="title"]`).type('Intelligent', { force: true }).invoke('val').should('match', new RegExp('Intelligent'));


    cy.get(`[data-cy="price"]`).type('56428').should('have.value', '56428');


    cy.get(`[data-cy="salePrice"]`).type('43295').should('have.value', '43295');


    cy.get(`[data-cy="percentSale"]`).type('82').should('have.value', '82');


    cy.get(`[data-cy="imageUrl"]`).type('zero connecting', { force: true }).invoke('val').should('match', new RegExp('zero connecting'));


    cy.get(`[data-cy="scores"]`).type('69').should('have.value', '69');


    cy.get(`[data-cy="simpleContent"]`).type('generating Lucia customized', { force: true }).invoke('val').should('match', new RegExp('generating Lucia customized'));


    cy.get(`[data-cy="otherInfo"]`).type('Borders', { force: true }).invoke('val').should('match', new RegExp('Borders'));


    cy.get(`[data-cy="searchField"]`).type('../fake-data/blob/hipster.txt', { force: true }).invoke('val').should('match', new RegExp('../fake-data/blob/hipster.txt'));


    cy.get(`[data-cy="role"]`).type('Coordinator Chicken', { force: true }).invoke('val').should('match', new RegExp('Coordinator Chicken'));


    cy.get(`[data-cy="createdDate"]`).type('2021-01-30T00:00').invoke('val').should('equal', '2021-01-30T00:00');


    cy.get(`[data-cy="modifiedDate"]`).type('2021-01-30T05:24').invoke('val').should('equal', '2021-01-30T05:24');


    cy.get(`[data-cy="createdBy"]`).type('hack Trail', { force: true }).invoke('val').should('match', new RegExp('hack Trail'));


    cy.get(`[data-cy="modifiedBy"]`).type('foreground array', { force: true }).invoke('val').should('match', new RegExp('foreground array'));


    cy.get(`[data-cy="dataSize"]`).type('39703').should('have.value', '39703');


    cy.get(`[data-cy="comment"]`).type('indexing markets Incredible', { force: true }).invoke('val').should('match', new RegExp('indexing markets Incredible'));

    cy.setFieldSelectToLastOfEntity('postDetails');

    cy.setFieldSelectToLastOfEntity('typePost');

    cy.setFieldSelectToLastOfEntity('typePostFilter');

    cy.get(entityCreateSaveButtonSelector).click({force: true});
    cy.scrollTo('top', {ensureScrollable: false});
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.intercept('GET', '/api/simple-posts*').as('entitiesRequestAfterCreate');
    cy.visit('/');
    cy.clickOnEntityMenuItem('simple-post');
    cy.wait('@entitiesRequestAfterCreate');
    cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount + 1);
    cy.visit('/');
  });
  */

  /* this test is commented because it contains required relationships
  it('should delete last instance of SimplePost', () => {
    cy.intercept('GET', '/api/simple-posts*').as('entitiesRequest');
    cy.intercept('GET', '/api/simple-posts/*').as('dialogDeleteRequest');
    cy.intercept('DELETE', '/api/simple-posts/*').as('deleteEntityRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('simple-post');
    cy.wait('@entitiesRequest').then(({ request, response }) => {
      startingEntitiesCount = response.body.length;
      if (startingEntitiesCount > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
        cy.get(entityDeleteButtonSelector).last().click({force: true});
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('simplePost').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({force: true});
        cy.wait('@deleteEntityRequest');
        cy.intercept('GET', '/api/simple-posts*').as('entitiesRequestAfterDelete');
        cy.visit('/');
        cy.clickOnEntityMenuItem('simple-post');
        cy.wait('@entitiesRequestAfterDelete');
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount - 1);
      }
      cy.visit('/');
    });
  });
  */
});
