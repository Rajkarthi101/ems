import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EmpRegServiceService } from '../emp-reg-service.service';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-edit-employee',
  standalone: true,
  imports: [MatInputModule,ReactiveFormsModule,CommonModule],
  templateUrl: './edit-employee.component.html',
  styleUrl: './edit-employee.component.css'
})
export class EditEmployeeComponent implements OnInit{
  constructor(private formBuilder: FormBuilder,private router:Router,private empRegServiceService:EmpRegServiceService) { }

  registrationForm!: FormGroup
  dataObject: any;
  registerData: any;

  ngOnInit(): void {
    console.log(this.empRegServiceService.editEmpData)
    
    if(this.empRegServiceService.editEmpData!=null){
        this.registerData = this.empRegServiceService.editEmpData;
        console.log(this.registerData);

        this.registrationForm = this.formBuilder.group({
        name: new FormControl(this.registerData.empName, [Validators.required]),//,Validators.pattern('[a-zA-Z0-9][@gmail.com]')
        email: new FormControl(this.registerData.empEmail, [Validators.required, Validators.pattern('[a-zA-Z0-9\.]+@gmail.com')]),
        position: new FormControl(this.registerData.empPosition, [Validators.required]),
        salary: new FormControl(this.registerData.empSalary, [Validators.required])
      })
    }
  }

  
  get f() {
    return this.registrationForm.controls;
  }
  handleSubmit() {

    this.dataObject = {
      empName: this.registrationForm.get('name')?.value,
      empEmail: this.registrationForm.get('email')?.value,
      empPosition: this.registrationForm.get('position')?.value,
      empSalary: this.registrationForm.get('salary')?.value,
      empId: this.empRegServiceService.editEmpData.empId
    }


    this.empRegServiceService.updateUserDetails(this.dataObject).subscribe({
      "next": (data) => {
        console.log(data);
        this.registerData = data;
        alert("Record Saved Successfully");
        this.router.navigate(['/list']);
      },
      "error": (error) => {
        alert(error.error.errors);
       }

    });
  }
}
