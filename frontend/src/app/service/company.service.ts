import {Injectable} from '@angular/core';
import {API_URL} from '../config/app.config';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Company} from '../model/company';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private urlCompany = `${API_URL}/company`;

  constructor(public http: HttpClient) {
  }

  getCompanies(): Observable<Array<Company>> {
    return this.http.get<Array<Company>>(`${this.urlCompany}/listCompanies`);
  }
}
