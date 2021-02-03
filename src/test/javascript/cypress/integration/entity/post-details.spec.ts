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

describe('PostDetails e2e test', () => {
  let startingEntitiesCount = 0;

  before(() => {
    cy.window().then(win => {
      win.sessionStorage.clear();
    });

    cy.clearCookies();
    cy.intercept('GET', '/api/post-details*').as('entitiesRequest');
    cy.visit('');
    cy.login('admin', 'admin');
    cy.clickOnEntityMenuItem('post-details');
    cy.wait('@entitiesRequest').then(({ request, response }) => (startingEntitiesCount = response.body.length));
    cy.visit('/');
  });

  it('should load PostDetails', () => {
    cy.intercept('GET', '/api/post-details*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('post-details');
    cy.wait('@entitiesRequest');
    cy.getEntityHeading('PostDetails').should('exist');
    if (startingEntitiesCount === 0) {
      cy.get(entityTableSelector).should('not.exist');
    } else {
      cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
    }
    cy.visit('/');
  });

  it('should load details PostDetails page', () => {
    cy.intercept('GET', '/api/post-details*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('post-details');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityDetailsButtonSelector).first().click({ force: true });
      cy.getEntityDetailsHeading('postDetails');
      cy.get(entityDetailsBackButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should load create PostDetails page', () => {
    cy.intercept('GET', '/api/post-details*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('post-details');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('PostDetails');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.visit('/');
  });

  it('should load edit PostDetails page', () => {
    cy.intercept('GET', '/api/post-details*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('post-details');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityEditButtonSelector).first().click({ force: true });
      cy.getEntityCreateUpdateHeading('PostDetails');
      cy.get(entityCreateSaveButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should create an instance of PostDetails', () => {
    cy.intercept('GET', '/api/post-details*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('post-details');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('PostDetails');

    cy.get(`[data-cy="uuid"]`)
      .type('44672049-bcac-4200-a342-3d663a5d12c1')
      .invoke('val')
      .should('match', new RegExp('44672049-bcac-4200-a342-3d663a5d12c1'));

    cy.get(`[data-cy="postDetailsId"]`).type('xsE]YS77', { force: true }).invoke('val').should('match', new RegExp('xsE]YS77'));

    cy.get(`[data-cy="content"]`)
      .type('../fake-data/blob/hipster.txt', { force: true })
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.get(`[data-cy="searchField"]`)
      .type('../fake-data/blob/hipster.txt', { force: true })
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.get(`[data-cy="role"]`).type('Brand', { force: true }).invoke('val').should('match', new RegExp('Brand'));

    cy.get(`[data-cy="createdDate"]`).type('2021-01-30T13:09').invoke('val').should('equal', '2021-01-30T13:09');

    cy.get(`[data-cy="modifiedDate"]`).type('2021-01-29T16:59').invoke('val').should('equal', '2021-01-29T16:59');

    cy.get(`[data-cy="createdBy"]`).type('Credit', { force: true }).invoke('val').should('match', new RegExp('Credit'));

    cy.get(`[data-cy="modifiedBy"]`)
      .type('Hà bluetooth target', { force: true })
      .invoke('val')
      .should('match', new RegExp('Hà bluetooth target'));

    cy.get(`[data-cy="dataSize"]`).type('12733').should('have.value', '12733');

    cy.get(`[data-cy="comment"]`)
      .type('Rubber Cambridgeshire bypass', { force: true })
      .invoke('val')
      .should('match', new RegExp('Rubber Cambridgeshire bypass'));

    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.intercept('GET', '/api/post-details*').as('entitiesRequestAfterCreate');
    cy.visit('/');
    cy.clickOnEntityMenuItem('post-details');
    cy.wait('@entitiesRequestAfterCreate');
    cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount + 1);
    cy.visit('/');
  });

  it('should delete last instance of PostDetails', () => {
    cy.intercept('GET', '/api/post-details*').as('entitiesRequest');
    cy.intercept('GET', '/api/post-details/*').as('dialogDeleteRequest');
    cy.intercept('DELETE', '/api/post-details/*').as('deleteEntityRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('post-details');
    cy.wait('@entitiesRequest').then(({ request, response }) => {
      startingEntitiesCount = response.body.length;
      if (startingEntitiesCount > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('postDetails').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest');
        cy.intercept('GET', '/api/post-details*').as('entitiesRequestAfterDelete');
        cy.visit('/');
        cy.clickOnEntityMenuItem('post-details');
        cy.wait('@entitiesRequestAfterDelete');
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount - 1);
      }
      cy.visit('/');
    });
  });
});
