import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../services/employee.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-update-employee',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './update-employee.component.html',
  styleUrl: './update-employee.component.css'
})
export class UpdateEmployeeComponent implements OnInit{
  employee: Employee = new Employee();
  id: number;
  constructor(private employeeService:EmployeeService, private router:Router, private route:ActivatedRoute){}
  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.employeeService.getEmployeeById(this.id).subscribe(data => {
      this.employee = data;
    })
  }

  saveEmployee(){
    this.employeeService.updateEmployeeById(this.id,this.employee,).subscribe(data=>{
      console.log(data);
    },
    error => console.log(error));
  }

  gotToEmployeeList(){
    this.router.navigate(['/employees']);
  }

  onSubmit(){
    this.saveEmployee();
    this.gotToEmployeeList();
  }
}
