import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../services/employee.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [],
  templateUrl: './employee-list.component.html',
  styleUrl: './employee-list.component.css',
})
export class EmployeeListComponent implements OnInit {
  employees: Employee[];
  constructor( private employeeService:EmployeeService, private router:Router){}
  ngOnInit(): void {
    this.getEmpoloyees();
  }

  private getEmpoloyees(){
    this.employeeService.getEmployeesList().subscribe(data =>{
      this.employees = data;
    });
  }

  navigateToUpdate(id:number){
    this.router.navigate(['update-employee',id]);
  }

  deleteEmployee(id:number){
    this.employeeService.deleteEmployeeById(id).subscribe(data =>{
      console.log(data);
      this.getEmpoloyees();
    },error=>{
      console.log(error)
    })
  }

  detailsEmployee(id:number){
    this.router.navigate(['employee-details',id]);
  }
}
