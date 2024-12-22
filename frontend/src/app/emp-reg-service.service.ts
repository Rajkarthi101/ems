import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
// import { EmployeeElement } from './employee-element';

@Injectable({ providedIn: 'root' }) 
export class EmpRegServiceService {

  constructor(private http:HttpClient) { }
  editEmpData!:any;

  saveUserDetails(data:any): Observable<any>{
    return this.http.post('http://localhost:8080/ems_live/employee/addEmployee',data);
  }

  updateUserDetails(data:any): Observable<any>{
    console.log(data);
    return this.http.put('http://localhost:8080/ems_live/employee/updatedEmployee',data);
  }

  getEmployeeData(): Observable<any[]>{
    return this.http.get<any[]>('http://localhost:8080/ems_live/employee/getAllEmployee');
  }
  
  deleteEmployee(empId:any): Observable<any>{
    return this.http.delete('http://localhost:8080/ems_live/employee/deleteEmployee?empId='+empId);
  }

  editEmployeeData(empId:any): Observable<any>{
    return this.http.get<any>('http://localhost:8080/ems_live/employee/getEmployee?empId='+empId);
  }

  searchByEmployeeData(optionType:any,searchBy:any): Observable<any>{
    return this.http.get<any>('http://localhost:8080/ems_live/employee/searchByEmployee?optionType='+optionType+'&searchBy='+searchBy);
  }
}
  