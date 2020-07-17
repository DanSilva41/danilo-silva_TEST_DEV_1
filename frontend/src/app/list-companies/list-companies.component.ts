import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {CompanyService} from '../service/company.service';
import {Company} from '../model/company';

@Component({
  selector: 'app-list-companies',
  templateUrl: './list-companies.component.html',
  styleUrls: ['./list-companies.component.scss']
})
export class ListCompaniesComponent {

  displayedColumns: string[] = ['name', 'segment', 'deviation'];
  dataSource: any;

  constructor(public companyService: CompanyService) {
  }

  getCompaniesInService() {
    this.companyService.getCompanies().subscribe((companies: Array<Company>) => {
      this.dataSource = new MatTableDataSource(companies);
    });
  }

}
