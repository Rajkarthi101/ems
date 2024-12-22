import { Routes } from '@angular/router';
import { RegistrationComponent } from './registration/registration.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { EditEmployeeComponent } from './edit-employee/edit-employee.component';

export const routes: Routes = [
    {path:'',component:RegistrationComponent},
    {path:'list',component:EmployeeListComponent},
    {path:'edit',component:EditEmployeeComponent}

];
