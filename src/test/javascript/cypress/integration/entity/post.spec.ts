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

describe('Post e2e test', () => {
  let startingEntitiesCount = 0;

  before(() => {
    cy.window().then(win => {
      win.sessionStorage.clear();
    });

    cy.clearCookies();
    cy.intercept('GET', '/api/posts*').as('entitiesRequest');
    cy.visit('');
    cy.login('admin', 'admin');
    cy.clickOnEntityMenuItem('post');
    cy.wait('@entitiesRequest').then(({ request, response }) => (startingEntitiesCount = response.body.length));
    cy.visit('/');
  });

  it('should load Posts', () => {
    cy.intercept('GET', '/api/posts*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('post');
    cy.wait('@entitiesRequest');
    cy.getEntityHeading('Post').should('exist');
    if (startingEntitiesCount === 0) {
      cy.get(entityTableSelector).should('not.exist');
    } else {
      cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
    }
    cy.visit('/');
  });

  it('should load details Post page', () => {
    cy.intercept('GET', '/api/posts*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('post');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityDetailsButtonSelector).first().click({ force: true });
      cy.getEntityDetailsHeading('post');
      cy.get(entityDetailsBackButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should load create Post page', () => {
    cy.intercept('GET', '/api/posts*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('post');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('Post');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.visit('/');
  });

  it('should load edit Post page', () => {
    cy.intercept('GET', '/api/posts*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('post');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityEditButtonSelector).first().click({ force: true });
      cy.getEntityCreateUpdateHeading('Post');
      cy.get(entityCreateSaveButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should create an instance of Post', () => {
    cy.intercept('GET', '/api/posts*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('post');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('Post');

    cy.get(`[data-cy="title"]`)
      .type('Garden Salad reciprocal', { force: true })
      .invoke('val')
      .should('match', new RegExp('Garden Salad reciprocal'));

    cy.get(`[data-cy="price"]`).type('75501').should('have.value', '75501');

    cy.get(`[data-cy="salePrice"]`).type('78639').should('have.value', '78639');

    cy.get(`[data-cy="percentSale"]`).type('0').should('have.value', '0');

    cy.get(`[data-cy="imageUrl"]`)
      .type('Division Berkshire Shores', { force: true })
      .invoke('val')
      .should('match', new RegExp('Division Berkshire Shores'));

    cy.get(`[data-cy="scores"]`).type('31').should('have.value', '31');

    cy.get(`[data-cy="simpleContent"]`).type('metrics', { force: true }).invoke('val').should('match', new RegExp('metrics'));

    cy.get(`[data-cy="otherInfo"]`).type('optimizing', { force: true }).invoke('val').should('match', new RegExp('optimizing'));

    cy.get(`[data-cy="content"]`)
      .type('../fake-data/blob/hipster.txt', { force: true })
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.get(`[data-cy="role"]`)
      .type('cross-platform Somoni', { force: true })
      .invoke('val')
      .should('match', new RegExp('cross-platform Somoni'));

    cy.get(`[data-cy="createdBy"]`).type('Libyan', { force: true }).invoke('val').should('match', new RegExp('Libyan'));

    cy.get(`[data-cy="modifiedBy"]`)
      .type('logistical ADP Data', { force: true })
      .invoke('val')
      .should('match', new RegExp('logistical ADP Data'));

    cy.get(`[data-cy="comment"]`)
      .type('robust Nevada Planner', { force: true })
      .invoke('val')
      .should('match', new RegExp('robust Nevada Planner'));

    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.intercept('GET', '/api/posts*').as('entitiesRequestAfterCreate');
    cy.visit('/');
    cy.clickOnEntityMenuItem('post');
    cy.wait('@entitiesRequestAfterCreate');
    cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount + 1);
    cy.visit('/');
  });

  it('should delete last instance of Post', () => {
    cy.intercept('GET', '/api/posts*').as('entitiesRequest');
    cy.intercept('GET', '/api/posts/*').as('dialogDeleteRequest');
    cy.intercept('DELETE', '/api/posts/*').as('deleteEntityRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('post');
    cy.wait('@entitiesRequest').then(({ request, response }) => {
      startingEntitiesCount = response.body.length;
      if (startingEntitiesCount > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('post').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest');
        cy.intercept('GET', '/api/posts*').as('entitiesRequestAfterDelete');
        cy.visit('/');
        cy.clickOnEntityMenuItem('post');
        cy.wait('@entitiesRequestAfterDelete');
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount - 1);
      }
      cy.visit('/');
    });
  });
});
