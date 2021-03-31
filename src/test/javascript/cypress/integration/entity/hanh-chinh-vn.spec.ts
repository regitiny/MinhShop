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

describe('HanhChinhVN e2e test', () =>
{
  let startingEntitiesCount = 0;

  before(() =>
  {
    cy.window().then(win =>
    {
      win.sessionStorage.clear();
    });

    cy.clearCookies();
    cy.intercept('GET', '/api/hanh-chinh-vns*').as('entitiesRequest');
    cy.visit('');
    cy.login('admin', 'admin');
    cy.clickOnEntityMenuItem('hanh-chinh-vn');
    cy.wait('@entitiesRequest').then(({request, response}) => (startingEntitiesCount = response.body.length));
    cy.visit('/');
  });

  it('should load HanhChinhVNS', () =>
  {
    cy.intercept('GET', '/api/hanh-chinh-vns*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('hanh-chinh-vn');
    cy.wait('@entitiesRequest');
    cy.getEntityHeading('HanhChinhVN').should('exist');
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

  it('should load details HanhChinhVN page', () =>
  {
    cy.intercept('GET', '/api/hanh-chinh-vns*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('hanh-chinh-vn');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0)
    {
      cy.get(entityDetailsButtonSelector).first().click({force: true});
      cy.getEntityDetailsHeading('hanhChinhVN');
      cy.get(entityDetailsBackButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should load create HanhChinhVN page', () =>
  {
    cy.intercept('GET', '/api/hanh-chinh-vns*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('hanh-chinh-vn');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({force: true});
    cy.getEntityCreateUpdateHeading('HanhChinhVN');
    cy.get(entityCreateSaveButtonSelector).should('exist');
    cy.visit('/');
  });

  it('should load edit HanhChinhVN page', () =>
  {
    cy.intercept('GET', '/api/hanh-chinh-vns*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('hanh-chinh-vn');
    cy.wait('@entitiesRequest');
    if (startingEntitiesCount > 0)
    {
      cy.get(entityEditButtonSelector).first().click({force: true});
      cy.getEntityCreateUpdateHeading('HanhChinhVN');
      cy.get(entityCreateSaveButtonSelector).should('exist');
    }
    cy.visit('/');
  });

  it('should create an instance of HanhChinhVN', () =>
  {
    cy.intercept('GET', '/api/hanh-chinh-vns*').as('entitiesRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('hanh-chinh-vn');
    cy.wait('@entitiesRequest');
    cy.get(entityCreateButtonSelector).click({force: true});
    cy.getEntityCreateUpdateHeading('HanhChinhVN');

    cy.get(`[data-cy="name"]`)
      .type('architectures array auxiliary', {force: true})
      .invoke('val')
      .should('match', new RegExp('architectures array auxiliary'));

    cy.get(`[data-cy="slug"]`).type('FTP', {force: true}).invoke('val').should('match', new RegExp('FTP'));

    cy.get(`[data-cy="type"]`).type('Som Metrics', {force: true}).invoke('val').should('match', new RegExp('Som Metrics'));

    cy.get(`[data-cy="nameWithType"]`)
      .type('generate dot-com Pound', {force: true})
      .invoke('val')
      .should('match', new RegExp('generate dot-com Pound'));

    cy.get(`[data-cy="code"]`).type('calculate', {force: true}).invoke('val').should('match', new RegExp('calculate'));

    cy.get(`[data-cy="parentCode"]`).type('Wooden Account', {force: true}).invoke('val').should('match', new RegExp('Wooden Account'));

    cy.get(`[data-cy="path"]`).type('parsing', {force: true}).invoke('val').should('match', new RegExp('parsing'));

    cy.get(`[data-cy="pathWithType"]`).type('Function-based', {force: true}).invoke('val').should('match', new RegExp('Function-based'));

    cy.get(entityCreateSaveButtonSelector).click({force: true});
    cy.scrollTo('top', {ensureScrollable: false});
    cy.get(entityCreateSaveButtonSelector).should('not.exist');
    cy.intercept('GET', '/api/hanh-chinh-vns*').as('entitiesRequestAfterCreate');
    cy.visit('/');
    cy.clickOnEntityMenuItem('hanh-chinh-vn');
    cy.wait('@entitiesRequestAfterCreate');
    cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount + 1);
    cy.visit('/');
  });

  it('should delete last instance of HanhChinhVN', () =>
  {
    cy.intercept('GET', '/api/hanh-chinh-vns*').as('entitiesRequest');
    cy.intercept('GET', '/api/hanh-chinh-vns/*').as('dialogDeleteRequest');
    cy.intercept('DELETE', '/api/hanh-chinh-vns/*').as('deleteEntityRequest');
    cy.visit('/');
    cy.clickOnEntityMenuItem('hanh-chinh-vn');
    cy.wait('@entitiesRequest').then(({request, response}) =>
    {
      startingEntitiesCount = response.body.length;
      if (startingEntitiesCount > 0)
      {
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount);
        cy.get(entityDeleteButtonSelector).last().click({force: true});
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('hanhChinhVN').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click({force: true});
        cy.wait('@deleteEntityRequest');
        cy.intercept('GET', '/api/hanh-chinh-vns*').as('entitiesRequestAfterDelete');
        cy.visit('/');
        cy.clickOnEntityMenuItem('hanh-chinh-vn');
        cy.wait('@entitiesRequestAfterDelete');
        cy.get(entityTableSelector).should('have.lengthOf', startingEntitiesCount - 1);
      }
      cy.visit('/');
    });
  });
});
