import { Component, OnInit } from '@angular/core';
import { AbstractControl, AsyncValidatorFn, FormBuilder, FormControl, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { delay, map, switchMap } from 'rxjs/operators';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  [x: string]: any;
  dataForm!: FormGroup;
  constructor(public crudApi: UserService, public fb: FormBuilder, private router: Router) {}

  ngOnInit(): void {
    this.infoForm();
  }

  infoForm(){
    /*Create Form group*/
    this.dataForm = this.fb.group({
      userName: ['', [Validators.required], [this.usernameExistsValidator()]],
      firstname: ['', [Validators.required]],
      lastname: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.pattern("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
      + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")], [this.emailExistsValidator()]],    
      password: ['', [Validators.required]],
      pwdd: ['', [Validators.required]]
    },
    {
      validators: this.MustMatch('password', 'pwdd')
    })
  }

  MustMatch(controlName: string, matchingControlName:string){
    return(formGroup: FormGroup)=>{
      const control = formGroup.controls[controlName];
      const matchingControl = formGroup.controls[matchingControlName];
      if(matchingControl.errors && !matchingControl.errors.MustMatch){
        return;
      }
      if(control.value !== matchingControl.value){
        matchingControl.setErrors({MustMatch:true});
      }else{
        matchingControl.setErrors(null);
      }
    }
  }

  private emailExistsValidator(): AsyncValidatorFn {
    return (control: AbstractControl): Observable<ValidationErrors | null> => {
      return of(control.value).pipe(
        delay(500),
        switchMap((email: any) => this.crudApi.doesEmailExist(email).pipe(
          map(emailExists => emailExists ? { emailExists: true } : null)
        ))
      );
    };
  }

  private usernameExistsValidator(): AsyncValidatorFn {
    return (control: AbstractControl): Observable<ValidationErrors | null> => {
      // Of is a asynchronous function
      return of(control.value).pipe(
        delay(500),
        switchMap((username: any) => this.crudApi.doesUsernameExist(username).pipe(
          map(userExists => userExists ? { userExists: true } : null)
        ))
      );
    };
  }

  onSubmit() {
    const val = this.dataForm.value;
    this.addData();
  }

  addData() {
    console.log(this.crudApi.createData(this.dataForm.value));
    this.crudApi.createData(this.dataForm.value).
    subscribe( (data: any) => {
      console.log("Registion success");
      this.router.navigate(['/login']);
    });
  }
}
