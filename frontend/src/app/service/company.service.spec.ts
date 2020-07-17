import {getTestBed, TestBed} from '@angular/core/testing';

import {CompanyService} from './company.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('CompanyService', () => {

  let injector: any;
  let service: CompanyService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [CompanyService]
    });

    injector = getTestBed();
    service = injector.get(CompanyService);
  });

  it('should be created the service', () => {
    expect(service).toBeTruthy();
  });

  it('should invoke getCompanies', () => {
    const spy = spyOn(service.http, 'get');
    service.getCompanies();
    expect(spy).toHaveBeenCalled();
  });

});
