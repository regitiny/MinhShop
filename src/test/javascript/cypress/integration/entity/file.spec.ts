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

describe('File e2e test', () => {
  let startingEntitiesCount = 0;

  before(() => {
    cy.window().then(win => {
      win.sessionStorage.clear();
    });

    cy.clearCookies();
    cy.intercept('GET', '/api/files*').as('entitiesRequest');
    cy.visit('');
    cy.login('admin', 'admin');
    cy.clickOnEntityMenuItem('file');
    cy.wait('@entitiesRequest').then(({ request, response }) => (startingEntitiesCount = response.body.length));
    cy.visit('/');
  });

  it('should load Files', () => {
    cy.intercept('GET', '/api/files*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('file');
    cy.wait('@entitiesRequest');
    cy.getEntityHeading('File').should('exist');
    if (startingEntitiesCount === 0) {
      cy.get(entityTableSelector).should('not.exist');
    } else {
      cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
    }
    cy.visit('/');
  });

  it('should load details File page', () => {
    cy.intercept('GET', '/api/files*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('file');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityDetailsButtonSelector).first().click({ force: true });
      cy.getEntityDetailsHeading('file');
      cy.get(entityDetailsBackButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should load create File page', () => {
    cy.intercept('GET', '/api/files*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('file');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('File');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.visit('/');
  });

  it('should load edit File page', () => {
    cy.intercept('GET', '/api/files*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('file');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0) {
      cy.get(entityEditButtonSelector).first().click({ force: true });
      cy.getEntityCreateUpdateHeading('File');
      cy.get(entityCreateSaveButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should create an instance of File', () => {
    cy.intercept('GET', '/api/files*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('file');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({ force: true });
    cy.getEntityCreateUpdateHeading('File');

    cy.get(`[data-cy="uuid"]`)
      .type('fbb5d871-dd42-4e20-b92a-a095ef01f7c1')
      .invoke('val')
      .should('match', new RegExp('fbb5d871-dd42-4e20-b92a-a095ef01f7c1'));

    cy.setFieldImageAsBytesOfEntity('videoData', 'integration-test.png', 'image/png');

    cy.get(`[data-cy="nameVideo"]`)
      .type('Brand Kenya generation', { force: true })
      .invoke('val')
      .should('match', new RegExp('Brand Kenya generation'));

    cy.get(`[data-cy="extension"]`).type('Solutions', { force: true }).invoke('val').should('match', new RegExp('Solutions'));

    cy.get(`[data-cy="typeFile"]`)
      .type('web functionalities', { force: true })
      .invoke('val')
      .should('match', new RegExp('web functionalities'));

    cy.get(`[data-cy="searchField"]`)
      .type('../fake-data/blob/hipster.txt', { force: true })
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.get(`[data-cy="role"]`)
      .type('Synergized Gloves Bacon', { force: true })
      .invoke('val')
      .should('match', new RegExp('Synergized Gloves Bacon'));

    cy.get(`[data-cy="createdDate"]`).type('2021-01-30T12:50').invoke('val').should('equal', '2021-01-30T12:50');

    cy.get(`[data-cy="modifiedDate"]`).type('2021-01-29T23:37').invoke('val').should('equal', '2021-01-29T23:37');

    cy.get(`[data-cy="createdBy"]`).type('SAS Vermont', { force: true }).invoke('val').should('match', new RegExp('SAS Vermont'));

    cy.get(`[data-cy="modifiedBy"]`)
      .type('connecting generate', { force: true })
      .invoke('val')
      .should('match', new RegExp('connecting generate'));

    cy.get(`[data-cy="dataSize"]`).type('47449').should('have.value', '47449');

    cy.get(`[data-cy="comment"]`)
      .type('virtual Paradigm Tools', { force: true })
      .invoke('val')
      .should('match', new RegExp('virtual Paradigm Tools'));

    cy.get(entityCreateSaveButtonSelector).click({ force: true });
    cy.scrollTo('top', { ensureScrollable: false });
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.intercept('GET', '/api/files*').as('entitiesRequestAfterCreate');
    cy.visit('/');
    cy.clickOnEntityMenuItem('file');
    cy.wait('@entitiesRequestAfterCreate');
    cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount + 1);
    cy.visit('/');
  });

  it('should delete last instance of File', () => {
    cy.intercept('GET', '/api/files*').as('entitiesRequest');
    cy.intercept('GET', '/api/files/*').as('dialogDeleteRequest');
    cy.intercept('DELETE', '/api/files/*').as('deleteEntityRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('file');
    cy.wait('@entitiesRequest').then(({ request, response }) => {
      startingEntitiesCount = response.body.length;
      if (startingEntitiesCount > 0) {
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
        cy.get(entityDeleteButtonSelector).last().click({ force: true });
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('file').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({ force: true });
        cy.wait('@deleteEntityRequest');
        cy.intercept('GET', '/api/files*').as('entitiesRequestAfterDelete');
        cy.visit('/');
        cy.clickOnEntityMenuItem('file');
        cy.wait('@entitiesRequestAfterDelete');
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount - 1);
      }
      cy.visit('/');
    });
  });
});
