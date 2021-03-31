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

describe('Image e2e test', () =>
{
  let startingEntitiesCount = 0;

  before(() =>
  {
    cy.window().then(win =>
    {
      win.sessionStorage.clear();
    });

    cy.clearCookies();
    cy.intercept('GET', '/api/images*').as('entitiesRequest');
    cy.visit('');
    cy.login('admin', 'admin');
    cy.clickOnEntityMenuItem('image');
    cy.wait('@entitiesRequest').then(({request, response}) => (startingEntitiesCount = response.body.length));
    cy.visit('/');
  });

  it('should load Images', () =>
  {
    cy.intercept('GET', '/api/images*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('image');
    cy.wait('@entitiesRequest');
    cy.getEntityHeading('Image').should('exist');
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

  it('should load details Image page', () =>
  {
    cy.intercept('GET', '/api/images*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('image');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0)
    {
      cy.get(entityDetailsButtonSelector).first().click({force: true});
      cy.getEntityDetailsHeading('image');
      cy.get(entityDetailsBackButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should load create Image page', () =>
  {
    cy.intercept('GET', '/api/images*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('image');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({force: true});
    cy.getEntityCreateUpdateHeading('Image');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.visit('/');
  });

  it('should load edit Image page', () =>
  {
    cy.intercept('GET', '/api/images*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('image');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0)
    {
      cy.get(entityEditButtonSelector).first().click({force: true});
      cy.getEntityCreateUpdateHeading('Image');
      cy.get(entityCreateSaveButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should create an instance of Image', () =>
  {
    cy.intercept('GET', '/api/images*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('image');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({force: true});
    cy.getEntityCreateUpdateHeading('Image');

    cy.get(`[data-cy="uuid"]`)
      .type('52b4c325-0a33-4177-a95f-157a96226d32')
      .invoke('val')
      .should('match', new RegExp('52b4c325-0a33-4177-a95f-157a96226d32'));

    cy.setFieldImageAsBytesOfEntity('imageData', 'integration-test.png', 'image/png');

    cy.get(`[data-cy="nameImage"]`)
      .type('compress Turkish systems', {force: true})
      .invoke('val')
      .should('match', new RegExp('compress Turkish systems'));

    cy.get(`[data-cy="extension"]`).type('Bacon', {force: true}).invoke('val').should('match', new RegExp('Bacon'));

    cy.get(`[data-cy="typeFile"]`).type('Synergized', {force: true}).invoke('val').should('match', new RegExp('Synergized'));

    cy.get(`[data-cy="searchField"]`)
      .type('../fake-data/blob/hipster.txt', {force: true})
      .invoke('val')
      .should('match', new RegExp('../fake-data/blob/hipster.txt'));

    cy.get(`[data-cy="role"]`).type('Trafficway', {force: true}).invoke('val').should('match', new RegExp('Trafficway'));

    cy.get(`[data-cy="createdDate"]`).type('2021-01-30T08:39').invoke('val').should('equal', '2021-01-30T08:39');

    cy.get(`[data-cy="modifiedDate"]`).type('2021-01-30T07:55').invoke('val').should('equal', '2021-01-30T07:55');

    cy.get(`[data-cy="createdBy"]`).type('Metal', {force: true}).invoke('val').should('match', new RegExp('Metal'));

    cy.get(`[data-cy="modifiedBy"]`)
      .type('Beauty quantify recontextualize', {force: true})
      .invoke('val')
      .should('match', new RegExp('Beauty quantify recontextualize'));

    cy.get(`[data-cy="dataSize"]`).type('17219').should('have.value', '17219');

    cy.get(`[data-cy="comment"]`)
      .type('Small Intelligent heuristic', {force: true})
      .invoke('val')
      .should('match', new RegExp('Small Intelligent heuristic'));

    cy.get(entityCreateSaveButtonSelector).click({force: true});
    cy.scrollTo('top', {ensureScrollable: false});
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.intercept('GET', '/api/images*').as('entitiesRequestAfterCreate');
    cy.visit('/');
    cy.clickOnEntityMenuItem('image');
    cy.wait('@entitiesRequestAfterCreate');
    cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount + 1);
    cy.visit('/');
  });

  it('should delete last instance of Image', () =>
  {
    cy.intercept('GET', '/api/images*').as('entitiesRequest');
    cy.intercept('GET', '/api/images/*').as('dialogDeleteRequest');
    cy.intercept('DELETE', '/api/images/*').as('deleteEntityRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('image');
    cy.wait('@entitiesRequest').then(({request, response}) =>
    {
      startingEntitiesCount = response.body.length;
      if (startingEntitiesCount > 0)
      {
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
        cy.get(entityDeleteButtonSelector).last().click({force: true});
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('image').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({force: true});
        cy.wait('@deleteEntityRequest');
        cy.intercept('GET', '/api/images*').as('entitiesRequestAfterDelete');
        cy.visit('/');
        cy.clickOnEntityMenuItem('image');
        cy.wait('@entitiesRequestAfterDelete');
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount - 1);
      }
      cy.visit('/');
    });
  });
});
