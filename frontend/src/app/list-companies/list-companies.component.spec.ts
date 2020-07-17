import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ListCompaniesComponent} from './list-companies.component';
import {MatTableModule} from '@angular/material/table';
import {CompanyService} from '../service/company.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA} from '@angular/core';
import {Company} from '../model/company';
import {of} from 'rxjs';

describe('ListCompaniesComponent', () => {
  let component: ListCompaniesComponent;
  let fixture: ComponentFixture<ListCompaniesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [MatTableModule, HttpClientTestingModule],
      declarations: [ListCompaniesComponent],
      providers: [CompanyService],
      schemas: [CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListCompaniesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component ListCompanies', () => {
    expect(component).toBeTruthy();
  });

  it('should invoke getCompaniesInService', () => {
    const companies: Array<Company> = [{name: 'AMAZON', segment: 'cloud computing', deviation: 26.89}];
    const spy = spyOn(component.companyService, 'getCompanies')
      .and.returnValue(of(companies));
    component.getCompaniesInService();
    expect(spy).toHaveBeenCalled();
  });

});
