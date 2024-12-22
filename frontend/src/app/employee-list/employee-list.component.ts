import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import { Router } from '@angular/router';
import { EmpRegServiceService } from '../emp-reg-service.service';
// import { EmployeeElement } from '../employee-element';
import {MatIconModule} from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { MatSelectModule } from '@angular/material/select';
// import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [MatSelectModule,MatTableModule, MatPaginatorModule, MatIconModule, MatButtonModule,CommonModule,MatInputModule,MatFormFieldModule, FormsModule],
  templateUrl: './employee-list.component.html',
  styleUrl: './employee-list.component.css'
})
export class EmployeeListComponent implements AfterViewInit,OnInit{
  constructor(private router:Router,private empRegServiceService:EmpRegServiceService) { }
  optionType:string='';
  searchBy:string='';
  onSearch(){
    console.log('option:'+this.optionType);
    console.log('searchBy:'+this.searchBy);

    if(this.optionType!='' && this.searchBy!=''){
      this.empRegServiceService.searchByEmployeeData(this.optionType,this.searchBy).subscribe({
        "next": (data) => {
          this.registeredData = data;
          this.dataSource = new MatTableDataSource<any>(this.registeredData);
        },
        "error": (error) => { console.log(error); }
      });
    }
  }

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  ngAfterViewInit(): void {
    throw new Error('Method not implemented.');
  }

  displayedColumns: string[] = ['empName','empEmail','empPosition',  'empSalary', 'edit','delete'];
  registeredData!:any[];
  dataSource!:MatTableDataSource<any>;

  ngOnInit(): void {
    this.empRegServiceService.getEmployeeData().subscribe({
      "next": (data) => {
        this.registeredData = data;
        this.dataSource = new MatTableDataSource<any>(this.registeredData);
      },
      "error": (error) => { console.log(error); }
    });
  }

  onDelete(empId:string){
    alert(empId);
    this.empRegServiceService.deleteEmployee(empId).subscribe({
      "next": (data) => {
        alert("Record has been deleted Successfully");
        this.ngOnInit();
      },
      "error": (error) => { console.log(error); }
    });
  }

  
  onEdit(empId:string){
    this.empRegServiceService.editEmployeeData(empId).subscribe({
      "next": (data) => {
        this.empRegServiceService.editEmpData=data;
      console.log("data::"+data)      
      this.router.navigate(['/edit']);
    },
      "error": (error) => { console.log(error); }
    });
  }
}
