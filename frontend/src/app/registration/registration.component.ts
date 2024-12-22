import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {  FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatInputModule } from '@angular/material/input'
import { ReactiveFormsModule } from '@angular/forms'
import { Router } from '@angular/router';
import { EmpRegServiceService } from '../emp-reg-service.service';

@Component({
  selector: 'app-registration',
  standalone: true,
  imports: [MatInputModule,ReactiveFormsModule,CommonModule],
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css'
})
export class RegistrationComponent implements OnInit {
  constructor(private formBuilder: FormBuilder,private router:Router,private empRegServiceService:EmpRegServiceService) { }

  registrationForm!: FormGroup
  dataObject: any;
  registerData: any;

  ngOnInit(): void {
    this.registrationForm = this.formBuilder.group({
      name: new FormControl('', [Validators.required]),//,Validators.pattern('[a-zA-Z0-9][@gmail.com]')
      email: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z0-9\.]+@gmail.com')]),
      position: new FormControl('', [Validators.required]),
      salary: new FormControl('', [Validators.required])
    })
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
    }


    this.empRegServiceService.saveUserDetails(this.dataObject).subscribe({
      "next": (data) => {
        console.log(data);
        this.registerData = data;
        alert("Record Saved Successfully");
        this.router.navigate(['/list']);
      },
      "error": (error) => {
        
        console.log(error);
        alert(error.error.errors);
       }

    });
  }
}
